package com.xdz.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "tb_favorite_problem")
public class FavoriteProblem {

	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;
	
	@Column(name = "uid")
	private Integer uid;
	
	@Column(name = "pid")
	private Integer pid;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "time")
	private Timestamp time;

	@Transient
	Problem problem;
	
	public Problem getProblem() {
		return problem;
	}
	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public FavoriteProblem() {
		// TODO Auto-generated constructor stub
	}
	
	public FavoriteProblem(Integer uid, Integer pid) {
		this.uid = uid;
		this.pid = pid;
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

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "FavoriteProblem [uid=" + uid + ", pid=" + pid + "]";
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavoriteProblem other = (FavoriteProblem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
