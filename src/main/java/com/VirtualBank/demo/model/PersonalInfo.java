package com.VirtualBank.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Entity
@Component
public class PersonalInfo {
	
	@Id
	private int accountNum;
	
	private String accType;
	private String city;
	private String firstName;
	private String lastName;
	private String socialSecurity;
	private String state;
	private String streetName;
	private String zipcode;
	
	
	
	@Override
	public String toString() {
		return "PersonalInfo [accountNum=" + accountNum + ", accType=" + accType + ", socialSecurity=" + socialSecurity
				+ ", city=" + city + ", firstName=" + firstName + ", lastName=" + lastName + ", state=" + state
				+ ", streetName=" + streetName + ", zipcode=" + zipcode + "]";
	}
	
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	
	
	
	public int getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
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
	public String getSSN() {
		return socialSecurity;
	}
	public void setSSN(String sSN) {
		socialSecurity = sSN;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	
	
}
