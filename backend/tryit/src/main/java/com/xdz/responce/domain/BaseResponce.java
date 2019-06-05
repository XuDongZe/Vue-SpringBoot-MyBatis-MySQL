package com.xdz.responce.domain;

public class BaseResponce<T> {
	private int status;
	private int code;
	private String msg;
	private T data;
	private Object extra;
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public Object getExtra() {
		return extra;
	}
	public void setExtra(Object extra) {
		this.extra = extra;
	}
	
	
}
