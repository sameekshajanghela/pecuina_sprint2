package com.capgemini.pecunia.accountmgmt.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.capgemini.pecunia.accountmgmt.dto.AccountDetails;
import com.capgemini.pecunia.accountmgmt.entities.Account;
import com.capgemini.pecunia.accountmgmt.entities.Address;
import com.capgemini.pecunia.accountmgmt.entities.Customer;

public class AccountUtil {
    private static final DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");

	/**
	 * @param account This method will generate random id according to the length passed           
	 * @return id
	 */
	public static String generateId(String prefix,int length) {
		StringBuilder id = new StringBuilder(prefix);
		for (int i = 0; i < length; i++) {
			Random random = new Random();
			int number = random.nextInt(9);
			id.append(number);
		}
		return id.toString();
	}

	public static Account convertToAccount(Map<String,Object> request) {
		Account account = new Account();
		double accountBalance = (double) request.get("accountBalance");
		account.setAccountBalance(accountBalance);
		String accountBranchId = (String) request.get("branchId");
		account.setAccountBranchId(accountBranchId);
		String accountType = (String) request.get("accountType");
		account.setAccountType(accountType);
		double accountInterest = (double) request.get("accountInterest");
		account.setAccountInterest(accountInterest);
		Date lastUpdated = (Date) request.get("lastUpdate");
		account.setLastUpdated(lastUpdated);
		return account;
		
	}

	public static AccountDetails convertToDetails(Account account){
		Customer customer=account.getCustomer();
		AccountDetails details=new AccountDetails();
        details.setAccountId(account.getAccountId());
        details.setAccountBalance(account.getAccountBalance());
        details.setAccountInterest(account.getAccountInterest());
        details.setAccountBranchId(account.getAccountBranchId());
        details.setAccountHolderId(account.getAccountHolderId());
        details.setAccountStatus(account.getAccountStatus());
        details.setAccountStatus(account.getAccountStatus());
        details.setAccountType(account.getAccountType());
        details.setCustomerId(customer.getCustomerId());
        details.setCustomerName(customer.getCustomerName());
        details.setLastUpdated(account.getLastUpdated());
		return details;
	}
	
	public static Customer convertToCustomer(Map<String,Object> request) {
		Customer customer = new Customer();
		String customerName = (String) request.get("customerName");
		customer.setCustomerName(customerName);
		//Date customerDob = (Date) request.get("customerDob");
		String customerDobText=(String) request.get("customerDob");
		LocalDate customerDobLocal=LocalDate.parse(customerDobText,formatter);
	    Date date=new Date(customerDobLocal.getYear(),customerDobLocal.getMonthValue(),customerDobLocal.getDayOfMonth());
		customer.setCustomerDob(date);
		String customerGender = (String) request.get("customerGender");
		customer.setCustomerGender(customerGender);
		String customerContact = (String) request.get("customerContact");
		customer.setCustomerContact(customerContact);
		String customerPan = (String) request.get("customerPan");
		customer.setCustomerPan(customerPan);
		String customerAadhar = (String) request.get("customerAadhar");
		customer.setCustomerAadhar(customerAadhar);
		return customer;
	}
	
	public static Address convertToAddress(Map<String,Object> request) {
		Address address = new Address();
		String addressLine = (String) request.get("addressLine");
		address.setAddressLine(addressLine);
		String addressCity = (String) request.get("city");
		address.setAddressCity(addressCity);
		String addressState = (String) request.get("state");
		address.setAddressState(addressState);
		String addressCountry = (String) request.get("country");
		address.setAddressCountry(addressCountry);
		String addressZipcode = (String) request.get("zipcode");
		address.setAddressZipcode(addressZipcode);
		return address;
	}
}
