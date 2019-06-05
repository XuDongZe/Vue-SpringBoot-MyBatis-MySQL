package com.xdz.service;

import com.xdz.domain.Skill;

public interface ISkillService {
	
	int deleteById(int id);
	
	int update(Skill skill);
	
	Skill insert(Skill skill);

	Skill selectById(int id);
}
