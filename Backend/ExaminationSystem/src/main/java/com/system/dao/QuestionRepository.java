package com.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.system.model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer>{

}
