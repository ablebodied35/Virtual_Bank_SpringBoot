package com.VirtualBank.demo.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.VirtualBank.demo.model.SessionTicket;
import com.VirtualBank.demo.service.LoginService;


@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@GetMapping(path="/{accNum}",produces="application/json")
	@ResponseBody
	public SessionTicket login(@PathVariable("accNum") int accNum, HttpSession session) {
	
		//System.out.println("Its starting..." + accNum);
		SessionTicket ticket = loginService.login(accNum);
		//System.out.println("Its going..." + accNum);
		session.setAttribute("ticket", ticket);
		return ticket;
				
	}
}






