package com.xdz.service;

import java.util.List;

import com.xdz.domain.ErrorProblem;
import com.xdz.domain.Submission;

public interface IErrorProblemService {
	int deleteById(int id);
	
	List<ErrorProblem> selectByUid(int uid);

	int insertBySubmission(Submission submission);

	ErrorProblem selectByUidAndPid(int uid, int pid);
}
