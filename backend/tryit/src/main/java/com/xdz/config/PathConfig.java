package com.xdz.config;

public class PathConfig {
	public static final String CustomerRanks = "/rank/users";
	public static final String CUSTOMER_LOGIN = "/login";
	public static final String CUSTOMER_REGISTER = "/register";
	public static final String CUSTOMER_UPDATE= "/update/user";
	public static final String Customer = "/user";
	public static final String Skill = "/skill";
	
	public static final String Problems = "/problems";
	public static final String Problem = "/problem";
	public static final String CustomerProblems = "/user/problems";
	public static final String PROBLEM_ADD = "/add/problem";
	public static final String PROBLEM_DELETE = "/delete/problem";
	public static final String PROBLEM_UPDATE = "update/problem";
	public static final String HotProblems = "/problems/hot";
	public static final String TagProblems = "/problems/tag";
	public static final String FavoriteProblems = "/favorite/problems";
	public static final String FAVORITE_PROBLEM_ADD = "/add/favorite/problem";
	public static final String FAVORITE_PROBLEM_DELETE = "/delete/favorite/problem";
	public static final String Submissions = "/submissions";
	public static final String SUBMISSION_ADD = "/add/submission";
	public static final String ErrorProblems = "/errors";
	public static final String ERROR_PROBLEM_DELETE = "/delete/error";
	
	public static final String Article = "/article";
	public static final String Articles = "/articles";
	public static final String CustomerArticles = "/user/articles";
	public static final String ARTICLE_ADD = "/add/article";
	public static final String ARTICLE_DELETE = "/delete/article";
	public static final String ARTICLE_UPDATE = "update/article";
	public static final String HotArticles = "/articles/hot";
	public static final String FavoriteArticles = "/favorite/articles";
	public static final String FAVORITE_ARTICLE_ADD = "/add/favorite/article";
	public static final String FAVORITE_ARTICLE_DELETE = "/delete/favorite/article";
	
	public static final String CommentProblems = "/comments/problem";
	public static final String COMMENT_PROBLEM_ADD = "/add/comment/problem";
	public static final String COMMENT_PROBLEM_DELETE = "/delete/comment/problem";
	public static final String COMMENT_PROBLEM_UPDATE = "/update/comment/problem";
	
	public static final String CommentArticles = "/comments/article";
	public static final String COMMENT_ARTICLE_ADD = "/add/comment/article";
	public static final String COMMENT_ARTICLE_DELETE = "/delete/comment/article";
	public static final String COMMENT_ARTICLE_UPDATE = "/update/comment/article";

//  初始化 检查 -> 收藏否?
	public static final String CHECK_PROBLEM_FAVORITE = "/check/favorite/problem";
	public static final String CHECK_ARTICLE_FAVORITE = "/check/favorite/article";
	public static final String FavoriteProblem = "/favorite/problem";
	
	public static final String PROBLEM_PREV = "/prev/problem"; 
	public static final String PROBLEM_NEXT = "/next/problem";
	public static final String ARTICLE_PREV = "/prev/article";
	public static final String ARTICLE_NEXT = "/next/article";
	
	public static final String ARTICLE_ADD_ONE_READER = "/add/article_reader";
}
