package com.capgemini.pecunia.accountmgmt.exceptions;

public class ChequeBouncedException extends RuntimeException{

    public ChequeBouncedException(String msg){
        super(msg);
    }
}
