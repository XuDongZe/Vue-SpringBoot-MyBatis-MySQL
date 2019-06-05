package com.xdz.tryit;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.xdz.baseDao.IUserDefineDao;
import com.xdz.dao.ICustomerDao;
import com.xdz.domain.Customer;
import com.xdz.service.IProblemService;
import com.xdz.service.serviceImpl.CustomerServiceImpl;
import com.xdz.service.serviceImpl.ProblemSerivceImpl;

import tk.mybatis.mapper.entity.Example;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TryitApplicationTests {

	@Autowired
	private IProblemService problemService;
	
	@Autowired
	private IUserDefineDao userDefineDao;
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Test
	public void contextLoads() {
		PageHelper.startPage(1, 10);
		Example example = new Example(Customer.class);
		example.orderBy("achievement").desc();
		List<Customer> customers = customerDao.selectByExample(example);
		
		customers.forEach(item -> {
			System.out.println(item + ":" + item.getAchievement());
		});
	}
}
