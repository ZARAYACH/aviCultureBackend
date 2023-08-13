package ma.ens.AviCultureBackend.Jwts;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import ma.ens.AviCultureBackend.exeption.InvalidToken;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.repository.UserRepo;
import ma.ens.AviCultureBackend.user.service.UserSessionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Base64;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtServiceImpl implements JwtsService {

	@Value("${token.signing.key}")
	private String jwtSigningKey;
	private final UserRepo userRepo;
	private final UserSessionService userSessionService;

	private final Algorithm accessTokenAlgorithm = getAccessTokenAlgorithm();
	private final Algorithm refreshTokenAlgorithm = getRefreshTokenAlgorithm();


	@Override
	public User extractUserFromAccessToken(String accessToken) throws NotFoundException, InvalidToken {
		DecodedJWT decodedJWT = getDecodedToken(accessToken, accessTokenAlgorithm);
		return userRepo.findUserByEmail(decodedJWT.getSubject())
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public User extractUserFromRefreshToken(String refreshToken) throws InvalidToken, NotFoundException {
		DecodedJWT decodedJWT = getDecodedToken(refreshToken, refreshTokenAlgorithm);
		return userRepo.findUserByEmail(decodedJWT.getSubject())
				.orElseThrow(NotFoundException::new);
	}

	@Override
	public String generateAccessToken(User user, String sessionId) {
		return JWT.create()
				.withSubject(user.getUsername())
				.withClaim("sessionId", sessionId)
				.withClaim("roles", user.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.withIssuedAt(Date.valueOf(LocalDate.now()))
				.withExpiresAt(new Date(System.currentTimeMillis() + 15 * 60 * 1000))
				.sign(accessTokenAlgorithm);

	}

	@Override
	public String generateRefreshToken(User user, String sessionId) {
		return JWT.create()
				.withSubject(user.getUsername())
				.withIssuedAt(Date.valueOf(LocalDate.now()))
				.withExpiresAt(Date.valueOf(LocalDate.now().plusMonths(3)))
				.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(refreshTokenAlgorithm);
	}

	@Override
	public boolean isAccessTokenValid(String accessToken) {
		try {
			User user = extractUserFromAccessToken(accessToken);
			DecodedJWT decodedJWT = getDecodedToken(accessToken, accessTokenAlgorithm);
			return userSessionService.isValideSession(decodedJWT.getClaim("sessionId").asString(), user);
		} catch (InvalidToken | NotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isRefreshTokenValid(String refreshToken) {
		try {
			User user = extractUserFromRefreshToken(refreshToken);
			DecodedJWT decodedJWT = getDecodedToken(refreshToken, refreshTokenAlgorithm);
			return userSessionService.isValideSession(decodedJWT.getClaim("sessionId").asString(), user);
		} catch (InvalidToken | NotFoundException e) {
			e.printStackTrace();
			return false;
		}

	}

	private DecodedJWT getDecodedToken(String token, Algorithm tokenAlgorithm) throws InvalidToken {
		try {
			JWTVerifier verifier = JWT.require(tokenAlgorithm).build();
			return verifier.verify(token);
		} catch (JWTVerificationException e) {
			throw new InvalidToken();
		}
	}

	private Algorithm getRefreshTokenAlgorithm() {
		return Algorithm.HMAC256(Base64.getEncoder()
				.encodeToString(jwtSigningKey.getBytes(StandardCharsets.UTF_8)));
	}

	private Algorithm getAccessTokenAlgorithm() {
		return Algorithm.HMAC256(jwtSigningKey.getBytes(StandardCharsets.UTF_8));
	}
}
