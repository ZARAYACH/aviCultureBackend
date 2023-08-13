package ma.ens.AviCultureBackend.Jwts;

import ma.ens.AviCultureBackend.exeption.InvalidToken;
import ma.ens.AviCultureBackend.exeption.NotFoundException;
import ma.ens.AviCultureBackend.user.modal.User;

public interface JwtsService {

	User extractUserFromAccessToken(String accessToken) throws NotFoundException, InvalidToken;

	User extractUserFromRefreshToken(String refreshToken) throws InvalidToken, NotFoundException;

	String generateAccessToken(User user, String sessionId);

	String generateRefreshToken(User user, String sessionId);

	boolean isAccessTokenValid(String AccessToken);
	boolean isRefreshTokenValid(String refreshToken);

}
