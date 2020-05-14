package com.capgemini.pecunia.transactionmgmt.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.pecunia.accountmgmt.dao.IAccountDao;
import com.capgemini.pecunia.accountmgmt.entities.Account;
import com.capgemini.pecunia.transactionmgmt.dao.ChequeDao;
import com.capgemini.pecunia.transactionmgmt.dao.TransactionDao;
import com.capgemini.pecunia.transactionmgmt.entities.Cheque;
import com.capgemini.pecunia.transactionmgmt.entities.Transaction;
import com.capgemini.pecunia.transactionmgmt.exception.AccountNotFoundException;
import com.capgemini.pecunia.transactionmgmt.util.TransactionUtil;


@Service
@Transactional
public class TransactionService implements ITransactionService {
	
	@Autowired
	IAccountDao accountDao;
	@Autowired
	TransactionUtil util;
	@Autowired
	TransactionDao transactionDao;
	@Autowired 
	ChequeDao chequeDao;

	@Override
	public double getBalance(Account account) {
		return account.getAccountBalance();
	}

	@Override
	public boolean updateBalance(Account account, double balance) {
		account.setAccountBalance(balance);
		return true;
	}

	@Override
	public Account getAccountById(String accNumber) {
		Optional<Account> optional=accountDao.findById(accNumber);
		if(optional.isPresent())
		{
			Account account=optional.get();
			return account;
		}
		throw new AccountNotFoundException("Account not exist for "+ accNumber);
	}

	@Override
	public int creditUsingSlip(Transaction transaction) {
		String transId=util.generateId(6);
		transaction.setTransId(transId);
		if(util.validateCreditSlip(transaction)) {
			Account account=getAccountById(transaction.getTransAccountId());
			account.setAccountBalance(account.getAccountBalance()+transaction.getTransAmount());
			accountDao.save(account);
			transactionDao.save(transaction);
			return 1;
		}
		else {
			return 0;
		}
		
	}

	@Override
	public int debitUsingSlip(Transaction transaction) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int creditUsingCheque(Transaction transaction, Cheque cheque) {
		String transId=util.generateId(6);
		transaction.setTransId(transId);
		String chequeId=util.generateId(6);
		cheque.setChequeId(chequeId);
		if(util.validateCheque(cheque, transaction)) {
			Account account=getAccountById(transaction.getTransAccountId());
			account.setAccountBalance(account.getAccountBalance()+transaction.getTransAmount());
			accountDao.save(account);
			chequeDao.save(cheque);
			transactionDao.save(transaction);
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public int debitUsingCheque(Transaction transaction, Cheque cheque) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String generateTransactionId(Transaction transaction) {
		String transId=util.generateId(6);
		return transId;
	}

	@Override
	public String generateChequeId(Cheque cheque) {
		String chequeId=util.generateId(6);
		return chequeId;
	}

}
