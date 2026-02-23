package com.thirdeye3_2.video.manager.exceptions.handler;
import com.thirdeye3_2.video.manager.dtos.Response;
import com.thirdeye3_2.video.manager.exceptions.CorruptedMultiMediaException;
import com.thirdeye3_2.video.manager.exceptions.GeneratorFetchException;
import com.thirdeye3_2.video.manager.exceptions.PropertyFetchException;
import com.thirdeye3_2.video.manager.exceptions.ResourceNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response<Void>> handleUserNotFound(ResourceNotFoundException ex) {
        Response<Void> response = new Response<>(
                false,
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(CorruptedMultiMediaException.class)
    public ResponseEntity<Response<Void>> handleCurruptedMultiMedia(CorruptedMultiMediaException ex) {
        Response<Void> response = new Response<>(
                false,
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(PropertyFetchException.class)
    public ResponseEntity<Response<Void>> handlePropertyFetch(PropertyFetchException ex) {
        Response<Void> response = new Response<>(
                false,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(GeneratorFetchException.class)
    public ResponseEntity<Response<Void>> handlePropertyFetch(GeneratorFetchException ex) {
        Response<Void> response = new Response<>(
                false,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private <T> ResponseEntity<Response<T>> buildResponse(
            boolean success, int errorCode, String errorMessage, T body) {
        return ResponseEntity
                .status(errorCode)
                .body(new Response<>(success, errorCode, errorMessage, body));
    }
}
