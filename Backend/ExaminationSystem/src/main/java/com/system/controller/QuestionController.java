package com.system.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.model.Question;
import com.system.service.QuestionService;

@RestController
@RequestMapping("/system")
@CrossOrigin("http://localhost:4200")
public class QuestionController {
	@Autowired
	private QuestionService service;
	@PostMapping("/insertQuestion")
	private final String question(@RequestBody Question question) throws SQLException, IOException {
		service.insertQuestion(question);
		return "Successfully inserted question!";
	}
	@GetMapping("/showQuestionBank")
	private final List<Question> question(){
		return service.showQuestionBank();
	}
	@GetMapping("/getQuestion/id={id}")
	private final Question question(@PathVariable int id) {
		return service.getQuestion(id);
	}
}
