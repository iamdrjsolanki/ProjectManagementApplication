package com.iamdrjsolanki.pma.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.iamdrjsolanki.pma.entities.UserAccount;
import com.iamdrjsolanki.pma.services.UserAccountService;

@Controller
public class SecurityController {
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	@Autowired
	UserAccountService userServ;
	
	@GetMapping("/register") 
	public String register(Model model) {
		UserAccount user = new UserAccount();
		model.addAttribute("user", user);
		return "security/register";
	}
	
	@PostMapping("/register/save")
	public String saveUser(UserAccount user, Model model) {
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		user.setEnabled(true);
		userServ.saveUser(user);
		return "redirect:/";
	}

}
