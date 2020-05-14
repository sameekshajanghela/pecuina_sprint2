package com.capgemini.pecunia.transactionmgmt.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.capgemini.pecunia.transactionmgmt.entities.Cheque;
import com.capgemini.pecunia.transactionmgmt.entities.Transaction;
import com.capgemini.pecunia.transactionmgmt.exception.*;

public class TransactionUtil {
	public static String generateId(int length) {
		StringBuilder id = new StringBuilder();
		for (int i = 0; i < length; i++) {
			Random random = new Random();
			int number = random.nextInt(9);
			id.append(number);
		}
		return id.toString();
	}
	
	public static Transaction convertToTransactionUsingSlip(Map<String,Object> request) {
		Transaction transaction=new Transaction();
		String transAccountId=(String)request.get("transAccountId");
		transaction.setTransAccountId(transAccountId);
		transaction.setTransType("CR");
		double transAmount= (double)request.get("transAmount");
		transaction.setTransAmount(transAmount);
		transaction.setOption("Using  Slip");
		transaction.setTransDate(new Date());
		//String transFrom = (String)request.get("transFrom");
		//transaction.setTransFrom(transFrom);
		
		return transaction;
	}
	
	
	public static Transaction convertToTransactionUsingCheque(Cheque cheque) {
		Transaction transaction=new Transaction();
		transaction.setTransAccountId(cheque.getChequeAccountNum());
		transaction.setOption("CR");
		transaction.setTransAmount(cheque.getAmount());
		transaction.setTransDate(new Date());
		transaction.setTransType("Using Cheque");
		return transaction;
	}
	
	public static Cheque convertToCheque(Map<String,Object> request) {
		Cheque cheque=new Cheque();
		int chequeNum = (int) request.get("chequeNum");
		cheque.setChequeNum(chequeNum);
		String chequeAccountNum = (String)request.get("chequeAccountNum");
		cheque.setChequeAccountNum(chequeAccountNum);
		String chequeHolderName= (String)request.get("chequeHolderName");
		cheque.setChequeHolderName(chequeHolderName);
		String chequeBankName=(String) request.get("chequeBankName");
		cheque.setChequeBankName(chequeBankName);
		String chequeIFSC=(String) request.get("chequeIFSC");
		cheque.setChequeIFSC(chequeIFSC);
		Date IssueDate=(Date)request.get("IssueDate");
		cheque.setIssueDate(IssueDate);
		double amount=(double)request.get("amount");
		cheque.setAmount(amount);
		
		
		return cheque;
	}
	
	
	public static boolean validateCreditSlip(Transaction transaction) {

		if (transaction.transAccountId.length() != 12
				&& !(transaction.getTransAmount() >= 100 && transaction.getTransAmount() <= 100000)) {
			throw new IncorrectSlipDetailsException("Slip details are Invalid");
		}
		else {
			return true;
		}
	}

	public static boolean validateDebitSlip(Transaction transaction) {

		if (transaction.transAccountId.length() != 12) {
			throw new IncorrectSlipDetailsException("Slip details are Invalid");
		}
		else {
			return true;
		}
	}



	public static boolean validateCheque(Cheque cheque,Transaction transaction) {
		Date issuedDate = cheque.getIssueDate();
		int issuedMonth = issuedDate.getMonth();
		int issuedYear = issuedDate.getYear();
		int issuedDay = issuedDate.getDate();
		Date today=new Date();
		Calendar cal=Calendar.getInstance();
		cal.setTime(today);
		
		int currentMonth= cal.get(Calendar.MONTH);
		int currentYear = cal.get(Calendar.YEAR);
	
		if (currentYear - issuedYear > 1) {
			throw new InvalidChequeException("Cheque is expired");
		}
		if (currentYear == issuedYear && currentMonth - issuedMonth>3) {
			throw new InvalidChequeException("Cheque is expired");
		}

		if (currentYear != issuedYear && currentMonth+12 - issuedMonth  > 3) {
			throw new InvalidChequeException("Cheque is expired");
		}

		if (cheque.getChequeBankName() == null || cheque.getChequeBankName().isEmpty()) {
			throw new IncorrectChequeDetailsException("Cheque details are Incomplete");
		}

		String chequenum = String.valueOf(cheque.getChequeNum());
        System.out.println("inside validatecreditcheque");
		if (cheque.getChequeAccountNum().length() != 12) {
			throw new InvalidChequeLengthException("Cheque Account Number is incorrect");
	}
		System.out.println("Transaction Amount "+transaction.getTransAmount());
		 if (!(transaction.getTransAmount() >= 100 && transaction.getTransAmount() <= 200000)) {
			 throw new InvalidTransactionAmountException("Transaction Amount is not in limit");
		 }
		if (chequenum.length() != 6) {
			throw new InvalidChequeNumberException("Cheque Number length is incorrect ");
		}
		
		if(!(cheque.getChequeIFSC().length() == 10) && (cheque.getChequeIFSC().matches("\\w+"))){
			
				throw new InvalidChequeIFSCException("Invalid IFSC");
				
		
			
		}
		return true;
	}
}
