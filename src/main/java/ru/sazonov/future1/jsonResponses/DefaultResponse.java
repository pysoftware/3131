package ru.sazonov.future1.jsonResponses;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class DefaultResponse extends CustomResponse {
    @Getter
    private String message;

    public DefaultResponse(String message, HttpStatus status) {
        super(status);
        this.message = message;
    }
}
