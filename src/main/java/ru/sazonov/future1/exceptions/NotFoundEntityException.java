package ru.sazonov.future1.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundEntityException extends CustomException {
    public NotFoundEntityException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
