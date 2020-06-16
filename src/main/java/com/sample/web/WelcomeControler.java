package com.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeControler {

	@GetMapping("/helloworld")
	public String welcome(String name, int age, Model model) {
		
		System.out.println("name :"+name +"AGE :"+age);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "welcome";
	}
}
