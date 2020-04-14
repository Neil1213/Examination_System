package com.system.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.TestRepository;
import com.system.model.StudentTestRelation;
import com.system.model.Test;
import com.system.model.User;

@Service
public class TestService {
	@Autowired
	private TestRepository repo;
	@Autowired
	private UserService userService;
	@Autowired
	private StudentTestRelationService studentTestService;
	@Autowired
	private EmailService emailService;
	public final void createTest(Test test) {
		repo.save(test);
	}
	public final Test getTest(int id) {
		return repo.findById(id).get();
	}
	public final List<Test> getAllTests(){
		return repo.findAll();
	}
	public final List<Test> getUnreleasedExams(){
		List<Test> bufList = repo.findAll();
		ArrayList<Test> bufRtnData = new ArrayList<Test>();
		bufList.forEach((test)->{
			if(test.getReleased() == 0) {
				bufRtnData.add(test);
			}
		});
		List<Test> returnData = bufRtnData;
		return returnData;
	}
	public final List<Test> getReleasedExams(){
		List<Test> bufList = repo.findAll();
		ArrayList<Test> bufRtnData = new ArrayList<Test>();
		bufList.forEach((test)->{
			if(test.getReleased()==1) {
				bufRtnData.add(test);
			}
		});
		List<Test> returnData = bufRtnData;
		return returnData;
	}
	public final void releaseExam(int examId) {
		repo.findById(examId).get().setReleased(1);
		repo.flush();
		List<User> students = userService.getUsers("ROLE_STUDENT");
		User instructor = userService.getUsers("ROLE_INSTRUCTOR").get(0);
		students.forEach((student)->{
			int id = student.getId();
			String name = student.getUsername();
			StudentTestRelation obj = new StudentTestRelation();
			obj.setReleased(0);obj.setTaken(0);obj.setTestId(examId);
			obj.setStudentId(id);obj.setStudentName(name);
			studentTestService.insertData(obj);
			try {
				emailService.sendEmail(student.getEmail(), instructor.getEmail(), "Regarding Exam", "Hi, \n\nYour exam is ready to take. Please take the exam before due date.\n\nThanks,\n"+instructor.getUsername());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		
	}
	public final void unreleaseExam(int examId) {
		repo.findById(examId).get().setReleased(0);
		repo.flush();
	}
}
