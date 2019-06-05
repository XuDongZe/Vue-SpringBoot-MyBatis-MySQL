package com.xdz.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.github.pagehelper.PageHelper; 
import com.github.pagehelper.PageInfo;

public class PagerUtil {
	public static Map<String, Boolean> getEasyPageInfo(PageInfo pageInfo) {
		Map<String, Boolean> res = new HashMap<String, Boolean>();
		res.put("HasPreviousPage", pageInfo.isHasPreviousPage());
		res.put("hasNextPage", pageInfo.isHasNextPage());
		
		return res;
	}
}
