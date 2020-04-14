package com.system.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.model.StudentTestRelation;
import com.system.service.StudentTestRelationService;

@RestController
@RequestMapping("/system")
@CrossOrigin("http://localhost:4200")
public class StudentTestRelationController {
	@Autowired
	private StudentTestRelationService service;
	@PostMapping("/testTaken")
	private final String testTaken(@RequestBody Map<String, Object> data) {
		final int studentId = (int)data.get("studentId");
		final int testId = (int)data.get("testId");
		return service.updateTaken(studentId, testId);
	}
	@PostMapping("/testReleased")
	private final String testReleased(@RequestBody Map<String, Object> data) {
		final int studentId = (int)data.get("studentId");
		final int testId = (int)data.get("testId");
		return service.updateReleased(studentId, testId);
	}
	@PostMapping("/assignExam")
	private final String assignExam(@RequestBody StudentTestRelation obj) {
		return service.insertData(obj);
	}
	@GetMapping("/getAvailableExams/username={username}")
	private final List<Map> getAvailableExams(@PathVariable String username){
		return service.getAvailableTests(username);
	}
	@GetMapping("/getPastExams/username={username}")
	private final List<Map> getPastExams(@PathVariable String username){
		return service.getPastTests(username);
	}
}
