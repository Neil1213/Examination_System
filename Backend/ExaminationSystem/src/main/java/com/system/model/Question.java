package com.system.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "questions")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private int id;
	@Column(name = "category")
	private String category;
	@Column(name = "difficulty")
	private String difficulty;
	@Column(name = "method_name")
	private String methodName;
	@Column(name = "question")
	private String question;
	@Column(name = "restrictions")
	private String restrictions;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "question_id", referencedColumnName = "question_id")
	private List<Testcases> testcases;
	@OneToMany
	@JoinColumn(name = "question_id", referencedColumnName = "question_id", updatable = false, insertable = false)
	@JsonIgnore
	private List<TestQuestionRelation> tests;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getRestrictions() {
		return restrictions;
	}
	public void setRestrictions(String restrictions) {
		this.restrictions = restrictions;
	}
	public List<Testcases> getTestcases() {
		return testcases;
	}
	public void setTestcases(List<Testcases> testcases) {
		this.testcases = testcases;
	}
	@Override
	public String toString() {
		return "Question [id=" + id + ", category=" + category + ", difficulty=" + difficulty + ", methodName="
				+ methodName + ", question=" + question + ", restrictions=" + restrictions + ", testcases=" + testcases
				+ ", tests=" + tests + "]";
	}
	
}
