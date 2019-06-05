package com.xdz.service;

import java.util.List;

import com.xdz.domain.Article;
import com.xdz.domain.Problem;

public interface IArticleService {
	int insert(Article record);
	
	int update(Article record);
	
	int delete(Article record);
	
	List<Article> selectAll();
	
	List<Article> selectByUid(int uid);
	
	boolean check(Article article);

	List<Article> selectByHotNums(int hotNums);

	Article selectByPrimaryKey(Integer aid);

	Article getNextArticle(int id);

	Article getPrevArticle(int id);

	int addOneReader(int id);

}
