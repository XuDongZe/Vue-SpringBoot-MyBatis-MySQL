// class PathURL {
// 	constructor() {
// 		this.login: "/login",
//   	this.register: "/register"
// 	}
// };

// const paths = new PathURL();
// export default paths;

const paths = {
	Customer : "/user",
	CustomerRanks : "/rank/users",
	Skill : "/skill",
  CUSTOMER_LOGIN : "/login",
	CUSTOMER_REGISTER : "/register",
	CUSTOMER_UPDATE: "/update/user",
	
	Problem : "/problem",
	Problems : "/problems",
	HotProblems : "/problems/hot",
	FavoriteProblems : "/favorite/problems",
	CustomerProblems : "/user/problems",
	TagProblems : "/problems/tag", //
	Submissions : "/submissions",
	ErrorProblems : "/errors",
	PROBLEM_ADD : "/add/problem",
	PROBLEM_DELETE : "/delete/problem",
	PROBLEM_UPDATE : "update/problem",
	FAVORITE_PROBLEM_ADD : "/add/favorite/problem",
	FAVORITE_PROBLEM_DELETE : "/delete/favorite/problem",
	SUBMISSION_ADD : "/add/submission",
	ERROR_PROBLEM_DELETE : "/delete/error", //
	
	Article: "/article",
	Articles : "/articles",
	HotArticles : "/articles/hot",
	FavoriteArticles : "/favorite/articles",
	CustomerArticles : "/user/articles",
	ARTICLE_ADD : "/add/article",
	ARTICLE_DELETE : "/delete/article",
	ARTICLE_UPDATE : "update/article",
	FAVORITE_ARTICLE_ADD : "/add/favorite/article",
	FAVORITE_ARTICLE_DELETE : "/delete/favorite/article",
	
	CommentProblems : "/comments/problem",
	COMMENT_PROBLEM_ADD : "/add/comment/problem",
	COMMENT_PROBLEM_DELETE : "/delete/comment/problem",
	COMMENT_PROBLEM_UPDATE : "/update/comment/problem", //
	
	CommentArticles : "/comments/article",
	COMMENT_ARTICLE_ADD : "/add/comment/article",
	COMMENT_ARTICLE_DELETE : "/delete/comment/article",
	COMMENT_ARTICLE_UPDATE : "/update/comment/article", // 

	CHECK_PROBLEM_FAVORITE: "/check/favorite/problem",
	CHECK_ARTICLE_FAVORITE: "/check/favorite/article",

	PROBLEM_PREV: "/prev/problem",
	PROBLEM_NEXT: "/next/problem",
	ARTICLE_PREV: "/prev/article",
	ARTICLE_NEXT: "/next/article",

	ARTICLE_ADD_ONE_READER: "/add/article_reader"
};

export default paths;

