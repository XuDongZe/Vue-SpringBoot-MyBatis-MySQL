package com.xdz.responce.domain;

public class BaseException extends RuntimeException {
	/** 
	* @Fields serialVersionUID : 
	*/ 
	private static final long serialVersionUID = 1L;
	// 服务器状态码
    private Integer errorCode;
    // 错误信息
    private String errorMsg;

    public BaseException(ErrorCodeAndMsg err) {
    	this(err.getCode(), err.getMsg());
    }
    
    public BaseException(String extra, ErrorCodeAndMsg err) {
    	this(err.getCode(), extra + ": " + err.getMsg());
    }
    
    public BaseException(Integer code, String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
        this.errorCode = code;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}

