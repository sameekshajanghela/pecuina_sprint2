package com.capgemini.pecunia.accountmgmt.dto;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

public class AccountDetails {
    private String accountId;
    private String accountHolderId;
    private String accountBranchId;
    private String accountType;
    private String accountStatus;
    private Double accountBalance;
    private Double accountInterest;
    private Date lastUpdated;

    private String customerId;

    private String customerName;

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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
