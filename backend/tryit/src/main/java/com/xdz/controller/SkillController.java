package com.xdz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xdz.config.PathConfig;
import com.xdz.domain.Skill;
import com.xdz.responce.domain.BaseException;
import com.xdz.responce.domain.BaseResponce;
import com.xdz.responce.domain.ErrorCodeAndMsg;
import com.xdz.responce.util.ResponceFactory;
import com.xdz.service.ISkillService;

@RestController
public class SkillController {
	@Autowired
	private ISkillService skillService;
	
	@GetMapping(value=PathConfig.Skill)
	@ResponseBody
	public BaseResponce<Skill>
	getSkillData(@RequestParam(name="id") int id) {
		Skill skill = skillService.selectById(id);
		if(skill == null) {
			throw new BaseException("getSkillByUserId "+id,
					ErrorCodeAndMsg.ERROR_NOTFOUND);
		}
		return ResponceFactory.success(skill);
	}
}
