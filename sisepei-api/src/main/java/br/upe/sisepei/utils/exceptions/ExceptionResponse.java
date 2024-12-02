package br.upe.sisepei.utils.exceptions;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private final int code;

    private final String message;

    private final Object details;

    public ExceptionResponse(int code, String message) {
        this.code = code;
        this.message = message;
        this.details = null;
    }

    public ExceptionResponse(int code, String message, Object details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }
}
