package ma.ens.AviCultureBackend.exeption;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationInvalidSessionException extends AuthenticationException {
    public AuthenticationInvalidSessionException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public AuthenticationInvalidSessionException(String msg) {
        super(msg);
    }
}
