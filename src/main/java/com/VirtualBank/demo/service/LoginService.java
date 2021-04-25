package com.VirtualBank.demo.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.VirtualBank.demo.dao.AccountsDB;
import com.VirtualBank.demo.dao.PersonalInfoDB;
import com.VirtualBank.demo.model.Account;
import com.VirtualBank.demo.model.SessionTicket;

@Service
public class LoginService {

	@Autowired
	AccountsDB accRepo;
	@Autowired
	PersonalInfoDB persInfoRepo;
	
	
	
	/*
	 * SERVICE METHOD: login
	 * 
	 * PARAMETERS:
	 * int : accNum(account number of user to verify if they have an account or not)
	 * 
	 * RETURNS:
	 * SessionTicket : ticket that will contain the persons first and last names along with the account balance
	 * 				   and boolean value to indicate if they passed or failed the login. True = pass, False = fail.
	 */
	public SessionTicket login(int accNum) 
	{	
		
		if(accRepo.existsById(accNum)) 
		{
			
			String first = persInfoRepo.findByfirstName(accNum);
			String last = persInfoRepo.findBylastName(accNum);
			int amount = accRepo.findByaccountBalance(accNum);
			boolean flag = true;
			Account acc = accRepo.findById(accNum);
			
			System.out.println(acc);
			
			System.out.println("First name: " + first);
			System.out.println("Last name: " + last);
			System.out.println("Balance available: $" +amount);
			
			return new SessionTicket(first,last,amount,accNum,flag,acc);
		}
		return new SessionTicket("","",-1,-1,false,null);
		
		
	}
	
	
	
	
	
}
