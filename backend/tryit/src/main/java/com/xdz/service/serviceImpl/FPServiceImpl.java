package com.xdz.service.serviceImpl;

import java.util.List;

import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdz.dao.IFavoriteProblemDao;
import com.xdz.dao.IProblemDao;
import com.xdz.domain.FavoriteProblem;
import com.xdz.domain.Problem;
import com.xdz.service.IFPService;
import com.xdz.utils.DateUtil;

import tk.mybatis.mapper.entity.Example;

@Service
public class FPServiceImpl implements IFPService {

	@Autowired
	private IFavoriteProblemDao fpDao;
	
	@Autowired
	private IProblemDao problemDao;
	
	@Override
	@Transactional
	public int insertByUidAndPid(FavoriteProblem record) {
		// TODO Auto-generated method stub
		if(checkByUidAndPid(record.getUid(), record.getPid())) {
			return 1;
		}
		DateUtil.ReWriteTimeStamp(record);
		Problem problem = problemDao.selectByPrimaryKey(record.getPid());
		problem.setFavorites(problem.getFavorites() + 1);
		problemDao.updateByPrimaryKeySelective(problem);
		return fpDao.insertUseGeneratedKeys(record);
	}

	@Override
	public FavoriteProblem selectByUIdAndPId(int uid, int pid) {
		Example example = new Example(FavoriteProblem.class);
		example.createCriteria()
		.andEqualTo("uid", uid)
		.andEqualTo("pid", pid);
		return fpDao.selectOneByExample(example);
	}
	
	@Override
	public List<FavoriteProblem> selectByUId(int uid) {
		// TODO Auto-generated method stub
		Example example = new Example(FavoriteProblem.class);
		example.createCriteria().andEqualTo("uid", uid);
		return fpDao.selectByExample(example);
	}

	@Override
	@Transactional
	public int deleteByPrimaryKey(FavoriteProblem record) {
		// TODO Auto-generated method stub
		if(checkByUidAndPid(record.getUid(), record.getPid()) == false) {
			return 1;
		}
		Problem problem = problemDao.selectByPrimaryKey(record.getPid());
		problem.setFavorites(problem.getFavorites() - 1);
		problemDao.updateByPrimaryKeySelective(problem);
		return fpDao.deleteByPrimaryKey((record.getId()));
	}

	@Override
	public boolean checkByUidAndPid(int uid, int pid) {
		// TODO Auto-generated method stub
		Example example = new Example(FavoriteProblem.class);
		example.createCriteria().andEqualTo("uid", uid).andEqualTo("pid", pid);
		
		return fpDao.selectCountByExample(example) != 0;
	}

	@Override
	@Transactional
	public int deleteByUidAndPid(FavoriteProblem record) {
		// TODO Auto-generated method stub
		Example example = new Example(FavoriteProblem.class);
		example.createCriteria()
			.andEqualTo("uid", record.getUid())
			.andEqualTo("pid", record.getPid());
		int res = fpDao.deleteByExample(example);
		
		Problem problem = problemDao.selectByPrimaryKey(record.getPid());
		int cnt = problem.getFavorites() - 1 <= 0 ? 0 : problem.getFavorites() - 1;
		problem.setFavorites(cnt);
		problemDao.updateByPrimaryKeySelective(problem);
		
		return res;
	}
}
