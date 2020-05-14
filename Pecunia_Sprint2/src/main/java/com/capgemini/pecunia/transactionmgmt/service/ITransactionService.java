package com.capgemini.pecunia.transactionmgmt.service;

import com.capgemini.pecunia.accountmgmt.entities.Account;
import com.capgemini.pecunia.transactionmgmt.entities.Cheque;
import com.capgemini.pecunia.transactionmgmt.entities.Transaction;

public interface ITransactionService {
	public double getBalance(Account account);

	public boolean updateBalance(Account account, double balance);

	public Account getAccountById(String accNumber);

	public int creditUsingSlip(Transaction transaction);

	public int debitUsingSlip(Transaction transaction);

	public int creditUsingCheque(Transaction transaction, Cheque cheque);

	public int debitUsingCheque(Transaction transaction, Cheque cheque);
	
	public String generateTransactionId(Transaction transaction);
	
	public String generateChequeId(Cheque cheque);

}
