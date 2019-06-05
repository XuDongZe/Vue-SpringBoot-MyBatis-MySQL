package com.xdz.service.serviceImpl;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdz.dao.ICommentProblemDao;
import com.xdz.dao.ICustomerDao;
import com.xdz.domain.CommentArticle;
import com.xdz.domain.CommentProblem;
import com.xdz.domain.Customer;
import com.xdz.service.ICmtProblemService;
import com.xdz.utils.DateUtil;

import tk.mybatis.mapper.entity.Example;

@Service
public class CmtProblemServiceImpl implements ICmtProblemService {

	@Autowired
	private ICommentProblemDao dao;
	@Autowired
	private ICustomerDao customerDao;
	
	@Transactional
	private void ReWriteProperties(CommentProblem record) {
		record.setFrom(
				customerDao.selectByPrimaryKey(record.getFromUid()) );
		record.setTo(
				customerDao.selectByPrimaryKey(record.getToUid()) );
	}
	@Transactional
	private void ReWritePropertiesToList(List<CommentProblem> list) {
		if(list == null) {
			return;
		}
		list.forEach(item -> ReWriteProperties(item));
	}
	
	@Override
	public int insert(CommentProblem record) {
		// TODO Auto-generated method stub
		DateUtil.ReWriteTimeStamp(record);
		return dao.insertSelective(record);
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	} 

	@Override
	public List<CommentProblem> selectByPid(int pid) {
		// TODO Auto-generated method stub
		Example example = new Example(CommentProblem.class);
		example.createCriteria().andEqualTo("pid", pid);
		List<CommentProblem> comments = dao.selectByExample(example);
		
		ReWritePropertiesToList(comments);
		return comments;
	}
	
	@Override
	public List<CommentProblem> selectByUid(int fromUid) {
		// TODO Auto-generated method stub
		Example example = new Example(CommentProblem.class);
		example.createCriteria().andEqualTo("fromUid", fromUid);
		List<CommentProblem> comments = dao.selectByExample(example);
		
		ReWritePropertiesToList(comments);
		return comments;
	}

	@Override
	public int updateBySupport(CommentProblem record) {
		// TODO Auto-generated method stub
		if(record.getSupports() == null) {
			return 1;
		}
		CommentProblem cmt = new CommentProblem();
		cmt.setId(record.getId());
		cmt.setSupports(record.getSupports());
		return dao.updateByPrimaryKeySelective(record);
	}

}
