package config.exceptionhandler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

/**
 * Created by chetan on 25.12.2017.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFound(
            EntityNotFoundException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage(), ex));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<Object> handleValidationException(
            DataIntegrityViolationException ex) {
        String message = "Offer with same job title already exists.";
        return buildResponseEntity(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, message, ex));
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<Object> handleAllOtherExceptions(
            RuntimeException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_GATEWAY, ex.getMessage(), ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
