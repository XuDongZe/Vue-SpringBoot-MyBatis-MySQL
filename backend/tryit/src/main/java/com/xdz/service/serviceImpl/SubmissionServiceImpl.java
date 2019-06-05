package com.xdz.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdz.dao.ISubmissionDao;
import com.xdz.domain.Submission;
import com.xdz.service.IProblemService;
import com.xdz.service.ISubmissionService;
import com.xdz.utils.DateUtil;

import tk.mybatis.mapper.entity.Example;

@Service
public class SubmissionServiceImpl implements ISubmissionService{
	
	@Autowired
	private ISubmissionDao dao;
	
	@Override
	public int insert(Submission submission) {
		// TODO Auto-generated method stub
		DateUtil.ReWriteTimeStamp(submission);
		return dao.insertUseGeneratedKeys(submission);
	}

	@Override
	public List<Submission> selectByUidAndPid(int uid, int pid) {
		// TODO Auto-generated method stub
		Example example = new Example(Submission.class);
		example.createCriteria()
			.andEqualTo("uid", uid)
			.andEqualTo("pid", pid);
		return dao.selectByExample(example);
	}
	
	
}
