package com.VirtualBank.demo;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import com.VirtualBank.demo.dao.AccountsDB;
import com.VirtualBank.demo.model.Account;


@SpringBootApplication
public class VirtualBankApplication {
	
	@Autowired
	AccountsDB accRepo;
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(VirtualBankApplication.class, args);
		
		
		
		
		
		Account acc = context.getBean(Account.class);
		acc.doSomething();
		
		
	}

}
