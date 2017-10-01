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
 * ExceptionHandler is a mechanism to handle runtime errors such as Http Request, ClassNotFound, IO, SQL, Remote etc.. 
 * The exception handling in java is one of the powerful mechanism to handle the runtime errors so that normal flow of the application can be maintained.
 * There are mainly two types of exceptions: checked and unchecked where error is considered as unchecked exception. The sun microsystem says there are three types of exceptions:
 * 
 * <br> 1. Checked Exception
 * <br>     The classes that extend Throwable class except RuntimeException and Error are known as checked exceptions 
 *      e.g.IOException, SQLException etc. Checked exceptions are checked at compile-time.
 * <br> 2. Unchecked Exception
 * <br> The classes that extend RuntimeException are known as unchecked exceptions e.g. ArithmeticException, NullPointerException, ArrayIndexOutOfBoundsException etc. 
 *      Unchecked exceptions are not checked at compile-time rather they are checked at runtime.
 * <br> 3. Error 
 * <br> Error is irrecoverable e.g. OutOfMemoryError, VirtualMachineError, AssertionError etc.
 * 
 * @author elko
 * @since '2017-09-25'
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ExceptionHandler.html">ExceptionHandler</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/ControllerAdvice.html">ControllerAdvice</a>
 * @see <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/mvc/method/annotation/ResponseEntityExceptionHandler.html">ResponseEntityExceptionHandler</a>
 */
@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
    
    /**
     * handeGeneral is method for handle any exception thats not define on other method in this class     
     * @param ex is return value of exception class that gets from exception handler 
     * @param request is http response of web request 
     * @return <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
     * is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.
     * 
     */
    @org.springframework.web.bind.annotation.ExceptionHandler({ Exception.class })
    protected ResponseEntity handleGeneral(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.toString(), 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    /**
     * 
     * handeNotFound is method for throwing exception if services class have NullPointerException error
     * 
     * @param ex is return value of exception class that gets from exception handler 
     * @param request is http response of web request 
     * @return <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
     * is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler({NullPointerException.class})
    protected ResponseEntity handleNotFound(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Error JSON Variabel ", 
          new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
    
    /**
     * 
     * handleJson is method for throwing exception if raw input of  JSON has a invalid format
     * 
     * @param ex is return value of exception class that gets from exception handler 
     * @param request is http response of web request 
     * @return <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
     * is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler({JsonParseException.class})
    protected ResponseEntity handleJSon(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Invalid JSON Format!, Please Check again! ", 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    /**
     * 
     * handleValidation is method for throwing exception if input value has a invalid format. such as email format, number format, and etc.. 
     * 
     * @param ex is return value of exception class that gets from exception handler 
     * @param request is http response of web request 
     * @return <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
     * is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.
    */
    @org.springframework.web.bind.annotation.ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity handleValidation(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Error validator, Please Check your data! such as email format, variable name and field cannot be empty", 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    
    /**
     *
     * hibernateSession is method for throwing exception if session has been closed.
     * 
     * @param ex is return value of exception class that gets from exception handler 
     * @param request is http response of web request 
     * @return <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html">ResponseEntity</a>
     * is meant to represent the entire HTTP response. You can control anything that goes into it: status code, headers, and body.
     */
     @org.springframework.web.bind.annotation.ExceptionHandler({HibernateJdbcException.class})
    protected ResponseEntity hibernateSession(
      Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Session is closed!", 
          new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
