package com.capgemini.pecunia.transactionmgmt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.pecunia.transactionmgmt.entities.Transaction;

public interface TransactionDao extends JpaRepository<Transaction,String> {

}
