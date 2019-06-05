package com.xdz.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdz.dao.IErrorProblemDao;
import com.xdz.dao.IProblemDao;
import com.xdz.domain.ErrorProblem;
import com.xdz.domain.Submission;
import com.xdz.service.IErrorProblemService;
import com.xdz.service.IProblemService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ErrorProblemServiceImpl implements IErrorProblemService{

	@Autowired
	private IErrorProblemDao dao;
	
	@Autowired
	private IProblemService problemService;
	
	private void RewriteSubmission(ErrorProblem error) {
		error.setProblem(problemService.selectByPrimaryKey(error.getPid()));
	}

	private boolean checkExisted(int uid, int pid) {
		Example example = new Example(ErrorProblem.class);
		example.createCriteria()
			.andEqualTo("uid", uid)
			.andEqualTo("pid", pid);
		int res = dao.selectCountByExample(example);
		return res == 0 ? false : true;
	}
	
	@Override
	public int insertBySubmission(Submission submission) {
		// TODO Auto-generated method stub
		if(checkExisted(submission.getUid(), submission.getPid()) ) {
			return 1;
		}
		ErrorProblem error = new ErrorProblem();
		error.setPid(submission.getPid());
		error.setUid(submission.getUid());
		return dao.insertUseGeneratedKeys(error);
	}

	@Override
	public ErrorProblem selectByUidAndPid(int uid, int pid) {
		// TODO Auto-generated method stub
		Example example = new Example(ErrorProblem.class);
		example.createCriteria()
			.andEqualTo("uid", uid);
		return dao.selectOneByExample(example);
	}

	@Override
	public List<ErrorProblem> selectByUid(int uid) {
		// TODO Auto-generated method stub
		Example example = new Example(ErrorProblem.class);
		example.createCriteria()
			.andEqualTo("uid", uid);
		List<ErrorProblem> errors = dao.selectByExample(example);
		errors.forEach(item -> RewriteSubmission(item));
		
		return errors;
	}

	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}
}
