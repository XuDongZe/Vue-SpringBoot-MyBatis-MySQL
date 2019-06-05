package com.xdz.responce.util;

import org.apache.ibatis.logging.Log;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.xdz.responce.domain.BaseException;
import com.xdz.responce.domain.BaseResponce;
import com.xdz.responce.domain.ErrorCodeAndMsg;

@ControllerAdvice
public class GlobalExceptionHandler {
//自定义异常处理
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public <T> BaseResponce<T> baseExceptionhandle(Exception e) {
        if (e instanceof BaseException) {
            Integer code = 104;
            BaseException exception = (BaseException) e;
            if (exception.getErrorCode() != 0) {
                code = exception.getErrorCode();
            }
            return ResponceFactory.error(code, e.getMessage());
        }
        e.printStackTrace();
        return ResponceFactory.error(e.getMessage() == null ? "服务器内部错误" : e.getMessage());
    }
    
//  自定义404处理  
    @ExceptionHandler(value = NoHandlerFoundException.class)
    @ResponseBody
    public <T> BaseResponce<T> notFoundHandler(NoHandlerFoundException e) {
    	return ResponceFactory.error(ErrorCodeAndMsg.ERROR_NOTFOUND.getCode(),
    			e.getMessage() + ", maybe your url is error!");
    }
}

