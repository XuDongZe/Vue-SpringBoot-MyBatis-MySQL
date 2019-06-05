package com.xdz.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdz.dao.ICmtArticleDao;
import com.xdz.dao.ICustomerDao;
import com.xdz.domain.CommentArticle;
import com.xdz.service.ICmtArticleService;
import com.xdz.utils.DateUtil;

import tk.mybatis.mapper.entity.Example;

@Service
public class CmtArticleServiceImpl implements ICmtArticleService{

	@Autowired
	private ICmtArticleDao dao;
	
	@Autowired
	private ICustomerDao customerDao;

	@Transactional
	private void ReWriteProperties(CommentArticle record) {
		record.setFrom(
				customerDao.selectByPrimaryKey(record.getFromUid()) );
		record.setTo(
				customerDao.selectByPrimaryKey(record.getToUid()) );
	}
	@Transactional
	private void ReWritePropertiesToList(List<CommentArticle> list) {
		if(list == null) {
			return;
		}
		list.forEach(item -> ReWriteProperties(item));
	}
	
	@Override
	public int insert(CommentArticle record) {
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
	public List<CommentArticle> selectByAid(int aid) {
		// TODO Auto-generated method stub
		Example example = new Example(CommentArticle.class);
		example.createCriteria().andEqualTo("aid", aid);
		List<CommentArticle> comments = dao.selectByExample(example);
		
		ReWritePropertiesToList(comments);
		return comments;
	}

	@Override
	public List<CommentArticle> selectByUid(int fromUid) {
		// TODO Auto-generated method stub
		Example example = new Example(CommentArticle.class);
		example.createCriteria().andEqualTo("fromUid", fromUid);
		List<CommentArticle> comments = dao.selectByExample(example);
		
		ReWritePropertiesToList(comments);
		return comments;
	}

	@Override
	public int updateBySupport(CommentArticle record) {
		// TODO Auto-generated method stub
		if(record.getSupports() == null) {
			return 1;
		}
		CommentArticle cmt = new CommentArticle();
		cmt.setId(record.getId());
		cmt.setSupports(record.getSupports());
		
		return dao.updateByPrimaryKeySelective(cmt);
	}
}
