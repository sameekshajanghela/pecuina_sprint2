package com.capgemini.pecunia.transactionmgmt.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.pecunia.transactionmgmt.entities.Cheque;
import com.capgemini.pecunia.transactionmgmt.entities.Transaction;
import com.capgemini.pecunia.transactionmgmt.service.TransactionService;
import com.capgemini.pecunia.transactionmgmt.util.TransactionUtil;

@RequestMapping("/transaction")
@RestController
public class TransactionRestController {
	private static final Logger Log = LoggerFactory.getLogger(TransactionRestController.class);
	
	@Autowired
	TransactionService service;
	
	@PostMapping("/creditusingslip")
	public ResponseEntity<String> creditUsingSlip(Map<String, Object> request){
		Transaction transaction=TransactionUtil.convertToTransactionUsingSlip(request);
		int result=service.creditUsingSlip(transaction);
		if(result==1)
			return new ResponseEntity<String>("Transaction Successful",HttpStatus.OK);
		else
			return new ResponseEntity<String>("UnSuccessFul",HttpStatus.BAD_REQUEST);
	}
	@PostMapping("/creditUsingCheque")
	public ResponseEntity<String> creditUsingCheque(Map<String,Object> request){
		Cheque cheque=TransactionUtil.convertToCheque(request);
		Transaction transaction=TransactionUtil.convertToTransactionUsingCheque(cheque);
		int result=service.creditUsingCheque(transaction, cheque);
		if(result==1)
			return new ResponseEntity<String>("Transaction Successful",HttpStatus.OK);
		else
			return new ResponseEntity<String>("UnSuccessFul",HttpStatus.BAD_REQUEST);
	}

}
