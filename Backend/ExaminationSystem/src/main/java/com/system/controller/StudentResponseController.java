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

import com.system.model.StudentResponse;
import com.system.service.StudentResponseService;

@RestController
@RequestMapping("/system")
@CrossOrigin("http://localhost:4200")
public class StudentResponseController {
	@Autowired
	private StudentResponseService service;
	@PostMapping("/submitExam")
	private final String submitExam(@RequestBody List<StudentResponse> responses) throws IOException, SQLException{
		return service.storeResponse(responses);
	}
	@GetMapping("/getStudentResponse/examId={examId}/userId={userId}")
	private final List<StudentResponse> userResponse(@PathVariable int examId, @PathVariable int userId ){
		return service.responses(userId, examId);
	}
}
