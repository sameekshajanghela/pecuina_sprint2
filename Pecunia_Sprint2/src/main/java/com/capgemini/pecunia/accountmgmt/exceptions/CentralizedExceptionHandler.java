package com.capgemini.pecunia.accountmgmt.exceptions;

import com.capgemini.pecunia.accountmgmt.controller.AccountRestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class CentralizedExceptionHandler {
    private static final Logger Log = LoggerFactory.getLogger(AccountRestController.class);

    @ExceptionHandler(ChequeBouncedException.class)
    public ResponseEntity<String> chequeBouncedException(ChequeBouncedException exception) {
        String msg = exception.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        return response;
    }


    @ExceptionHandler(AccountNotCreatedException.class)
    public ResponseEntity<String> handleAccountNotCreated(AccountNotCreatedException exception) {
        String msg = exception.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        return response;
    }

    /**
     * this method will run when Account not found
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<String> handleAccountNotFound(AccountNotFoundException ex) {
        Log.error("Account not found exception ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        return response;
    }

    /**
     * this method will run when ConstraintViolationException occur
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
        Log.error("constraint violation ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        return response;
    }

    /**
     * Blanket Exception Handler
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll(Throwable ex) {
        Log.error("Something went wrong ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

}
