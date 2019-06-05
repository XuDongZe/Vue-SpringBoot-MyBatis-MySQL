package com.xdz.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xdz.baseDao.IUserDefineDao;
import com.xdz.dao.IArticleDao;
import com.xdz.dao.ICustomerDao;
import com.xdz.domain.Article;
import com.xdz.domain.Customer;
import com.xdz.domain.Problem;
import com.xdz.service.IArticleService;
import com.xdz.utils.DateUtil;

import tk.mybatis.mapper.entity.Example;

@Service
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private IArticleDao articleDao;
	
	@Autowired
	private ICustomerDao customerDao;
	
	@Autowired
	private IUserDefineDao userDefineDao;
	
	private void RewriteProperties(Article article) {
		if(article == null ) {
			return;
		}
		article.setAuthor(
				customerDao.selectByPrimaryKey(article.getUid()));
	}
	private void ReWritePropertiesToList(List<Article> articles) {
		if(articles == null) {
			return;
		}
		articles.forEach(item -> RewriteProperties(item));
	}
	
	@Override
	public boolean check(Article article) {
		// TODO Auto-generated method stub
		return articleDao.selectCount(article) != 0;
	}
	
	@Override
	public int insert(Article record) {
		// TODO Auto-generated method stub
		DateUtil.ReWriteTimeStamp(record);
		return articleDao.insertSelective(record);
	}

	@Override
	public int update(Article record) {
		// TODO Auto-generated method stub
		return articleDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int delete(Article record) {
		// TODO Auto-generated method stub
		return articleDao.deleteByPrimaryKey(record.getId());
	}

	@Override
	public List<Article> selectAll() {
		// TODO Auto-generated method stub
		List<Article> articles = articleDao.selectAll();
		if(articles == null) {
			return null;
		}
		ReWritePropertiesToList(articles);
		return articles;
	}

	@Override
	public List<Article> selectByUid(int uid) {
		// TODO Auto-generated method stub
		Example example = new Example(Article.class);
		example.createCriteria().andEqualTo("uid", uid);
		
		List<Article> articles = articleDao.selectByExample(example);
		if(articles == null) {
			return null;
		}
		ReWritePropertiesToList(articles);
		return articles;
	}

	@Override
	public List<Article> selectByHotNums(int hotNums) {
		// TODO Auto-generated method stub
		Example example = new Example(Article.class);
		example.orderBy("supports").desc();
		
		List<Article> articles = articleDao.selectByExample(example);
		if(articles == null) {
			return null;
		}
		ReWritePropertiesToList(articles);
		return articles;
	}
	@Override
	public Article selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		Article res = articleDao.selectByPrimaryKey(id);
		RewriteProperties(res);
		return res;
	}
	@Override
	public Article getNextArticle(int id) {
		// TODO Auto-generated method stub
		Article res = userDefineDao.getNextArticle(id);
		RewriteProperties(res);
		
		return res;
	}
	@Override
	public Article getPrevArticle(int id) {
		// TODO Auto-generated method stub
		Article res = userDefineDao.getPrevArticle(id);
		RewriteProperties(res);
		
		return res;
	}
	@Override
	public int addOneReader(int id) {
		// TODO Auto-generated method stub
		Article article = articleDao.selectByPrimaryKey(id);
		if(article == null) {
			return 0;
		}
		article.setReaders(article.getReaders() + 1);
		return articleDao.updateByPrimaryKeySelective(article);
	}

}
