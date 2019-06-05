package com.xdz.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="tb_tag_problem")
public class TagProblem {
	@Id
	@GeneratedValue(generator="JDBC")
	private Integer id;
	
	@Column(name="pid")
	private Integer pid;
	
	@Column(name = "name")
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}


	@Override
	public String toString() {
		return "TagProblem [id=" + id + ", pid=" + pid + ", name=" + name + "]";
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
		TagProblem other = (TagProblem) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
