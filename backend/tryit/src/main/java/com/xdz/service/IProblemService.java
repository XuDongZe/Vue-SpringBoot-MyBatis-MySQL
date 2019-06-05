package com.xdz.service;



import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xdz.domain.Problem;

public interface IProblemService {
	
	int insert(Problem problem);

	int delete(Problem problem);
	
	int update(Problem problem);
	
	Problem selectByPrimaryKey(int id);
	
	List<Problem> selectAll();
	
	List<Problem> selectByHotNums(int hotNums);
	
//	与用户相关的
	List<Problem> selectAllByUserId(int uid);

	boolean check(Problem problem);
	
	Problem getNextProblem(int id);
	
	Problem getPrevProblem(int id);
}
