package com.capgemini.pecunia.accountmgmt.service;

import java.util.List;
import com.capgemini.pecunia.accountmgmt.entities.Account;
import com.capgemini.pecunia.accountmgmt.entities.Address;
import com.capgemini.pecunia.accountmgmt.entities.Customer;

public interface IAccountService {

	String addAccount(Customer customer, Address address, Account account);

	boolean deleteAccount(String accountId);

	Account showAccountDetails(String accountId);

	Account findByAccountById(String accountId);

	List<Account> fetchAllAccounts();

	String updateCustomerName(Account account, Customer customer);

    String updateCustomerContact(Account account, Customer customer);

	String updateCustomerAddress(Account account, Address address);

	Customer findByCustomerId(String customerId);
}
