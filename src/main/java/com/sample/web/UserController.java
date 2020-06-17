package com.sample.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.sample.domain.User;
import com.sample.domain.UserRepository;

@Controller
public class UserController {
	private List<User> users = new ArrayList<User>();

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/create")
	public String create(User user) {

		System.out.println("user : " + user);
		users.add(user);
		userRepository.save(user);
		return "redirect:/list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "list";
	}
}
