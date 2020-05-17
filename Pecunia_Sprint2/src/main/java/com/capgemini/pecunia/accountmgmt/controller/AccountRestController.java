package com.capgemini.pecunia.accountmgmt.controller;

import java.util.List;
import java.util.Map;

import com.capgemini.pecunia.accountmgmt.dto.AccountDetails;
import com.capgemini.pecunia.accountmgmt.exceptions.AccountNotCreatedException;
import com.capgemini.pecunia.accountmgmt.util.AccountUtil;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Size;

import com.capgemini.pecunia.accountmgmt.entities.Account;
import com.capgemini.pecunia.accountmgmt.entities.Address;
import com.capgemini.pecunia.accountmgmt.entities.Customer;
import com.capgemini.pecunia.accountmgmt.exceptions.AccountNotFoundException;
import com.capgemini.pecunia.accountmgmt.service.IAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/***
 * @author Sameeksha Janghela
 */
@RequestMapping("/accounts")
@RestController
@Validated
public class AccountRestController {
    private static final Logger Log = LoggerFactory.getLogger(AccountRestController.class);

    @Autowired
    private IAccountService accountService;

    /**
     * add account object
     *
     * @return response to server
     */
    @PostMapping("/add")
    public ResponseEntity<String> addAccount(@RequestBody Map<String, Object> request) {
        Account account = AccountUtil.convertToAccount(request);
        Customer customer = AccountUtil.convertToCustomer(request);
        Address address = AccountUtil.convertToAddress(request);
        String msg = accountService.addAccount(customer, address, account);
        ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
        return response;
    }

    /**
     * update customer name by account id
     *
     * @param accountId
     * @return response to server
     */
    @PutMapping("/{accountId}/changename")
    public ResponseEntity<String> updateCustomerName(@RequestBody Map<String, String> request,
                                                     @PathVariable("accountId") String accountId) {
        Account account = accountService.findByAccountById(accountId);
        Customer customer = account.getCustomer();
        String name = request.get("customerName");
        customer.setCustomerName(name);
        String msg = accountService.updateCustomerName(account, customer);
        ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
        return response;

    }

    /**
     * update customer contact by account id
     *
     * @param accountId
     * @return response to server
     */
    @PutMapping("/{accountId}/updatecontact")
    public ResponseEntity<String> updateCustomerContact(@RequestBody Map<String, String> request,
                                                        @PathVariable("accountId") String accountId) {
        Account account = accountService.findByAccountById(accountId);
        Customer customer = account.getCustomer();
        String newContact = request.get("customerContact");
        customer.setCustomerContact(newContact);
        String msg = accountService.updateCustomerContact(account, customer);
        ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
        return response;
    }

    /**
     * update address by account id
     *
     * @param accountId
     * @return response to server
     */
    @PutMapping("/{accountId}/updateaddress")
    public ResponseEntity<String> updateCustomerAddress(@RequestBody Map<String, Object> request,
                                                        @PathVariable("accountId") String accountId) {
        Account account = accountService.findByAccountById(accountId);
        Address address = AccountUtil.convertToAddress(request);
        String msg = accountService.updateCustomerAddress(account, address);
        ResponseEntity<String> response = new ResponseEntity<String>(msg, HttpStatus.OK);
        return response;

    }

    /**
     * fetch account object by account id
     *
     * @param accountId
     * @return account and response to server
     */
    @GetMapping("/{accountId}/showdetails")
    public ResponseEntity<AccountDetails> showAccountDetailsById(
            @PathVariable("accountId") @Size(min = 12, max = 12) String accountId) {
        Account account = accountService.showAccountDetails(accountId);
        AccountDetails accountDetails = AccountUtil.convertToDetails(account);
        ResponseEntity<AccountDetails> response = new ResponseEntity<>(accountDetails, HttpStatus.OK);
        return response;
    }

    /**
     * fetch all the accounts from database
     *
     * @return account list and response to server
     */
    @GetMapping()
    public ResponseEntity<List<Account>> fetchAllAccounts() {
        List<Account> account = accountService.fetchAllAccounts();
        ResponseEntity<List<Account>> response = new ResponseEntity<>(account, HttpStatus.OK);
        return response;
    }

    /**
     * set account status to be closed
     *
     * @return
     */
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAccount(@RequestBody Map<String, Object> request) {
        String accountId = (String) request.get("accountId");
        boolean isTrue = accountService.deleteAccount(accountId);
        if (!isTrue) {
            throw new AccountNotCreatedException("could not" + " create account");
        }
        String msg = "Account Closed Successfully";
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.OK);
        return response;
    }

}
