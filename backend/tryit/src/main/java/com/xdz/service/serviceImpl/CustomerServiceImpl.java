package com.xdz.service.serviceImpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.xdz.baseDao.IUserDefineDao;
import com.xdz.dao.ICustomerDao;
import com.xdz.dao.ISkillDao;
import com.xdz.domain.Customer;
import com.xdz.domain.Problem;
import com.xdz.domain.Skill;
import com.xdz.service.ICustomerService;
import com.xdz.service.ISkillService;

import tk.mybatis.mapper.entity.Example;

@Service
public class CustomerServiceImpl implements ICustomerService {
	@Autowired
	private ICustomerDao customerDao;
	@Autowired
	private ISkillService skillService;
	
	@Transactional
	private Customer WritePropertiesToCustomer(Customer customer) {
		if(customer == null || customer.getSkill() != null)
			return null;
		Skill skill = skillService.selectById(customer.getId());
		customer.setSkill(skill);
		return customer;
	}
	
	@Transactional
	private List<Customer> WritePropertiesToProblemList(List<Customer> customers) {
		if(customers == null || customers.isEmpty())
			return null;
		customers.forEach((item) ->
			WritePropertiesToCustomer(item));
		return customers;
	}
	
	@Override
	public Customer insert(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.insertSelective(customer);
		Skill skill = new Skill(customer.getId());
		skillService.insert(skill);
		customer.setSkill(skill);
		return customer;
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return customerDao.deleteByPrimaryKey(id);
	}

	@Override
	public int update(Customer customer) {
		// TODO Auto-generated method stub
		return customerDao.updateByPrimaryKeySelective(customer);
	}
	
	@Override
	public Customer selectByName(String username) {
		// TODO Auto-generated method stub
		Example example = new Example(Customer.class, true, true);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("username", username);
		Customer customer = customerDao.selectOneByExample(example);
		
		return WritePropertiesToCustomer(customer);
	}
	
	@Override
	public Customer selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		Customer customer = customerDao.selectByPrimaryKey(id);
		return WritePropertiesToCustomer(customer);
	}
	@Override
	public List<Customer> selectAll() {
		// TODO Auto-generated method stub
		 return WritePropertiesToProblemList( customerDao.selectAll() );
	}

	@Override
	public Customer selectByNameAndPwd(String username, String password) {
		// TODO Auto-generated method stub
		Customer customer = new Customer(username, password);
		customer = customerDao.selectOne(customer);
		return WritePropertiesToCustomer(customer);
	}

	@Override
	public List<Customer> selectByRankInfo(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		Example example = new Example(Customer.class);
		example.orderBy("achievement").desc();
		
		return customerDao.selectByExample(example);
	}

}
