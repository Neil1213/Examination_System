package com.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.model.StudentTestFeedback;

@Repository
public interface StudentTestFeedbackRepository extends JpaRepository<StudentTestFeedback, Integer> {

}
