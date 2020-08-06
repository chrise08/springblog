package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiceController {
	@GetMapping("/dice-roll")
	public String viewRollDice(){
		return "dice-roll";
	}
	
	@GetMapping("/dice-roll/{n}")
	public String viewResults(@PathVariable int n, Model model){
		
		int diceRoll = (int) (Math.random() * 6) + 1;
		String message;
		
		if(diceRoll == n){
			message = "Great job! You guessed the roll number!";
		}else{
			message = "Sorry, not quite! Try again!";
		}
		
		model.addAttribute("n", n);
		model.addAttribute("message", message);
		model.addAttribute("diceRoll", diceRoll);
		
		return "dice-results";
	}
}