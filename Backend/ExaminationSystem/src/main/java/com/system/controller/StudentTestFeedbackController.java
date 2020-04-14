package com.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.model.StudentTestFeedback;
import com.system.service.StudentTestFeedbackService;

@RestController
@RequestMapping("/system")
@CrossOrigin("http://localhost:4200")
public class StudentTestFeedbackController {
	@Autowired
	private StudentTestFeedbackService service;
	@PostMapping("/storeFeedback")
	private final String insertFeedback(@RequestBody List<StudentTestFeedback> feedback) {
		return service.saveFeedback(feedback);
	}
	@GetMapping("/retrieveFeedback/examId={examId}/userId={userId}")
	private final List<StudentTestFeedback> retrieveFeedback(@PathVariable int examId, @PathVariable int userId){
		return service.getFeedback(userId, examId);
	}
}
