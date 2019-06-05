package com.xdz.responce.domain;

public enum ErrorCodeAndMsg {

//  概要信息
	ERROR_PRAMA(900, "参数错误"),
	ERROR_NOTFOUND(901, "接口或者数据未发现"),
	ERROR_SYSTEM(902, "系统错误"),
	ERROR_DATABASE(903, "数据库操作失败"),
	ERROR_NETWORK(904, "网络错误"),
	ERROR_UNKOWN(905, "未知的错误"),
	
//	用户相关
	ERROR_CUSTOMER_NOT_EXISTED(100, "用户不存在"),
	ERROR_CUSTOMER_PASSWORD(101, "密码错误"),
	ERROR_CUSTOMER_ALREADY_EXISTED(102, "用户已经存在"),
	ERROR_CUSTOMER_UPDATE(103, "用户信息更新失败"),
	ERROR_CUSTOMER_INSERT(104, "注册失败"),
	
//  题目相关
	ERROR_PROBLEM_NOT_EXISTED(200, "题目不存在"),
	ERROR_PROBLEM_INSERT(201, "题目上传失败"),
	ERROR_PROBLEM_DELETE(202, "题目删除失败"),
	ERROR_PROBLEM_ALREADY_EXISTED(203, "题目已经存在"),
	ERROR_SUBMISSION_NOT_EXISTED(204, "提交记录找不到"),
	ERROR_SUBMISSION_INSERT(205, "提交记录保存成功"),
	ERROR_ERRORS_NOT_FOUND(206, "错题本记录未发现"),
	ERROR_PROBLEM_UPDATE(207, "题目修改失败"),
	
//  文章相关
	ERROR_ARTICLE_NOT_EXISTED(200, "文章不存在"),
	ERROR_ARTICLE_INSERT(201, "文章上传失败"),
	ERROR_ARTICLE_DELETE(202, "文章删除失败"),
	ERROR_ARTICLE_ALREADY_EXISTED(203, "文章已经存在"),
	ERROR_ARTICLE_UPDATE(207, "文章修改失败"),
	
//	评论相关
	ERROR_COMMENT_NOT_FOUND(401, "评论列表未发现"),
	ERROR_COMMENT_INSERT(402, "评论上传失败"),
	ERROR_COMMENT_DELETE(403, "评论删除失败"),
	ERROR_COMMENT_UPDATE(404, "评论更新失败"),
	;
	private int code;
	private String msg;
	
	private ErrorCodeAndMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	
}
