package com.capgemini.pecunia.accountmgmt.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.capgemini.pecunia.accountmgmt.dao.IAccountDao;
import com.capgemini.pecunia.accountmgmt.dao.IAddressDao;
import com.capgemini.pecunia.accountmgmt.dao.ICustomerDao;
import com.capgemini.pecunia.accountmgmt.entities.Account;
import com.capgemini.pecunia.accountmgmt.entities.Address;
import com.capgemini.pecunia.accountmgmt.entities.Customer;
import com.capgemini.pecunia.accountmgmt.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.accountmgmt.exceptions.CustomerNotFoundException;
import com.capgemini.pecunia.accountmgmt.exceptions.InvalidArgumentException;
import com.capgemini.pecunia.accountmgmt.util.AccountUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/***
 * @author Sameeksha Janghela
 */

@Service
@Transactional
public class AccountServiceImplementation implements IAccountService {

	@Autowired
	private IAccountDao accountDao;

	@Autowired
	private ICustomerDao customerDao;

	@Autowired
	private IAddressDao addressDao;

	/**
	 * @param account,customer,address This method will validate account and add it
	 *                                 to the database
	 * @return account
	 */
	@Override
	public String addAccount(Customer customer, Address address, Account account) {
		if (account == null) {
			throw new InvalidArgumentException("Account can't be null");
		}
		if (address == null) {
			throw new InvalidArgumentException("Address can't be null");
		}
		if (customer == null) {
			throw new InvalidArgumentException("Customer can't be null");
		}
		String addressId = AccountUtil.generateId("add-",6);
		address.setAddressId(addressId);
		address=addressDao.save(address);

		String customerId = AccountUtil.generateId("cust-",6);
		customer.setCustomerId(customerId);
		customer.setCustomerAddress(address);
		customer=customerDao.save(customer);

		String accountId = AccountUtil.generateId("",12);
		account.setAccountId(accountId);
		account.setCustomer(customer);
        account.setAccountStatus("active");
        account.setAccountHolderId(customerId);
        account.setCustomer(customer);
        account.setLastUpdated(new Date());
		account=accountDao.save(account);
		return accountId;
	}

	/**
	 * @param accountId This method will show account details by account id
	 * @return account
	 */
	@Override
	public Account showAccountDetails(String accountId) {
		Account account = findByAccountById(accountId);
		return account;
	}

	/**
	 * @param accountId This method will fetch the account by account id
	 * @return
	 */
	@Override
	public Account findByAccountById(String accountId) {
		Optional<Account> optional = accountDao.findById(accountId);
		if (optional.isPresent()) {
			Account account = optional.get();
			return account;
		}
		throw new AccountNotFoundException("account not found for id=" + accountId);
	}

	@Override
	public Customer findByCustomerId(String customerId) {
		Optional<Customer> optional = customerDao.findById(customerId);
		if (optional.isPresent()) {
			Customer customer = optional.get();
			return customer;
		}
		throw new CustomerNotFoundException("customer not found for id=" + customerId);
	}

	/**
	 * This method will return list of all account
	 *
	 * @return List of accounts
	 */
	@Override
	public List<Account> fetchAllAccounts() {
		List<Account> list = accountDao.findAll();
		return list;
	}

	/**
	 * @param accountId This method will delete the account by account id
	 * @return
	 */
	@Override
	public boolean deleteAccount(String accountId) {
		Account account = findByAccountById(accountId);
		account.setAccountStatus("Close");
		return true;
	}

	/**
	 * This method will update the customer name
	 *
	 * @return
	 */
	@Override
	public String updateCustomerName(Account account, Customer customer) {
		boolean exists = accountDao.existsById(account.getAccountId());
		if (exists) {
			customer = customerDao.save(customer);
			return "Customer Name Updated Successfully";
		}
		return " Update Unsuccessful";
	}

	/**
	 * This method will update the customer contact
	 *
	 * @return
	 */
	@Override
	public String updateCustomerContact(Account account, Customer customer) {
		boolean exists = accountDao.existsById(account.getAccountId());
		if (exists) {
			customer = customerDao.save(customer);
			return "Customer Contact Updated Succesfully";
		}
		return "Update Unsuccessful";
	}

	/**
	 * This method will update the customer address
	 *
	 * @return
	 */
	@Override
	public String updateCustomerAddress(Account account, Address address) {
		boolean exists = accountDao.existsById(account.getAccountId());
		if (exists) {
			address = addressDao.save(address);
			return "Customer address added successfully";
		}
		return "Update Unsuccessful";
	}

	
}
