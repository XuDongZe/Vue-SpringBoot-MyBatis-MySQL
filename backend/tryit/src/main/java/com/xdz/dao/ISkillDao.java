package com.xdz.dao;

import org.apache.ibatis.annotations.Select;

import com.xdz.baseDao.IBaseDao;
import com.xdz.domain.Skill;

public interface ISkillDao extends IBaseDao<Skill> {
	@Select("select * from tb_skill where uid = #{uid}")
	public Skill selectByUId(int uid);
	
}

