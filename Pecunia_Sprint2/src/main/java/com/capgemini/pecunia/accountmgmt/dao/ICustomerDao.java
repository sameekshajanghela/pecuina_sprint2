package com.capgemini.pecunia.accountmgmt.dao;

import com.capgemini.pecunia.accountmgmt.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICustomerDao extends JpaRepository<Customer, String> {

}
