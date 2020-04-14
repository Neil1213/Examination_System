package com.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.system.model.StudentTestRelation;

@Repository
public interface StudentTestRelationRepository extends JpaRepository<StudentTestRelation, Integer> {
	
}
