package com.VirtualBank.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Account {
	
	@Id
	private int accountNum;
	
	private int accountBalance;
	private String accountStatus;
	private String accountType;
	
	
	
	public Account() {
		
	}
	
	public Account(int accountNum, int accountBalance, String accountStatus, String accountType) {
		
		this.accountNum = accountNum;
		this.accountBalance = accountBalance;
		this.accountStatus = accountStatus;
		this.accountType = accountType;
	}


	public int getAccountNum() {
		return accountNum;
	}


	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	
	public void doSomething() {
		System.out.println("Hi...");
	}

	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public int getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	@Override
	public String toString() {
		return "Account [accountNum=" + accountNum + ", accountBalance=" + accountBalance + ", accountStatus="
				+ accountStatus + ", accountType=" + accountType + "]";
	}
	
}
