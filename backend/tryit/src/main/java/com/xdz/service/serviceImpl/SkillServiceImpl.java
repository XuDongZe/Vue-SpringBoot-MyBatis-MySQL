package com.xdz.service.serviceImpl;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xdz.dao.ISkillDao;
import com.xdz.domain.Customer;
import com.xdz.domain.Skill;
import com.xdz.service.ISkillService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.spring.annotation.MapperScan;

@Service
public class SkillServiceImpl implements ISkillService {
	@Autowired
	private ISkillDao skillDao;
	
	@Override
	public Skill insert(Skill skill) {
		int res = skillDao.insertSelective(skill);
		return res == 0 ? null : skill;
	}

	@Override
	public int deleteById(int id) {
		// TODO Auto-generated method stub
		return skillDao.deleteByPrimaryKey(id);
	}

	@Override
	public int update(Skill skill) {
		// TODO Auto-generated method stub
		return skillDao.updateByPrimaryKeySelective(skill);
	}

	@Override
	public Skill selectById(int id) {
		// TODO Auto-generated method stub
		return skillDao.selectByPrimaryKey(id);
	}

}
