package com.xdz.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Date;

public class DateUtil {
	public static <T> void ReWriteTimeStamp(T data) {
		try {
			Method method = data.getClass().getMethod("setTime", Timestamp.class);
			Timestamp time = new Timestamp(new Date().getTime());
			try {
				method.invoke(data, time);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			return;
		}
	}
}
