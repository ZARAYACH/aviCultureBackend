package ma.ens.AviCultureBackend.exeption;

import ma.ens.AviCultureBackend.exeption.modal.ExceptionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@EnableAspectJAutoProxy
@ControllerAdvice
@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(CustomizedResponseEntityExceptionHandler.class);

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ExceptionDto> handleNotFoundException(Exception ex) {
		ex.printStackTrace();
        return new ResponseEntity<>(ExceptionDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({BadRequestExeption.class})
    public ResponseEntity<ExceptionDto> handleBadRequestExceptions(Exception ex) {
		ex.printStackTrace();
        return new ResponseEntity<>(ExceptionDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<ExceptionDto> handleUnauthorizedException(Exception ex) {
		ex.printStackTrace();
        return new ResponseEntity<>(ExceptionDto.builder()
                .message(ex.getMessage())
                .status(HttpStatus.FORBIDDEN)
                .build(), HttpStatus.FORBIDDEN);
    }

	@ExceptionHandler({AuthenticationException.class})
	public ResponseEntity<ExceptionDto> handleUnauthenticatedException(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<>(ExceptionDto.builder()
				.message(ex.getMessage())
				.status(HttpStatus.UNAUTHORIZED)
				.build(), HttpStatus.UNAUTHORIZED);
	}
	@ExceptionHandler({TooManyRequestsException.class})
	public ResponseEntity<ExceptionDto> handleTooManyRequestsException(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<>(ExceptionDto.builder()
				.message(ex.getMessage())
				.status(HttpStatus.TOO_MANY_REQUESTS)
				.build(), HttpStatus.TOO_MANY_REQUESTS);
	}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDto> handleAnyOtherException(Exception ex) {
		ExceptionDto exceptionDto = ExceptionDto.builder()
				.message("Internal Server Error")
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.build();
		logger.error("Error id: {}", exceptionDto.getErrorId(), ex);
		return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}



