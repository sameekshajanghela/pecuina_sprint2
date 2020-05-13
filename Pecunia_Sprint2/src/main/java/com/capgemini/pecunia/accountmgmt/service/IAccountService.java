package com.capgemini.pecunia.accountmgmt.service;

import java.util.List;
import com.capgemini.pecunia.accountmgmt.entities.Account;
import com.capgemini.pecunia.accountmgmt.entities.Address;
import com.capgemini.pecunia.accountmgmt.entities.Customer;

public interface IAccountService {

	String addAccount(Customer customer, Address address, Account account);

	boolean deleteAccount(String accountId);

	Account showAccountDetails(String accountId);

	Account findByAccountId(String accountId);

	List<Account> fetchAllAccounts();

	boolean updateCustomerName(Account account, Customer customer);

	boolean updateCustomerContact(Account account, Customer customer);

	boolean updateCustomerAddress(Account account, Address address);

	String addCustomerDetails(Customer customer, Address address);

	Customer findByCustomerId(String customerId);
}
