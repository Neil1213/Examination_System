package com.system.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "test_question_relation")
public class TestQuestionRelation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	@Column(name = "id")
	private int id;
	@Column(name = "points")
	private int points;
	@Column(name = "question_id")
	private int questionId;
	@ManyToOne
	@JoinColumn(name="question_id", referencedColumnName = "question_id", updatable = false, insertable = false)
	private Question question;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPoints() {
		return points;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	
	public Question getQuestion() {
		return question;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	@Override
	public String toString() {
		return "TestQuestionRelation [id=" + id + ", points=" + points + ", questionId=" + questionId + "]";
	}
	
}
