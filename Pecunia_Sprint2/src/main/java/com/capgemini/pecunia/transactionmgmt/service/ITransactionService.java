package com.capgemini.pecunia.transactionmgmt.service;

import com.capgemini.pecunia.accountmgmt.entities.Account;
import com.capgemini.pecunia.transactionmgmt.entities.Cheque;
import com.capgemini.pecunia.transactionmgmt.entities.Transaction;

public interface ITransactionService {
	 double getBalance(Account account);

	 boolean updateBalance(Account account, double balance);

	 Account getAccountById(String accNumber);

	 int creditUsingSlip(Transaction transaction);

	 int debitUsingSlip(Transaction transaction);

	 int creditUsingCheque(Transaction transaction, Cheque cheque);

	 int debitUsingCheque(Transaction transaction, Cheque cheque);

}
