package com.xdz.service;

import java.util.List;

import com.xdz.domain.CommentProblem;

public interface ICmtProblemService {
	
	int insert(CommentProblem record);
	
	int deleteById(int id);
	
	List<CommentProblem> selectByPid(int pid);
	List<CommentProblem> selectByUid(int fromUid);

	int updateBySupport(CommentProblem record);
}
