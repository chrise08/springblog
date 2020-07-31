package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class LoginController {
	
	@GetMapping("/login")
	public String viewLoginForm() {
		return "login";
	}
	
	@PostMapping("/profile")
	public String viewLoginInfo(@RequestParam(name = "username") String username,
	                            @RequestParam(name = "password") String password,
	                            Model model) {
		// mock database
		ArrayList<String> ads = new ArrayList<>();
		ads.add("Ad 1");
		ads.add("Ad 2");
		ads.add("Ad 3");
		
		model.addAttribute("username", username);
		model.addAttribute("password", password);
		model.addAttribute("ads", ads);
		return "profile";
	}
}
