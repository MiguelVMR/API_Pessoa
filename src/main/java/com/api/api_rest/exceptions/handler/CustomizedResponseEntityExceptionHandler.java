package com.api.api_rest.exceptions.handler;

import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.api.api_rest.exceptions.ExceptionResponce;
import com.api.api_rest.exceptions.UnsupportedMathOperationException;

@ControllerAdvice
@RestController

public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final  ResponseEntity <ExceptionResponce> handleAllExcepitons(
        Exception ex,WebRequest request) {
            ExceptionResponce exceptionResponce = new ExceptionResponce(
                new Date(),
                 ex.getMessage(),
                 request.getDescription(false));
                 return new ResponseEntity<>(exceptionResponce,HttpStatus.INTERNAL_SERVER_ERROR);
        
    }

    @ExceptionHandler(UnsupportedMathOperationException.class)
    public final  ResponseEntity <ExceptionResponce> handleBadRequestExcepitons(
        Exception ex,WebRequest request) {
            ExceptionResponce exceptionResponce = new ExceptionResponce(
                new Date(),
                 ex.getMessage(),
                 request.getDescription(false));
                 
                 return new ResponseEntity<>(exceptionResponce,HttpStatus.BAD_REQUEST);
        
    }

}
