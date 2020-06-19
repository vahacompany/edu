package com.sample.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.domain.User;
import com.sample.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	private List<User> users = new ArrayList<User>();

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/loginForm")
	public String loginFrom() {
		return "/user/login";
	}
	
	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userRepository.findByUserId(userId);
		if (user == null) {
			return "redirect:/users/loginForm";
		}
		
		if (!user.matchPassword(password)) {
			System.out.println("Login Failure!");
			return "redirect:/users/loginForm";
		}
		
		System.out.println("Login Success!!!");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);
		return "redirect:/";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {

		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);
		System.out.println("logout Success!!!");

		return "redirect:/";
	}
	
	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}
	@GetMapping("{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		if (HttpSessionUtils.isLoginUser(session)) {
			return "/user/updateForm";
		}
		
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		if (!sessionUser.matchId(id)) {
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		}
		
		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		return "/user/updateForm";
	}

	@PostMapping("")
	public String create(User user) {

		System.out.println("user : " + user);
		users.add(user);
		userRepository.save(user);
		return "redirect:/users";
	}
	@PostMapping("/{id}")
	public String update(@PathVariable Long id, User updatedUser, HttpSession session) {
		if (HttpSessionUtils.isLoginUser(session)) {
			return "/user/updateForm";
		}
		
		User sessionUser = HttpSessionUtils.getUserFromSession(session);
		if (!sessionUser.matchId(id)) {
			throw new IllegalStateException("자신의 정보만 수정할 수 있습니다.");
		}
		
		User user = userRepository.findById(id).get();
		user.update(updatedUser);
		userRepository.save(user);
		return "redirect:/users";
	}

	@GetMapping("")
	public String list(Model model) {
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}
}
