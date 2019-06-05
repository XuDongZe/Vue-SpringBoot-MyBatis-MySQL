package com.xdz.responce.util;

import com.xdz.responce.domain.BaseResponce;

import net.bytebuddy.asm.Advice.This;

public class ResponceFactory {
	
	private static<T> BaseResponce<T> responce(Integer status, Integer code, String msg, T data) {
		BaseResponce<T> result = new BaseResponce<>();
        result.setStatus(status);
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        result.setExtra(null);
        return result;
    }
	
	public static<T> BaseResponce<T> success() {
		return success(null);
	}
	public static<T> BaseResponce<T> success(T data) {
		return responce(1, 200, "请求成功", data);
	}
	public static<T> BaseResponce<T> success(T data, Object extra) {
		BaseResponce<T> res = responce(1, 200, "请求成功", data);
		res.setExtra(extra);
		return res;
	}
	
	public static<T> BaseResponce<T> error(int errorCode, String errorMsg) {
		return responce(0, errorCode, errorMsg, null);
	}
	
//	使用errorCode = 500 作为系统通用错误
	public static<T> BaseResponce<T> error(String errorMsg) {
		return error(500, errorMsg);
	}
}
