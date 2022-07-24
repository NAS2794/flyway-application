package com.flwaway.Exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConverterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ValidationException;
import java.io.IOException;
import java.net.SocketException;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class ControllerExceptionhandler {

    @ExceptionHandler
    public ResponseEntity<ItemErrorResponse> handleException(SocketException ex){

        System.out.println("ValidationException");
        ItemErrorResponse errorResponse = new ItemErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ItemErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ItemErrorResponse> handleException(ValidationException ex){

        System.out.println("ValidationException");
        ItemErrorResponse errorResponse = new ItemErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ItemErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ItemErrorResponse> handleException(DateTimeException ex){

        System.out.println("DateTimeException");
        ItemErrorResponse errorResponse = new ItemErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ItemErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConverterNotFoundException.class)
    public ResponseEntity<ItemErrorResponse> handleRuntimeException(ConverterNotFoundException ex){

        System.out.println("ConverterNotFoundException");
        ItemErrorResponse errorResponse = new ItemErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ItemErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ItemErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        BindingResult result = ex.getBindingResult();

        System.out.println("Web handle exception");

        List<String> errorList = new ArrayList<>();
        result.getFieldErrors().forEach((fieldError) -> {
            System.out.println("Default msg"+fieldError.getDefaultMessage());
            errorList.add( fieldError.getDefaultMessage());
        });
        result.getGlobalErrors().forEach((fieldError) -> {
            errorList.add(fieldError.getObjectName()+" : " +fieldError.getDefaultMessage() );
        });

        ItemErrorResponse errorResponse = new ItemErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage("Invalid request.Refer to messages and try again!!");
        errorResponse.setTimeStamp(System.currentTimeMillis());
        errorResponse.setFieldErrors(errorList);
        return errorResponse;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ItemErrorResponse> handleRuntimeException(Exception ex){

        System.out.println("Unknown Exception Occured");
        ItemErrorResponse errorResponse = new ItemErrorResponse();
        errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<ItemErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
