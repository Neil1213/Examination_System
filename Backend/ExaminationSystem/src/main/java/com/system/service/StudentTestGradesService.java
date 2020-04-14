package com.system.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.StudentTestGradesRepository;
import com.system.dao.StudentTestRelationRepository;
import com.system.model.StudentTestGrades;

@Service
public class StudentTestGradesService {
	@Autowired
	private StudentTestGradesRepository repo;
	@Autowired
	private StudentTestRelationRepository STRrepo;
	public final String insertGrades(StudentTestGrades grades) {
		repo.save(grades);
		return "Successfully saved grades";
	}
	public final StudentTestGrades retrieveGrades(int userId, int testId) {
		StudentTestGrades grades = new StudentTestGrades();
		repo.findAll().forEach((grade) -> {
			if(grade.getUserId() == userId && grade.getTestId() == testId) {
				grades.setId(grade.getId());
				grades.setTestId(grade.getTestId());
				grades.setUserId(grade.getUserId());
				grades.setGrade(grade.getGrade());
			}
		});
		return grades;
	}
	public final StudentTestGrades getGrade(int examId, int studentId) {
		ArrayList<StudentTestGrades> buf = new ArrayList<StudentTestGrades>();
		repo.findAll().forEach((data)->{
			if(data.getTestId()==examId && data.getUserId()==studentId) {
				buf.add(data);
			}
		});
		StudentTestGrades grade = buf.get(0);
		return grade;
	}
	public final List<StudentTestGrades> getStudents (int examId){
		ArrayList<Integer> studentIds = new ArrayList<Integer>();
		STRrepo.findAll().forEach((data)->{
			if((int)data.getTestId()==examId && data.getReleased()==0 && data.getTaken()==1) {
				studentIds.add(data.getStudentId());
			}
		});
		ArrayList<StudentTestGrades> grades = new ArrayList<StudentTestGrades>();
		for(int id:studentIds) {
			repo.findAll().forEach((data)->{
				if((int)data.getTestId()==examId && (int)data.getUserId()==id) {
					grades.add(data);
				}
			});
		}
		List<StudentTestGrades> rtnData = grades;
		System.out.println(rtnData);
		return rtnData;
	}
}
