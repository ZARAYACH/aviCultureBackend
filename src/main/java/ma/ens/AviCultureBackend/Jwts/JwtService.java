package ma.ens.AviCultureBackend.Jwts;

import ma.ens.AviCultureBackend.user.modal.User;
import ma.ens.AviCultureBackend.user.modal.UserSession;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {

	User extractUserFromAccessToken(String accessToken) throws AuthenticationException;

	String generateAccessTokenWithRefreshToken(String refreshToken) throws AuthenticationException;

	String generateAccessToken(UserDetails user, UserSession session);

	String generateRefreshToken(UserDetails user, UserSession session);

	boolean isAccessTokenValid(String AccessToken);
}
