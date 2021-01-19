package com.assignment.roim.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

public class RsponseEntityHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse  = new ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());
        return new ResponseEntity(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(PaymentException.class)
    public final ResponseEntity<Object> PaymentException(Exception ex,WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());
        return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(UserNotFound.class)
    public final ResponseEntity<Object> UserNotFooundException(Exception ex,WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(ex.getMessage(),request.getDescription(false),new Date());
        return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);
    }
    protected ResponseEntity<Object> handleMethodArgument(MethodArgumentNotValidException ex, HttpHeaders httpHeaders
            , HttpStatus status, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Validation Falied",
                ex.getBindingResult().toString(),new Date());
        return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

}
