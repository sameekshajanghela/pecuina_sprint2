package com.capgemini.pecunia.transactionmgmt.exception;

public class InvalidChequeNumberException extends RuntimeException {
	public InvalidChequeNumberException(String msg) {
		super(msg);
	}
}
