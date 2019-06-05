package com.xdz.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table(name = "tb_skill")
public class Skill {
	@Id
	private Integer id;
	
	@Column(name = "cpp")
	private Integer cpp;
	@Column(name = "linux")
	private Integer linux;
	@Column(name = "os")
	private Integer os;
	@Column(name = "string")
	private Integer string;
	@Column(name = "java")
	private Integer java;
	@Column(name = "network")
	private Integer network;
	@Column(name = "html_css")
	private Integer htmlCss;
	@Column(name = "js")
	private Integer js;
	@Column(name = "data_struct")
	private Integer dataStruct;
	@Column(name = "数据库")
	private Integer database;
	
	public Skill() {
		
	}
	public Skill(int id) {
		this.setId(id);
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCpp() {
		return cpp;
	}
	public void setCpp(Integer cpp) {
		this.cpp = cpp;
	}
	public Integer getLinux() {
		return linux;
	}
	public void setLinux(Integer linux) {
		this.linux = linux;
	}
	public Integer getOs() {
		return os;
	}
	public void setOs(Integer os) {
		this.os = os;
	}
	public Integer getString() {
		return string;
	}
	public void setString(Integer string) {
		this.string = string;
	}
	public Integer getJava() {
		return java;
	}
	public void setJava(Integer java) {
		this.java = java;
	}
	public Integer getNetwork() {
		return network;
	}
	public void setNetwork(Integer network) {
		this.network = network;
	}
	public Integer getHtmlCss() {
		return htmlCss;
	}
	public void setHtmlCss(Integer htmlCss) {
		this.htmlCss = htmlCss;
	}
	public Integer getJs() {
		return js;
	}
	public void setJs(Integer js) {
		this.js = js;
	}
	public Integer getDataStruct() {
		return dataStruct;
	}
	public void setDataStruct(Integer dataStruct) {
		this.dataStruct = dataStruct;
	}
	public Integer getDatabase() {
		return database;
	}
	public void setDatabase(Integer database) {
		this.database = database;
	}
	@Override
	public String toString() {
		return "Skill [id=" + id + "]";
	}
}
