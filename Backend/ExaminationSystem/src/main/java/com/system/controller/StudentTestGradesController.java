package com.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.model.StudentTestGrades;
import com.system.service.StudentTestGradesService;

@RestController
@RequestMapping("/system")
@CrossOrigin("http://localhost:4200") 
public class StudentTestGradesController {
	@Autowired
	private StudentTestGradesService service;
	@GetMapping("/getStudents/examId={examId}")
	private List<StudentTestGrades> students(@PathVariable int examId){
		return service.getStudents(examId);
	}
	@GetMapping("/getGrade/examId={examId}/userId={userId}")
	private StudentTestGrades grade(@PathVariable int examId, @PathVariable int userId) {
		return service.getGrade(examId, userId);
	}
}
