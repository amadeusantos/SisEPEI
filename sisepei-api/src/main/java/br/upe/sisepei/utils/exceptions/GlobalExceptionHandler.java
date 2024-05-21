package br.upe.sisepei.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(404, exception.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponse> handleValidationException(ValidationException exception) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ExceptionResponse(403, exception.getMessage()));
    }
}
