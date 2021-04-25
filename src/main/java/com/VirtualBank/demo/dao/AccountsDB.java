package com.VirtualBank.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.VirtualBank.demo.model.Account;

@Component
public interface AccountsDB extends JpaRepository<Account, Integer>{

	@Query("SELECT accountBalance FROM Account WHERE accountNum = ?1")
	int findByaccountBalance(int accNum);
	
	Account findById(int accNum);
	
	@Transactional
	@Modifying
	@Query("UPDATE Account SET accountBalance = ?1 WHERE accountNum = ?2")
	void changeAccBalance(int deposit, int accNum);
	
	@Transactional
	@Modifying
	@Query("UPDATE Account SET accountStatus = ?1 WHERE accountNum = ?2")
	void setAccStatus(String status, int accNum);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM Account WHERE accountNum=?1")
	void deleteAcc(int accNum);
	
	
}
