package com.system.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student_test_grades")
public class StudentTestGrades {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "grade")
	private double grade;
	@Column(name = "test_id")
	private int testId;
	@Column(name = "user_id")
	private int userId;
	@ManyToOne
	@JoinColumn(name="user_id", referencedColumnName = "id", updatable = false, insertable = false)
	private User user;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getGrade() {
		return grade;
	}
	public void setGrade(double grade) {
		this.grade = grade;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public int getUserId() {
		return userId;
	}
	public User getUser() {
		return user;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
