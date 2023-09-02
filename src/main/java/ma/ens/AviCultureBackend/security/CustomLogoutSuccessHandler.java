package ma.ens.AviCultureBackend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.Jwts.JwtService;
import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.service.UserSessionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    private final UserSessionService userSessionService;
    private final JwtService jwtService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String accessToken = jwtService.extractAccessTokenFromRequest(request);
        User user = jwtService.extractUserFromAccessToken(accessToken);
        userSessionService.closeOpenUserSessions(user);
    }
}
