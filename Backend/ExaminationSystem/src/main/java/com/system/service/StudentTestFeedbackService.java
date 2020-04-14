package com.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.StudentTestFeedbackRepository;
import com.system.model.StudentTestFeedback;

@Service
public class StudentTestFeedbackService {
	@Autowired
	private StudentTestFeedbackRepository repo;
	public final String saveFeedback(List<StudentTestFeedback> feedback) {
		repo.saveAll(feedback);
		return "Successfully inserted feedback";
	}
	public final List<StudentTestFeedback> getFeedback(int studentId, int testId){
		ArrayList<StudentTestFeedback> tempObj = new ArrayList<StudentTestFeedback>();
		repo.findAll().forEach((obj) -> {
			if(obj.getTestId() == testId && obj.getStudentId() == studentId) {
				tempObj.add(obj);
			}
		});
		List<StudentTestFeedback> feedback = tempObj;
		return feedback;
	}
}
