package com.VirtualBank.demo.model;

import org.springframework.stereotype.Component;

@Component
public class Receipt {

	private String firstName;
	private String lastName;
	private String typeOfTransaction;
	private String preAmount;
	private String transactionAmount;
	private String postAmount;
	
	
	
	@Override
	public String toString() {
		return "Receipt [firstName=" + firstName + ", lastName=" + lastName + ", typeOfTransaction=" + typeOfTransaction
				+ ", preAmount=" + preAmount + ", transactionAmount=" + transactionAmount + ", postAmount=" + postAmount
				+ "]";
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
	public String getTypeOfTransaction() {
		return typeOfTransaction;
	}
	public void setTypeOfTransaction(String typeOfTransaction) {
		this.typeOfTransaction = typeOfTransaction;
	}
	public String getPreAmount() {
		return preAmount;
	}
	public void setPreAmount(String preAmount) {
		this.preAmount = preAmount;
	}
	public String getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public String getPostAmount() {
		return postAmount;
	}
	public void setPostAmount(String postAmount) {
		this.postAmount = postAmount;
	}
	
	
	
}
