package ma.ens.AviCultureBackend.Jwts;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import ma.ens.AviCultureBackend.exeption.AuthenticationInvalidSessionException;
import ma.ens.AviCultureBackend.exeption.AuthenticationInvalidTokenException;
import ma.ens.AviCultureBackend.exeption.UnauthorizedException;
import ma.ens.AviCultureBackend.user.modal.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/token")
public class TokenController {

    private final JwtService jwtService;

    @PostMapping(path = "/refresh")
    public void getAccessTokenByRefreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getCookies() == null || request.getCookies().length == 0) {
            throw new AuthenticationInvalidTokenException("Refresh Token not Found");
        }
        Cookie refreshTokenCookie = stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("refresh_token"))
                .findFirst().orElseThrow(() -> new AuthenticationInvalidTokenException("Refresh Token not Found"));

        String accessToken = jwtService.generateAccessTokenWithRefreshToken(refreshTokenCookie.getValue());
        Cookie accessTokenCookie = new Cookie("access_token", accessToken);
        accessTokenCookie.setMaxAge(1800);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setHttpOnly(true);
        response.addCookie(accessTokenCookie);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
