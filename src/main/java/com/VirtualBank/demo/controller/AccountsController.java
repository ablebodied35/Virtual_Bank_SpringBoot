package com.VirtualBank.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.VirtualBank.demo.model.PersonalInfo;
import com.VirtualBank.demo.model.Receipt;
import com.VirtualBank.demo.service.AccountOperations;

@RestController
@RequestMapping("/account")
public class AccountsController {
	
	@Autowired
	private AccountOperations accOp;
	
	
		
		
		@GetMapping(path="/getBalance", 
					produces="application/json")
		@ResponseBody
		public int balanceInquiry(HttpSession session) {
			
			return accOp.balanceInquiry(session);
		
		}
		
		@PutMapping(path="/makeDeposit/{deposit}",
					produces="application/json")
		@ResponseBody
		public String makeDeposit(@PathVariable("deposit") int deposit, HttpSession session) {
			
			 int ret = accOp.makeDeposit(deposit, session);
			 return returnString(ret);
		}
			
		@PutMapping(path="/makeWithdrawal/{withdrawal}",
					produces="application/json")
		@ResponseBody
		public String makeWithdraw(@PathVariable("withdrawal") int withdrawal, HttpSession session) {
			
			 int ret = accOp.makeWithdrawal(withdrawal, session);
			 return returnString(ret);
		}
			
		@PutMapping(path="/changeAccStatus/{decision}",
					produces="application/json")
		@ResponseBody
		public String changeAccStatus(@PathVariable("decision") int value, HttpSession session) {
			
			boolean decision;
			
			if(value == 0)
				decision = true;//Open account
			else if(value == 1)
				decision = false;//Close account
			else
				return "An error occurred";
			
			int ret = accOp.changeAccStatus(decision, session);
			
			return returnString(ret);
			
		}
		
		@DeleteMapping(path="/deleteAcc",
					   produces="application/json")
		@ResponseBody
		public String deleteAcc(HttpSession session) {
			
			int ret = accOp.deleteAcc(session);
			return returnString(ret);
			
		}
		
		
		@PostMapping(path="/createAcc",
					 produces="application/json",
					 consumes="application/json")
		@ResponseBody 
		public String createAcc(@RequestBody PersonalInfo info) {
			
			return accOp.createAcc(info);
			
			
		}
			
		private String returnString(int ret) {
				
			if(ret != -1) {
				return "Transaction Successful";
			}
			else {
				return "An error occurred";
			} 
		}
		
	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	

