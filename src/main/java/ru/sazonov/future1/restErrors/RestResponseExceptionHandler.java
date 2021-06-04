package ru.sazonov.future1.restErrors;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.sazonov.future1.exceptions.CustomException;
import ru.sazonov.future1.jsonResponses.DefaultResponse;
import ru.sazonov.future1.jsonResponses.ValidationErrorResponse;

@RestControllerAdvice(annotations = RestController.class)
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleBindException(
            BindException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        logger.info("handleBindException: \n" + ex.getBindingResult());
        return ResponseEntity.status(status).body(new ValidationErrorResponse(ex.getFieldErrors()));
    }

    @ExceptionHandler({CustomException.class})
    public ResponseEntity<?> defaultHandler(CustomException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(new DefaultResponse(ex.getMessage(), ex.getHttpStatus()));
    }
}
