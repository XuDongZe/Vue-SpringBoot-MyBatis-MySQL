package com.xdz.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdz.config.PathConfig;
import com.xdz.domain.Customer;
import com.xdz.domain.Skill;
import com.xdz.responce.domain.BaseException;
import com.xdz.responce.domain.BaseResponce;
import com.xdz.responce.domain.ErrorCodeAndMsg;
import com.xdz.responce.util.ResponceFactory;
import com.xdz.service.ICustomerService;
import com.xdz.service.ISkillService;

@RestController
public class CustomerController {
	
	@Autowired
	private ICustomerService customerService;
	@Autowired
	private ISkillService skillService;
	
	@PostMapping(value=PathConfig.CUSTOMER_REGISTER)
	@ResponseBody
	@Transactional
	public BaseResponce<Customer>
	register(@RequestBody Customer customer) {
		Customer res = customerService.selectByName(customer.getUsername());
		if(res != null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_CUSTOMER_ALREADY_EXISTED);
		}
		customer = customerService.insert(customer);
		if(customer == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_CUSTOMER_INSERT);
		}
		return ResponceFactory.success(customer);
	}
	
	@PostMapping(value = PathConfig.CUSTOMER_LOGIN)
	@ResponseBody
	@Transactional
	public BaseResponce<Customer>
	login(@RequestBody Customer customer) {
		Customer res = customerService.selectByName(customer.getUsername());
		if(res == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_CUSTOMER_NOT_EXISTED);
		}
		if( !customer.getPassword().equals(res.getPassword()) ) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_CUSTOMER_PASSWORD);
		}
		return ResponceFactory.success(res);
	}
	
	@PostMapping(value = PathConfig.CUSTOMER_UPDATE)
	@ResponseBody
	@Transactional
	public BaseResponce<Customer>
	update(@RequestBody Customer customer) {
		int res = customerService.update(customer);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_CUSTOMER_UPDATE);
		}
		Skill skill = customer.getSkill();
		if(skill != null) {
			skill.setId(customer.getId());
			if( skillService.update(skill) == 0) {
				throw new BaseException(ErrorCodeAndMsg.ERROR_CUSTOMER_UPDATE);
			}
		}
		return ResponceFactory.success();
	}
	
	@GetMapping(PathConfig.CustomerRanks)
	@ResponseBody
	@Transactional
	public BaseResponce
	getRanks(@RequestParam int pageNum, @RequestParam int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Customer> res = customerService.selectByRankInfo(pageNum, pageSize);
		if(res == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_CUSTOMER_NOT_EXISTED);
		}
		PageInfo<Customer> pageInfo = new PageInfo<>(res);
		return ResponceFactory.success(pageInfo);
	}
}
