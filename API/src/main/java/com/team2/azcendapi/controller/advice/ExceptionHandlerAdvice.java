package com.team2.azcendapi.controller.advice;

import com.team2.azcendapi.exception.BadRequestException;
import com.team2.azcendapi.exception.ResourceConflictException;
import com.team2.azcendapi.exception.ResourceNotFoundException;
import com.team2.azcendapi.model.generic.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException(ResourceNotFoundException x) {
        LOGGER.error(x.getMessage());
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, x.getMessage(),
                x.getCustomUserMessage());
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorMessage> handleException(BadRequestException x) {
        LOGGER.error(x.getMessage());
        ErrorMessage message = new ErrorMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST, x.getMessage(), x.getCustomUserMessage());
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ResourceConflictException.class)
    public ResponseEntity<ErrorMessage> handleException(ResourceConflictException x) {
        LOGGER.error(x.getMessage());
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, x.getMessage(), x.getCustomUserMessage());
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.CONFLICT);
    }

}
