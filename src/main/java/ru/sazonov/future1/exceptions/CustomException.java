package ru.sazonov.future1.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public abstract class CustomException extends RuntimeException {

    @Getter
    @Setter
    HttpStatus httpStatus;

    public CustomException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}
