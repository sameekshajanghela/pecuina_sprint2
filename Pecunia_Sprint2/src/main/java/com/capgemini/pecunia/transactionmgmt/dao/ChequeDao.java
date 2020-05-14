package com.capgemini.pecunia.transactionmgmt.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.pecunia.transactionmgmt.entities.Cheque;

public interface ChequeDao extends JpaRepository<Cheque,String>{

}
