package com.capgemini.pecunia.accountmgmt.dao;
import com.capgemini.pecunia.accountmgmt.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountDao extends JpaRepository<Account, String> {

}
