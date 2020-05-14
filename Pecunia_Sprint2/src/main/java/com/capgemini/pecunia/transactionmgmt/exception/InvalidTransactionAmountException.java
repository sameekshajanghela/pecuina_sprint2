package com.capgemini.pecunia.transactionmgmt.exception;

public class InvalidTransactionAmountException extends RuntimeException {
	public InvalidTransactionAmountException(String msg) {
		super(msg);
	}
}
