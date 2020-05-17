package com.capgemini.pecunia.transactionmgmt.controller;

import java.util.Map;

import com.capgemini.pecunia.accountmgmt.exceptions.ChequeBouncedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.pecunia.transactionmgmt.entities.Cheque;
import com.capgemini.pecunia.transactionmgmt.entities.Transaction;
import com.capgemini.pecunia.transactionmgmt.service.TransactionService;
import com.capgemini.pecunia.transactionmgmt.util.TransactionUtil;

@RequestMapping("/transactions")
@RestController
public class TransactionRestController {
    private static final Logger Log = LoggerFactory.getLogger(TransactionRestController.class);

    @Autowired
    private TransactionService service;

    @PostMapping("/credit/slip")
    public ResponseEntity<String> creditUsingSlip(@RequestBody Map<String, Object> request) {
        Transaction transaction = TransactionUtil.convertToTransactionUsingSlip(request);
        service.creditUsingSlip(transaction);
        return new ResponseEntity<String>("Transaction successful", HttpStatus.OK);

    }

    @PostMapping("/credit/cheque")
    public ResponseEntity<String> creditUsingCheque(@RequestBody Map<String, Object> request) {
        Cheque cheque = TransactionUtil.convertToCheque(request);
        Transaction transaction = TransactionUtil.convertToTransactionUsingCheque(cheque);
        service.creditUsingCheque(transaction, cheque);
        return new ResponseEntity<>("Transaction Successful", HttpStatus.OK);

    }


}
