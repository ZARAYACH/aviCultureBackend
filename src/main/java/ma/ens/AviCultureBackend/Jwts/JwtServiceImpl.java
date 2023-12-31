package ma.ens.AviCultureBackend.Jwts;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import ma.ens.AviCultureBackend.exeption.*;
import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.modal.UserSession;
import ma.ens.AviCultureBackend.user.repository.UserRepo;
import ma.ens.AviCultureBackend.user.service.UserSessionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class JwtServiceImpl implements JwtService {
    private final String jwtSigningKey;
    private final UserRepo userRepo;
    private final UserSessionService userSessionService;
    private final Algorithm accessTokenAlgorithm;
    private final Algorithm refreshTokenAlgorithm;

    @Autowired
    public JwtServiceImpl(@Value("${token.signing.key}") String jwtSigningKey, UserRepo userRepo, UserSessionService userSessionService) {
        this.jwtSigningKey = jwtSigningKey;
        this.userRepo = userRepo;
        this.userSessionService = userSessionService;
        accessTokenAlgorithm = getAccessTokenAlgorithm();
        refreshTokenAlgorithm = getRefreshTokenAlgorithm();
    }

    @Override
    public User extractUserFromAccessToken(String accessToken) throws AuthenticationException {
        DecodedJWT decodedJWT = getDecodedToken(accessToken, accessTokenAlgorithm);
        User user = userRepo.findUserByEmail(decodedJWT.getSubject())
                .orElseThrow(() -> new AuthenticationNotFoundUserException("Could not find user with email " + decodedJWT.getSubject()));
        try {
            UserSession session = userSessionService.getUserSessionWithIdAndUser(decodedJWT.getClaim("sessionId").asString(), user);
            checkValidityOfUserSession(user, session);
            return user;
        } catch (NotFoundException e) {
            throw new AuthenticationInvalidSessionException("Session Not Found");
        }
    }

    @Override
    public String generateAccessTokenWithRefreshToken(String refreshToken) throws AuthenticationException {
        DecodedJWT decodedJWT = getDecodedToken(refreshToken, refreshTokenAlgorithm);
        User user = userRepo.findUserByEmail(decodedJWT.getSubject())
                .orElseThrow(() -> new AuthenticationNotFoundUserException("Could not find user with email " + decodedJWT.getSubject()));
        try {
            UserSession session = userSessionService.getUserSessionWithIdAndUser(decodedJWT.getClaim("sessionId").asString(), user);
            return generateAccessToken(user, session);
        } catch (NotFoundException e) {
            throw new AuthenticationInvalidSessionException("Session Not Found");
        }
    }

    @Override
    public String generateAccessToken(UserDetails user, UserSession session) {
        checkValidityOfUserSession(user, session);
        return JWT.create()
                .withSubject(user.getUsername())
                .withClaim("sessionId", session.getId())
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(new Date(System.currentTimeMillis() + 30 * 60 * 1000))
                .sign(accessTokenAlgorithm);
    }

    @Override
    public String generateRefreshToken(UserDetails user, UserSession session) {
        checkValidityOfUserSession(user, session);
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .withExpiresAt(Date.valueOf(LocalDate.now().plusMonths(3)))
                .withClaim("sessionId", session.getId())
                .withClaim("roles", user.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(refreshTokenAlgorithm);
    }

    @Override
    public boolean isAccessTokenValid(String accessToken) {
        try {
            return extractUserFromAccessToken(accessToken) != null;
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return false;
        }
    }

    private DecodedJWT getDecodedToken(String token, Algorithm tokenAlgorithm) throws AuthenticationException {
        try {
            JWTVerifier verifier = JWT.require(tokenAlgorithm).build();
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            throw new AuthenticationInvalidTokenException("Couldn't verify the token", e);
        }
    }

    @Override
    public String extractAccessTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        Cookie authorizationCookie = null;
        if (request.getCookies() != null && request.getCookies().length > 0) {
            authorizationCookie = stream(request.getCookies())
                    .filter(cookie -> cookie.getName().equals("access_token"))
                    .findFirst().orElse(null);
        }
        if (authorizationCookie != null && StringUtils.isNotBlank(authorizationCookie.getValue())) {
            return authorizationCookie.getValue();
        } else if (StringUtils.isNotBlank(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring("Bearer ".length());
        }
        throw new UnauthenticatedException("Could not extract access token from request");
    }

    private boolean checkValidityOfUserSession(UserDetails user, UserSession session) {
        if (user == null ||
                session == null ||
                session.getUser() == null ||
                !session.getUser().getEmail().equals(user.getUsername()) ||
                !session.isStillValid()) {
            throw new AuthenticationInvalidSessionException("Invalid Session");
        }
        return true;
    }

    private Algorithm getRefreshTokenAlgorithm() {
        return Algorithm.HMAC256(Base64.getEncoder()
                .encodeToString(jwtSigningKey.getBytes(StandardCharsets.UTF_8)));
    }

    private Algorithm getAccessTokenAlgorithm() {
        return Algorithm.HMAC256(jwtSigningKey.getBytes(StandardCharsets.UTF_8));
    }
}
