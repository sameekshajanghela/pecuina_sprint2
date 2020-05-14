package com.capgemini.pecunia.transactionmgmt.exception;

public class InvalidChequeException extends RuntimeException{
	public InvalidChequeException(String msg) {
		super(msg);
	}
}
