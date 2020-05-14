package com.capgemini.pecunia.accountmgmt.entities;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table(name = "account")
public class Account {
	
	@Id
	@Min(value=12)
	private String accountId;
	private String accountHolderId;
	@Min(value=4)
	private String accountBranchId;
	private String accountType;
	private String accountStatus;
	private Double accountBalance;
	private Double accountInterest;
	private Date lastUpdated;

	@OneToOne
	private Customer customer;

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@OneToOne
	private Address address;


	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public String getAccountHolderId() {
		return accountHolderId;
	}
	public void setAccountHolderId(String accountHolderId) {
		this.accountHolderId = accountHolderId;
	}
	public String getAccountBranchId() {
		return accountBranchId;
	}
	public void setAccountBranchId(String accountBranchId) {
		this.accountBranchId = accountBranchId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public Double getAccountInterest() {
		return accountInterest;
	}
	public void setAccountInterest(Double accountInterest) {
		this.accountInterest = accountInterest;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}


    /**
     * override hashcode
     * @return hashcode
     */
	@Override
	public int hashCode() {     
    return accountId.hashCode();
    }
	
	

    /**
     *  check equality of account object
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !(object instanceof Account)) return false;
        Account account = (Account) object;
        return this.accountId.equals(account.getAccountId());
    }

    /**
     *
     * @return combine details of account
     */
    @Override
   	public String toString() {
   		return "Account [accountId=" + accountId + ", accountHolderId=" + accountHolderId + ", accountBranchId="
   				+ accountBranchId + ", accountType=" + accountType + ", accountStatus=" + accountStatus
   				+ ", accountBalance=" + accountBalance + ", accountInterest=" + accountInterest + ", lastUpdated="
   				+ lastUpdated + "]";
   	}

}
