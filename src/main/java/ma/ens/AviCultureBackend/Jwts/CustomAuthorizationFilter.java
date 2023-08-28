package ma.ens.AviCultureBackend.Jwts;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.user.modal.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RequiredArgsConstructor
@Component
public class CustomAuthorizationFilter extends OncePerRequestFilter {

	private final JwtService jwtsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		if (request.getMethod().equals("POST") && request.getServletPath().equals("/login")) {
			filterChain.doFilter(request, response);
			return;
		}
		String authorizationHeader = request.getHeader(AUTHORIZATION);
		Cookie authorizationCookie = null;
		if (request.getCookies() != null && request.getCookies().length > 0) {
			authorizationCookie = stream(request.getCookies())
					.filter(cookie -> cookie.getName().equals("access_token"))
					.findFirst().orElse(null);
		}

		String token;
		if (authorizationCookie != null && StringUtils.isNotBlank(authorizationCookie.getValue())) {
			token = authorizationCookie.getValue();
		} else if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring("Bearer ".length());
		} else {
			filterChain.doFilter(request, response);
			return;
		}
		User user = jwtsService.extractUserFromAccessToken(token);
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			filterChain.doFilter(request, response);
	}
}
