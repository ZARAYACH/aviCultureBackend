package ma.ens.AviCultureBackend.Jwts;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.modal.UserSession;
import ma.ens.AviCultureBackend.user.repository.UserRepo;
import ma.ens.AviCultureBackend.user.repository.UserSessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtsService jwtsService;
    private final UserRepo userRepo;
    private final UserSessionRepo userSessionRepo;

    @Autowired
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, AuthenticationManager authenticationManager1, JwtsService jwtsService, UserRepo userRepo, UserSessionRepo userSessionRepo) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager1;
        this.jwtsService = jwtsService;
        this.userRepo = userRepo;
        this.userSessionRepo = userSessionRepo;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        try {
            EmailPasswordModal emailPasswordModal = new ObjectMapper().readValue(request.getInputStream(), EmailPasswordModal.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(emailPasswordModal.email(), emailPasswordModal.password());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new AuthenticationCredentialsNotFoundException("Couldn't extract credentials From the request", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        UserSession session = userSessionRepo.save(UserSession.builder().user(user).build());

        String accessToken = jwtsService.generateAccessToken(user, session.getId());
        String refreshToken = jwtsService.generateRefreshToken(user, session.getId());

        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        accessTokenCookie.setMaxAge(900);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setHttpOnly(true);
        response.addCookie(accessTokenCookie);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        //TODO: handle unsuccessfulAuthentication attempteds
        throw failed;
    }

}