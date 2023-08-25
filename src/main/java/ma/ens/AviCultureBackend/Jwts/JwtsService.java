package ma.ens.AviCultureBackend.Jwts;

import ma.ens.AviCultureBackend.user.modal.User;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtsService {

	User extractUserFromAccessToken(String accessToken) throws AuthenticationException;

	User extractUserFromRefreshToken(String refreshToken) throws AuthenticationException;

	String generateAccessToken(UserDetails user, String sessionId);

	String generateRefreshToken(UserDetails user, String sessionId);

	boolean isAccessTokenValid(String AccessToken);
	boolean isRefreshTokenValid(String refreshToken);

}
