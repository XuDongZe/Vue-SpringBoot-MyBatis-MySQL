package com.xdz.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "tb_comment_problem")
public class CommentProblem {
	
	@Id
	@GeneratedValue(generator = "JDBC")
	private Integer id;
	
	@Column(name = "pid")
	private Integer pid;
	
	@Column(name = "from_uid")
	private Integer fromUid;
	@Transient
	private Customer from;
	
	@Column(name = "to_uid")
	private Integer toUid;
	@Transient
	private Customer to;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "supports")
	private Integer supports;
	
	@Column(name = "time")
	private Timestamp time;

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

	public Integer getFromUid() {
		return fromUid;
	}

	public void setFromUid(Integer fromUid) {
		this.fromUid = fromUid;
	}

	public Customer getFrom() {
		return from;
	}

	public void setFrom(Customer from) {
		this.from = from;
	}

	public Integer getToUid() {
		return toUid;
	}

	public void setToUid(Integer toUid) {
		this.toUid = toUid;
	}

	public Customer getTo() {
		return to;
	}

	public void setTo(Customer to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getSupports() {
		return supports;
	}

	public void setSupports(Integer supports) {
		this.supports = supports;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "CommentProblem [id=" + id + ", pid=" + pid + ", fromUid=" + fromUid + ", toUid=" + toUid + "]";
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
		CommentProblem other = (CommentProblem) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
