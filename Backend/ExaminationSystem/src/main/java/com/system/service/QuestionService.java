package com.system.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.dao.QuestionRepository;
import com.system.model.Question;

@Service
public class QuestionService {
	@Autowired
	QuestionRepository repo;
	@Autowired private LoggingService logger;
	private final String className = this.getClass().getName().toString();
	public final void insertQuestion(Question question) throws SQLException, IOException {
		logger.log("info", "Saving question in database...", this.className);
		repo.save(question);
		logger.log("info", "Successfully saved question in database", this.className);
	}
	public final List<Question> showQuestionBank(){
		return repo.findAll();
	}
	public final Question getQuestion(int id) {
		return repo.findById(id).get();
	}
}
