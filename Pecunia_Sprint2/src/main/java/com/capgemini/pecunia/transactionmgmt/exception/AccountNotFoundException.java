package com.capgemini.pecunia.transactionmgmt.exception;

public class AccountNotFoundException extends RuntimeException{
	public AccountNotFoundException(String msg) {
		super(msg);
	}
}
