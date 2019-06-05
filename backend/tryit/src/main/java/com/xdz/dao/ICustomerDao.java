package com.xdz.dao;


import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import com.xdz.baseDao.IBaseDao;
import com.xdz.domain.Customer;

public interface ICustomerDao extends IBaseDao<Customer> {
//	@Results({
//		@Result(property="id", column="id"),
//		@Result(property="skill", column="id", one=@One(select="com.xdz.dao.ISkillDao.selectByUId", fetchType=FetchType.LAZY))
//	})
//	@Select("select * tb_customer from where username=#{username} and password=#{password}")
//	public Customer selectWithInfoById(int id);
}
