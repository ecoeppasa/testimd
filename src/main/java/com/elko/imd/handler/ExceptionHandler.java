/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.elko.imd.handler;

import com.google.gson.JsonParseException;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.hibernate5.HibernateJdbcException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author elko
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
    @org.springframework.web.bind.annotation.ExceptionHandler({ Exception.class })
    protected ResponseEntity handleGeneral(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.toString(), 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler({NullPointerException.class})
    protected ResponseEntity handleNotFound(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Error JSON Variabel ", 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler({JsonParseException.class})
    protected ResponseEntity handleGSon(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Invalid JSON Format!, Please Check again! ", 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    @org.springframework.web.bind.annotation.ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity handleValidation(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Error validator, Please Check your data! such as email format, variable name and field cannot be empty", 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
     @org.springframework.web.bind.annotation.ExceptionHandler({HibernateJdbcException.class})
    protected ResponseEntity hibernateSession(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Session is closed!", 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
