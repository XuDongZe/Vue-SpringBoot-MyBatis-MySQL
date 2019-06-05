package com.xdz.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name = "tb_article")
public class Article {
	
	@Id
    @GeneratedValue(generator = "JDBC")
	private Integer id;
	
	@Column(name = "uid")
	private Integer uid;
	@Transient
	private Customer author;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "content")
	private String content;
	
	@Column(name = "supports")
	private Integer supports;
	
	@Column(name = "favorites")
	private Integer favorites;
	
	@Column(name = "readers")
	private Integer readers;
	
	@Column(name = "time")
	private Timestamp time;
	
	public Integer getFavorites() {
		return favorites;
	}

	public void setFavorites(Integer favorites) {
		this.favorites = favorites;
	}

	public Integer getReaders() {
		return readers;
	}

	public void setReaders(Integer readers) {
		this.readers = readers;
	}

	public void setSupports(Integer supports) {
		this.supports = supports;
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

	public Customer getAuthor() {
		return author;
	}

	public void setAuthor(Customer author) {
		this.author = author;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getSupports() {
		return supports;
	}

	public void setSupports(int supports) {
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
		return "Article [id=" + id + ", uid=" + uid + ", title=" + title + "]";
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
		Article other = (Article) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
