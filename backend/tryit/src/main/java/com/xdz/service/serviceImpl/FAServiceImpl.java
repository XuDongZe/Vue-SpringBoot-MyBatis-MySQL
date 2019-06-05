package com.xdz.service.serviceImpl;

import java.awt.Checkbox;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdz.dao.IArticleDao;
import com.xdz.dao.IFavoriteArticleDao;
import com.xdz.dao.IProblemDao;
import com.xdz.domain.Article;
import com.xdz.domain.FavoriteArticle;
import com.xdz.domain.FavoriteProblem;
import com.xdz.domain.Problem;
import com.xdz.service.IArticleService;
import com.xdz.service.IFAService;
import com.xdz.utils.DateUtil;

import tk.mybatis.mapper.entity.Example;

@Service
public class FAServiceImpl implements IFAService {

	@Autowired
	private IFavoriteArticleDao faDao;
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private IArticleDao articleDao;
	
	private void ReWriteProperties(FavoriteArticle record) {
		if(record == null) {
			return;
		}
		record.setArticle(articleService.selectByPrimaryKey(record.getAid()));
	}
	
	private void ReWritePropertiesToList(List<FavoriteArticle> list) {
		list.forEach(item -> {
			ReWriteProperties(item);
		});
	}
	
	@Override
	public int insert(FavoriteArticle record) {
		// TODO Auto-generated method stub
		int res = faDao.insertSelective(record);
		return res;
	}

	@Override
	public int deleteById(Integer id) {
		// TODO Auto-generated method stub
		return faDao.deleteByPrimaryKey(id);
	}

	@Override
	public List<FavoriteArticle> selectByUId(int uid) {
		// TODO Auto-generated method stub
		Example example = new Example(FavoriteArticle.class);
		example.createCriteria().andEqualTo("uid", uid);
		List<FavoriteArticle> res = faDao.selectByExample(example);
		ReWritePropertiesToList(res);
		
		return res;
	}

	@Override
	public boolean checkByUidAndAid(int uid, int aid) {
		// TODO Auto-generated method stub
		Example example = new Example(FavoriteArticle.class);
		example.createCriteria().andEqualTo("uid", uid).andEqualTo("aid", aid);
		
		return faDao.selectCountByExample(example) != 0;
	}

	@Override
	@Transactional
	public int insertByUidAndPid(FavoriteArticle record) {
		// TODO Auto-generated method stub
		if(checkByUidAndAid(record.getUid(), record.getAid())) {
			return 1;
		}
		DateUtil.ReWriteTimeStamp(record);
		Article article = articleDao.selectByPrimaryKey(record.getAid());
		article.setFavorites(article.getFavorites() + 1);
		articleDao.updateByPrimaryKeySelective(article);
		return faDao.insertUseGeneratedKeys(record);
	}

	@Override
	@Transactional
	public int deleteByUidAndAid(FavoriteArticle record) {
		// TODO Auto-generated method stub
		Example example = new Example(FavoriteArticle.class);
		example.createCriteria()
			.andEqualTo("uid", record.getUid())
			.andEqualTo("aid", record.getAid());
		int res = faDao.deleteByExample(example);
		
		Article article = articleDao.selectByPrimaryKey(record.getAid());
		int cnt = article.getFavorites() - 1 <= 0 ? 0 : article.getFavorites() - 1;
		
		article.setFavorites(cnt);
		articleDao.updateByPrimaryKeySelective(article);
		
		return res;
	}
}
