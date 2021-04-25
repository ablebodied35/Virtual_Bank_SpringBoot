package com.VirtualBank.demo.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class SessionTicket {
	
	String firstName;
	String lastName;
	int accBalance;
	int accNum;
	boolean flag;
	Account account;
	


	public SessionTicket(String firstName, String lastName, int accBalance, int accNum,boolean flag, Account acc) {
		
		this.firstName=firstName;
		this.lastName=lastName;
		this.accBalance=accBalance;
		this.accNum=accNum;
		this.flag=flag;
		this.account = acc;
		
	}
	
	public boolean getFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAccBalance() {
		return accBalance;
	}
	public void setAccBalance(int accBalance) {
		this.accBalance = accBalance;
	}

	public int getAccNum() {
		return accNum;
	}

	public void setAccNum(int accNum) {
		this.accNum = accNum;
	}

	@Override
	public String toString() {
		return "SessionTicket [firstName=" + firstName + ", lastName=" + lastName + ", accBalance=" + accBalance
				+ ", accNum=" + accNum + ", flag=" + flag + ", account=" + account + "]";
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	
}
