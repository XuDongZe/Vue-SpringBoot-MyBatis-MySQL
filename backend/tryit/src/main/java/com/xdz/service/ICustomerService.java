package com.xdz.service;

import java.util.List;

import com.xdz.domain.Customer;

public interface ICustomerService {
	Customer insert(Customer customer);
	
	int deleteByPrimaryKey(Integer id);
	
	int update(Customer customer);

	Customer selectByPrimaryKey(Integer id);
    List<Customer> selectAll();

	Customer selectByName(String username);

	Customer selectByNameAndPwd(String username, String password);

	List<Customer> selectByRankInfo(int pageNum, int pageSize);

}
