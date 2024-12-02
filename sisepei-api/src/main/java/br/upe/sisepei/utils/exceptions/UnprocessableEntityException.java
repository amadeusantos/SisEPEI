package br.upe.sisepei.utils.exceptions;

import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.*;

@Getter
public class UnprocessableEntityException extends RuntimeException {
    private final Map<String, List<String>> fieldErrors;

    public UnprocessableEntityException(String message, List<FieldError> fieldErrors) {
        super(message);
        Map<String, List<String>> errors = new HashMap<>();
        for (FieldError fieldError : fieldErrors) {
            if (errors.containsKey(fieldError.getField())) {
                errors.get(fieldError.getField()).add(fieldError.getDefaultMessage());
            } else {
                List<String> messages = List.of(Objects.requireNonNull(fieldError.getDefaultMessage()));
                errors.put(fieldError.getField(), messages);
            }
        }
        this.fieldErrors = errors;
    }
}
