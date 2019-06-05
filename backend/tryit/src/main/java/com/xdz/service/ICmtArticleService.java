package com.xdz.service;

import java.util.List;

import com.xdz.domain.CommentArticle;

public interface ICmtArticleService {

	int insert(CommentArticle record);
	
	int deleteById(int id);
	
	List<CommentArticle> selectByAid(int aid);
	
	List<CommentArticle> selectByUid(int fromUid);

	int updateBySupport(CommentArticle record);
}
