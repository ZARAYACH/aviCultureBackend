package ma.ens.AviCultureBackend.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AuthenticationInvalidRefreshTokenException extends AuthenticationException {
    public AuthenticationInvalidRefreshTokenException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationInvalidRefreshTokenException(String msg) {
        super(msg);
    }
}
