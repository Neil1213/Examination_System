package com.system.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.autograder.AutoGrader;
import com.system.dao.StudentResponseRepository;
import com.system.model.StudentResponse;

@Service
public class StudentResponseService {
	@Autowired private StudentResponseRepository repo;
	@Autowired private AutoGrader grader;
	@Autowired private StudentTestRelationService STRservice;
	public final String storeResponse (List<StudentResponse> response) throws IOException, SQLException {
		repo.saveAll(response);
		int testId = response.get(0).getTestId();
		int studentId = response.get(0).getStudentId();
		STRservice.updateTaken(studentId, testId);
		STRservice.updateReleased(studentId, testId);
		grader.grader(response);
		return "Successfully response stored";
	}
	public final List<StudentResponse> responses (int studentId, int testId){
		ArrayList<StudentResponse> tempList = new ArrayList<StudentResponse>();
		repo.findAll().forEach((response)->{
			if(response.getStudentId() == studentId && response.getTestId() == testId) {
				tempList.add(response);
			}
		});
		List<StudentResponse> responses = tempList;
		return responses;
	}
}
