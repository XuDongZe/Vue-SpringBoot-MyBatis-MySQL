package com.xdz.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdz.baseDao.IUserDefineDao;
import com.xdz.dao.ICustomerDao;
import com.xdz.dao.IProblemDao;
import com.xdz.domain.Problem;
import com.xdz.domain.TagProblem;
import com.xdz.service.ICustomerService;
import com.xdz.service.IProblemService;
import com.xdz.service.ITagProblemService;

import tk.mybatis.mapper.entity.Example;

@Service
public class ProblemSerivceImpl implements IProblemService {

	@Autowired
	private IProblemDao problemDao;
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Autowired
	private ITagProblemService tpService;
	
	@Autowired
	private IUserDefineDao userDefineDao;
	
	@Override
	public boolean check(Problem problem) {
		return problemDao.selectCount(problem) == 0 ? false : true;
	}
	
	@Transactional
	private Problem WritePropertiesToProblem(Problem problem) {
		if(problem == null)
			return null;
		problem.setAuthor(
				customerDao.selectByPrimaryKey(problem.getUid()) );
		problem.setTags(
				tpService.selectByPId(problem.getId()) );
		return problem;
	}
	
	@Transactional
	private List<Problem> WritePropertiesToProblemList(List<Problem> problems) {
		if(problems == null)
			return null;
		problems.forEach((item) ->
			WritePropertiesToProblem(item));
		return problems;
	}
	
	@Override
	@Transactional
	public int insert(Problem problem) {
		// TODO Auto-generated method stub
		int res = problemDao.insertSelective(problem);
		if(res == 0) return 0;
		
		int pid = problem.getId();
		List<TagProblem> tags = problem.getTags();
		tags.forEach(item -> {
			item.setPid(pid);
		});
		tpService.insertList(tags);
		return res;
	}

	@Override
	public Problem selectByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		Problem problem = problemDao.selectByPrimaryKey(id);
		return WritePropertiesToProblem(problem);
	}

	@Override
	public List<Problem> selectAll() {
		// TODO Auto-generated method stub
		List<Problem>problems = problemDao.selectAll();
		return WritePropertiesToProblemList(problems);
	}

	@Override
	public List<Problem> selectAllByUserId(int uid) {
		// TODO Auto-generated method stub
		Example example = new Example(Problem.class);
		example.createCriteria().andEqualTo("uid", uid);
		return WritePropertiesToProblemList(problemDao.selectByExample(example));
	}
	
	@Override
	public List<Problem> selectByHotNums(int hotNums) {
		// TODO Auto-generated method stub
		Example example = new Example(Problem.class);		
		example.orderBy("supports").desc();
		List<Problem> problems = problemDao.selectByExample(example);
		return WritePropertiesToProblemList(problems);
	}

	@Override
	public int delete(Problem problem) {
		// TODO Auto-generated method stub
		return problemDao.deleteByPrimaryKey(problem.getId());
	}

	@Override
	@Transactional
	public int update(Problem problem) {
		// TODO Auto-generated method stub
		List<TagProblem> olds = tpService.selectByPId(problem.getId());
		List<TagProblem> news = problem.getTags();
		
		int pid = problem.getId();
		news.forEach(item -> {
			if(olds.contains(item)) {
				tpService.update(item);
			}else {
				item.setPid(pid);
				tpService.insert(item);
			}
		});
		olds.forEach(item -> {
			if( news.contains(item) == false) {
				tpService.deleteById(item.getId());
			}
		});
		return problemDao.updateByPrimaryKeySelective(problem);
	}

	@Override
	public Problem getNextProblem(int id) {
		// TODO Auto-generated method stub
		Problem res = userDefineDao.getNextProblem(id);
		WritePropertiesToProblem(res);
		return res;
	}

	@Override
	public Problem getPrevProblem(int id) {
		// TODO Auto-generated method stub
		Problem res = userDefineDao.getPrevProblem(id);
		WritePropertiesToProblem(res);
		return res;
	}	
	
}
