package com.system.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.StudentTestRelationRepository;
import com.system.model.StudentTestRelation;
import com.system.model.Test;

@Service
public class StudentTestRelationService {
	@Autowired
	private StudentTestRelationRepository repo;
	@Autowired
	private TestService testService;
	public final String insertData(StudentTestRelation obj) {
		repo.save(obj);
		return "Successfully inserted data";
	}
	public final String updateReleased(int studentId, int testId) {
		repo.findAll().forEach((obj)->{
			if(obj.getTestId() == testId && obj.getStudentId()==studentId) {
				obj.setReleased(1);
			}
		});
		repo.flush();
		return "Successfully updated";
	}
	public final String updateTaken(int studentId, int testId) {
		repo.findAll().forEach((obj)->{
			if(obj.getTestId() == testId && obj.getStudentId()==studentId) {
				obj.setTaken(1);
			}
		});
		repo.flush();
		return "Successfully updated";
	}
	public final List<Map> getAvailableTests(String username){
		ArrayList<Map> bufObj = new ArrayList<Map>();
		repo.findAll().forEach((obj)->{
			Map buf = new LinkedHashMap(3);
			if(obj.getStudentName().equals(username) && obj.getTaken()==0) {
				Test test =  testService.getTest(obj.getTestId());
				buf.put("name", test.getName());
				buf.put("id", test.getId());
				buf.put("points", test.getPoints());
				bufObj.add(buf);
			}
		});
		List<Map> rtnObj = bufObj;
		return rtnObj;
	}
	public final List<Map> getPastTests(String username){
		ArrayList<Map> bufObj = new ArrayList<Map>();
		repo.findAll().forEach((obj)->{
			Map buf = new LinkedHashMap(3);
			if(obj.getStudentName().equals(username) && obj.getTaken()==1 && obj.getReleased()==1) {
				Test test =  testService.getTest(obj.getTestId());
				buf.put("name", test.getName());
				buf.put("id", test.getId());
				buf.put("points", test.getPoints());
				bufObj.add(buf);
			}
		});
		List<Map> rtnObj = bufObj;
		return rtnObj;
	}
}
