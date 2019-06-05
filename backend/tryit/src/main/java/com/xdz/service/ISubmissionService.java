package com.xdz.service;

import java.util.List;

import com.xdz.domain.Submission;

public interface ISubmissionService {
	int insert(Submission submission);
	
	List<Submission> selectByUidAndPid(int uid, int pid);
}
