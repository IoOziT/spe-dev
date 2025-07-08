package fr.sdv.spe_dev.exceptionHandlers;

import fr.sdv.spe_dev.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse<String> handleUserNotFoundException(UserNotFoundException ex) {
        return new ErrorResponse<>(ex.getMessage(), "user_not_found");
    }

}
