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

import com.system.model.Test;
import com.system.service.TestService;

@RestController
@RequestMapping("/system")
@CrossOrigin("http://localhost:4200") 
public class TestController {
	@Autowired
	private TestService service;
	@GetMapping("/getTest/id={id}")
	private final Test test (@PathVariable int id) {
		return service.getTest(id);
	}
	@GetMapping("/getTests")
	private final List<Test> tests(){
		return service.getAllTests();
	}
	@PostMapping("/createTest")
	private final String test(@RequestBody Test test) {
		service.createTest(test);
		return "Successfully created test";
	}
	@GetMapping("/getUnreleasedExams")
	private final List<Test> unreleasedTest(){
		return service.getUnreleasedExams();
	}
	@GetMapping("/getReleasedExams")
	private final List<Test> releasedTest(){
		return service.getReleasedExams();
	}
	@GetMapping("/releaseExam/examId={examId}")
	private final void releaseExam(@PathVariable int examId) {
		service.releaseExam(examId);
	}
	@GetMapping("/unreleaseExam/examId={examId}")
	private final void unreleaseExam(@PathVariable int examId) {
		service.unreleaseExam(examId);
	}
}
