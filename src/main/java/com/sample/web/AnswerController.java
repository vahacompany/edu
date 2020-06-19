package com.sample.web;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sample.domain.Answer;
import com.sample.domain.AnswerRepository;
import com.sample.domain.Question;
import com.sample.domain.QuestionRepository;
import com.sample.domain.User;

@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {
	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@PostMapping("")
	public String create(@PathVariable Long questionId, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}

		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question= new Question();
		Optional<Question> questionOpt = questionRepository.findById(questionId);
		if (questionOpt.isPresent()) {
			question = questionOpt.get();
		}
		Answer answer = new Answer(loginUser, question, contents);
		answerRepository.save(answer);
		return String.format("redirect:/questions/%d", questionId);
	}
}