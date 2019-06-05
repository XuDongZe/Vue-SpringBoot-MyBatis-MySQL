package com.xdz.domain;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "tb_problem")
public class Problem {
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;
	
	private Integer uid;
	private String title;
	private String description;
	private String optionA;
	private String optionB;
	private String optionC;
	private String optionD;
	private String answer;
	private Integer supports;
	private Integer favorites;
	private String difficulty;
	
	@Transient
	List<TagProblem> tags;
	
	@Transient
	private Customer author;
	
	public List<TagProblem> getTags() {
		return tags;
	}

	public void setTags(List<TagProblem> tags) {
		this.tags = tags;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getSupports() {
		return supports;
	}

	public void setSupports(Integer supports) {
		this.supports = supports;
	}

	public Integer getFavorites() {
		return favorites;
	}

	public void setFavorites(Integer favorites) {
		this.favorites = favorites;
	}

	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Customer getAuthor() {
		return author;
	}

	public void setAuthor(Customer author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Problem [id=" + id + ", uid=" + uid + ", title=" + title + "]";
	}
	
	
}
