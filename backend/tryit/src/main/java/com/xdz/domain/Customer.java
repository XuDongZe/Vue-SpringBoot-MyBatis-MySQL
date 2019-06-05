package com.xdz.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "tb_customer")//mybatis通用接口mapper依赖JPA实体类采用JPA
public class Customer {
//	主键, 自动递增
	@Id
    @GeneratedValue(generator = "JDBC")
	private Integer id;
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="avatar")
	private String avatar;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="description")
	private String description;
	
	@Column(name="achievement")
	private Integer achievement;
	
	@Column(name="week_rise")
	private Integer weekRise;
	
	@Column(name="rank_order")
	private Integer rankOrder;
	
	@Column(name="problems")
	private Integer problems;
	
	@Column(name="articles")
	private Integer articles;
	
	@Column(name="supports")
	private Integer supports;
	
	@Transient
	private Skill skill;
	
	public Customer() {
		
	}
	public Customer(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	
	
	public Skill getSkill() {
		return skill;
	}
	public void setSkill(Skill skill) {
		this.skill = skill;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getAchievement() {
		return achievement;
	}
	public void setAchievement(Integer achievement) {
		this.achievement = achievement;
	}
	public Integer getWeekRise() {
		return weekRise;
	}
	public void setWeekRise(Integer weekRise) {
		this.weekRise = weekRise;
	}
	public Integer getRankOrder() {
		return rankOrder;
	}
	public void setRankOrder(Integer rankOrder) {
		this.rankOrder = rankOrder;
	}
	public Integer getProblems() {
		return problems;
	}
	public void setProblems(Integer problems) {
		this.problems = problems;
	}
	public Integer getArticles() {
		return articles;
	}
	public void setArticles(Integer articles) {
		this.articles = articles;
	}
	public Integer getSupports() {
		return supports;
	}
	public void setSupports(Integer supports) {
		this.supports = supports;
	}
	
	
}