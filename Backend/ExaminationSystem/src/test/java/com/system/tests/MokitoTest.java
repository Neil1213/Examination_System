package com.system.tests;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.system.dao.StudentTestRelationRepository;
import com.system.model.StudentTestRelation;
import com.system.service.StudentTestRelationService;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MokitoTest {
	@Autowired 
	StudentTestRelationService service;
	@MockBean
	StudentTestRelationRepository repository;
	@Test
	public void assignExamTest() {
		StudentTestRelation obj1 = new StudentTestRelation();
		StudentTestRelation obj2 = new StudentTestRelation();
		obj1.setId(1);obj1.setReleased(0);obj1.setTaken(0);obj1.setTestId(1);
		obj1.setStudentId(2);obj1.setStudentName("Neil1312");
		obj2.setId(2);obj2.setReleased(0);obj2.setTaken(1);obj1.setTestId(1);
		obj1.setStudentId(3);obj1.setStudentName("Sumeet");
		when(repository.save(obj1)).thenReturn(obj1);
		when(repository.save(obj2)).thenReturn(obj2);
		assertEquals("Successfully inserted data", service.insertData(obj1));
		assertEquals("Successfully inserted data", service.insertData(obj2));
	}
}
