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

@Entity
@Table(name = "tests")
public class Test {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "points")
	private int points;
	@Column(name = "released")
	private int released;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "test_id", referencedColumnName = "id")
	private List<TestQuestionRelation> questions;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPoints() {
		return points;
	}
	public int getReleased() {
		return released;
	}
	public void setReleased(int released) {
		this.released = released;
	}
	public void setPoints(int points) {
		this.points = points;
	}
	public List<TestQuestionRelation> getQuestions() {
		return questions;
	}
	public void setQuestions(List<TestQuestionRelation> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "Test [id=" + id + ", name=" + name + ", points=" + points + ", questions=" + questions + "]";
	}
	
}
