package com.VirtualBank.demo.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.VirtualBank.demo.dao.AccountsDB;
import com.VirtualBank.demo.dao.PersonalInfoDB;
import com.VirtualBank.demo.model.Account;
import com.VirtualBank.demo.model.PersonalInfo;
import com.VirtualBank.demo.model.SessionTicket;

@Component
@Service
public class AccountOperations {

	@Autowired
	private AccountsDB accRepo;
	@Autowired
	private PersonalInfoDB persInfoRepo;
	
	//Value range for new accounts
	private int max = 999999;
	private int min = 100000;
	
	
	
	/*
	 * SERVICE METHOD: balanceInquiry
	 * Used to check the balance of the account
	 * 
	 * PARAMETERS:
	 * session : HttpSession object that stores our SessionTicket holding information about the account
	 * 
	 * RETURNS:
	 * int : returns either the balance of the account or -1 indicating error 
	 */
	public int balanceInquiry(HttpSession session) {
		
		SessionTicket ticket = getTicket(session);
		if(ticket.getFlag()) {
			
			return ticket.getAccBalance();
		}
		else
			return -1;
		
	
	}
	
	
	/*
	 * SERVICE METHOD: makeDeposit 
	 * Used to make an account deposit
	 * 
	 * PARAMETERS:
	 * deposit : int value holding the amount to deposit into account 
	 * session : HttpSession object that stores our SessionTicket holding information about the account 
	 * 
	 * RETURNS:
	 * int : either the value of the new account balance after depositing or -1 to indicate error 
	 */
	public int makeDeposit(int deposit, HttpSession session) {
		SessionTicket ticket = getTicket(session);
		
		if(ticket.getFlag() 
		&& deposit > 0 
		&& isAccOpen(ticket)) {
			int balance = ticket.getAccBalance();
			int sum = balance + deposit;
			accRepo.changeAccBalance(sum, ticket.getAccNum());
			ticket.setAccBalance(sum);
			return sum;
		}
		else
			return -1;
	}
	
	/*
	 * SERVICE METHOD: makeWithdrawal 
	 * Used to make a withdrawal from the account
	 * 
	 * PARAMETERS:
	 * withdrawal : int that holds the amount the user wants to withdraw from the account
	 * session : HttpSession object that stores the SessionTicket object which holds information about the account
	 * 
	 * RETURNS:
	 * int : returns either the new account balance or -1 to indicate error 
	 */
	public int makeWithdrawal(int withdrawal, HttpSession session) {
		SessionTicket ticket = getTicket(session);
		
		if(ticket.getFlag() 
		&& validateWithdrawSum(withdrawal,ticket)
		&& isAccOpen(ticket)) 
		{
			int balance = ticket.getAccBalance();
			int difference = balance - withdrawal;
			accRepo.changeAccBalance(difference, ticket.getAccNum());
			ticket.setAccBalance(difference);
			return difference;
		}
		else
			return -1;
		
		
	}
	
	/*
	 * SERVICE METHOD: changeAccStatus 
	 * Used to change the Accounts status between open and closed
	 * 
	 * PARAMETERS:
	 * decision : boolean value on which deepends if account will be open or closed. True means open while False means close
	 * session : HttpSession object that hold SessionTicket which holds information about the account
	 * 
	 * RETURNS:
	 * int : 1 to indicate success. -1 to indicate error 
	 */
	public int changeAccStatus(boolean decision, HttpSession session) {
		
		SessionTicket ticket = getTicket(session);
		if(ticket.getFlag()) {
			if(decision) {
				accRepo.setAccStatus("O", ticket.getAccNum());
				ticket.getAccount().setAccountStatus("O");
			}
			else {
				accRepo.setAccStatus("C", ticket.getAccNum());	
				ticket.getAccount().setAccountStatus("C");
			}
			return 1;
		}
		else {
			return -1;
		}
	}
	
	/*
	 * SERVICE METHOD: createAcc 
	 * Used to create a new account
	 * 
	 * PARAMETERS:
	 * info : PersonalInfo object which holds sensitive information about the new user
	 * 
	 * RETURNS:
	 * String : returns Account information such as account number, balance, status and type  
	 */
	public String createAcc(PersonalInfo info) {
		int accNum;
		Account newAcc;
		while(true) {
			accNum = getRandomNumber(min,max);
			if(!accRepo.existsById(accNum)) {
				info.setAccountNum(accNum);
				break;
			}
		}
		newAcc = new Account(accNum,0,"O",info.getAccType());
		accRepo.save(newAcc);
		persInfoRepo.save(info);
		return newAcc.toString();
	
	}
	
	/*
	 * SERVICE METHOD: deleteAcc 
	 * Used to delete an existing account
	 * 
	 * PARAMETERS:
	 * session : HttpSession object that holds SessionTicket which holds information about the account
	 * 
	 * RETURNS:
	 * int : 1 to indicate success, -1 to indicate error 
	 */
	public int deleteAcc(HttpSession session) {
		
		SessionTicket ticket = getTicket(session);
		if(ticket.getFlag()) {
			accRepo.deleteAcc(ticket.getAccNum());
			ticket = null;
			return 1;
		}
		else {
			return -1;
		}
		
	}
	
	
	/*
	 * SERVICE METHOD: getRandomNumber 
	 * Used to generate a new account number
	 * PARAMETERS:
	 * min : int value holding the lower bound for account number generation which is 100000 
	 * max : int value holding the upper bound for account number generation which is 999999
	 * 
	 * RETURNS:
	 * int : returns the 6 digit random number generated over the range [min,max] 
	 */
	private int getRandomNumber(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	/*
	 * SERVICE METHOD: validateWithdrawSum 
	 * Used to validate whether a sum that a user is attempting to withdraw is valid or not
	 * 
	 * PARAMETERS:
	 * sum : int value holding the sum the user wishes to withdraw
	 * ticket : SessionTicket object which holds information about the account
	 * 
	 * RETURNS:
	 * boolean : True if the sum is an appropriate amount, positive and less than the current account balance
	 * 			 False if it is not an appropriate sum, negative or greater than current account balance  
	 */
	private boolean validateWithdrawSum(int sum, SessionTicket ticket) {
		
		if(sum > 0 && sum < ticket.getAccBalance()) {
			return true;
		}
		return false;
		
	}
	
	/*
	 * SERVICE METHOD: isAccOpen 
	 * Used to see if the account is open or not. If not no transaction may be done without first opening the account
	 * 
	 * PARAMETERS:
	 * ticket : SessionTicket object which holds information about the account
	 * 
	 * RETURNS:
	 * boolean : True if the account is open 
	 * 			 False if the account is closed 
	 */
	private boolean isAccOpen(SessionTicket ticket) {
		if (ticket.getAccount().getAccountStatus().equals("O"))
			return true;
		return false;
	}
	
	
	/*
	 * SERVICE METHOD: getTicket 
	 * Used to make code more readable for future reference. Simply returns a SessionTicket object but will
	 * allow the reading of code to be better. You will read getTicket and understand whats happening instead
	 * of what is in this method. Albeit, it is not difficult but I am trying to develop a habit of creating more
	 * readable code for team based projects.
	 * 
	 * PARAMETERS:
	 * session : HttpSession object which holds SessonTicket which holds information about the account
	 * 
	 * RETURNS:
	 * SessionTicket : SessionTicket object which holds information about the account 
	 */
	private SessionTicket getTicket(HttpSession session) {
		return (SessionTicket) session.getAttribute("ticket");
	}
	
	
	
}
