package com.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.model.StudentTestGrades;

@Repository
public interface StudentTestGradesRepository extends JpaRepository<StudentTestGrades, Integer> {

}
