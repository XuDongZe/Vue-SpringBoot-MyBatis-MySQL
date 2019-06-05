package com.xdz.service.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdz.dao.ITagProblemDao;
import com.xdz.domain.TagProblem;
import com.xdz.service.ITagProblemService;

import tk.mybatis.mapper.entity.Example;

@Service
public class TagProblemServiceImpl implements ITagProblemService {

	@Autowired
	private ITagProblemDao tpDao;
	
	@Override
	public int insert(TagProblem tp) {
		// TODO Auto-generated method stub
		return tpDao.insertUseGeneratedKeys(tp);
	}

	@Override
	public int insertList(List<TagProblem> list) {
		// TODO Auto-generated method stub
		return tpDao.insertList(list);
	}

	@Override
	public int update(TagProblem tp) {
		// TODO Auto-generated method stub
		return tpDao.updateByPrimaryKey(tp);
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return tpDao.deleteByPrimaryKey(id);
	}

	@Override
	public TagProblem selectById(int id) {
		// TODO Auto-generated method stub
		TagProblem res = tpDao.selectByPrimaryKey(id);
		return res;
	}
	
	@Override
	public List<TagProblem> selectByPId(int pid) {
		// TODO Auto-generated method stub
		Example example = new Example(TagProblem.class);
		example.createCriteria().andEqualTo("pid", pid);
		List<TagProblem> res = tpDao.selectByExample(example);
		return res;
	}

	@Override
	public List<TagProblem> selectByName(String name) {
		// TODO Auto-generated method stub
		Example example = new Example(TagProblem.class);
		example.createCriteria().andEqualTo("name", name);
		List<TagProblem> res = tpDao.selectByExample(example);
		return res;
	}
}
