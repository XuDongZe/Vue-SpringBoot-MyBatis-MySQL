package com.xdz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdz.config.PathConfig;
import com.xdz.domain.Article;
import com.xdz.domain.CommentArticle;
import com.xdz.domain.FavoriteArticle;
import com.xdz.domain.FavoriteProblem;
import com.xdz.domain.Problem;
import com.xdz.responce.domain.BaseException;
import com.xdz.responce.domain.BaseResponce;
import com.xdz.responce.domain.ErrorCodeAndMsg;
import com.xdz.responce.util.ResponceFactory;
import com.xdz.service.IArticleService;
import com.xdz.service.ICmtArticleService;
import com.xdz.service.IFAService;

@RestController
public class ArticleController {

//	文章服务
	@Autowired
	private IArticleService articleService;
//  收藏夹服务
	@Autowired
	private IFAService faService;
//	评论服务
	@Autowired
	private ICmtArticleService cmtService;

//	下面是基础文章服务
	@PostMapping(value = PathConfig.ARTICLE_ADD)
	@ResponseBody
	@Transactional
	public BaseResponce addArticle(@RequestBody Article record) {
		if(articleService.check(record)) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_ALREADY_EXISTED);
		}
		int res = articleService.insert(record);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_INSERT);
		}
		return ResponceFactory.success();
	}
	
	@PostMapping(value = PathConfig.ARTICLE_DELETE)
	@ResponseBody
	@Transactional
	public BaseResponce deleteArticle(@RequestBody Article article) {
		int res = articleService.delete(article);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_DELETE);
		}
		return ResponceFactory.success();
	}
	
	@PostMapping(value = PathConfig.ARTICLE_UPDATE)
	@ResponseBody
	@Transactional
	public BaseResponce updateArticle(@RequestBody Article article) {
		int res = articleService.update(article);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_UPDATE);
		}
		return ResponceFactory.success();
	}
	
	@PostMapping(value = PathConfig.ARTICLE_ADD_ONE_READER)
	@ResponseBody
	@Transactional
	public BaseResponce addOneReader(
			@RequestBody Article article) {
		int res = articleService.addOneReader(article.getId());
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_UPDATE); 
		}
		return ResponceFactory.success();
	}
	
	@GetMapping(value = PathConfig.Article)
	@ResponseBody
	@Transactional
	public BaseResponce getArticle(
		@RequestParam int id) {
		Article article = articleService.selectByPrimaryKey(id);
		if(article == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_NOT_EXISTED);
		}
		return ResponceFactory.success(article);
	}
	
	@GetMapping(value = PathConfig.ARTICLE_PREV)
	@ResponseBody
	@Transactional
	public BaseResponce getPrevArticle(
		@RequestParam int id) {
		Article article = articleService.getPrevArticle(id);
		if(article == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_NOT_EXISTED);
		}
		return ResponceFactory.success(article);
	}
	
	@GetMapping(value = PathConfig.ARTICLE_NEXT)
	@ResponseBody
	@Transactional
	public BaseResponce getNextArticle(
		@RequestParam int id) {
		Article article = articleService.getNextArticle(id);
		if(article == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_NOT_EXISTED);
		}
		return ResponceFactory.success(article);
	}
	
	@GetMapping(value = PathConfig.Articles)
	@ResponseBody
	@Transactional
	public BaseResponce getPageArticles(
		@RequestParam(name="pageNum") int pageNum,
		@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> articles = articleService.selectAll();
		if(articles == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_NOT_EXISTED);
		}
		PageInfo<Article> pageInfo = new PageInfo<>(articles);
		return ResponceFactory.success(pageInfo);
	}
	
	@GetMapping(value = PathConfig.CustomerArticles)
	@ResponseBody
	@Transactional
	public BaseResponce getArticlesByUid(
		@RequestParam int uid,
		@RequestParam(name="pageNum") int pageNum,
		@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Article> articles = articleService.selectByUid(uid);
		if(articles == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_NOT_EXISTED);
		}
		PageInfo<Article> pageInfo = new PageInfo<>(articles);
		return ResponceFactory.success(pageInfo);
	}
	
	@GetMapping(value = PathConfig.HotArticles)
	@ResponseBody
	@Transactional
	public BaseResponce getHotArticles
	(@RequestParam(name="hotNums", defaultValue="1") int hotNums) {
		PageHelper.startPage(1, hotNums);
		List<Article> articles = articleService.selectByHotNums(hotNums);
		if(articles == null ) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_NOTFOUND);
		}
		PageInfo<Article> pageInfo = new PageInfo<>(articles);
		return ResponceFactory.success(pageInfo.getList());
	}
	
//  下面是收藏夹服务
	@GetMapping(value = PathConfig.CHECK_ARTICLE_FAVORITE)
	@ResponseBody
	public BaseResponce checkArticleFavorited(
			@RequestParam int uid, @RequestParam int aid) {
		return  ResponceFactory.success( faService.checkByUidAndAid(uid, aid) );
	}
//	收藏夹展示
	@GetMapping(value = PathConfig.FavoriteArticles)
	@ResponseBody
	public BaseResponce getFavoriteArticles(
			@RequestParam int uid, @RequestParam int pageNum, @RequestParam int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<FavoriteArticle> fps = faService.selectByUId(uid);
		if(fps == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_NOT_EXISTED);
		}
		PageInfo<FavoriteArticle> pageInfo = new PageInfo<>(fps);
		return ResponceFactory.success(pageInfo);
	}

//收藏夹编辑: -> 增加题目
	@PostMapping(value = PathConfig.FAVORITE_ARTICLE_ADD)
	@ResponseBody
	@Transactional
	public BaseResponce insertFavoritearticle(@RequestBody FavoriteArticle record) {
		if(faService.checkByUidAndAid(record.getUid(), record.getAid())) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_ALREADY_EXISTED);
		}
		int res = faService.insertByUidAndPid(record);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_INSERT);
		}
		return ResponceFactory.success();
	}

// 收藏夹编辑: 删除题目
	@PostMapping(value = PathConfig.FAVORITE_ARTICLE_DELETE)
	@ResponseBody
	@Transactional
	public BaseResponce deleteFavoriteArticle(@RequestBody FavoriteArticle record) {
		int res = faService.deleteByUidAndAid(record);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ARTICLE_DELETE);
		}
		return ResponceFactory.success();
	}
//  下面是评论服务
	@GetMapping(value = PathConfig.CommentArticles)
	@ResponseBody
	public BaseResponce getCommentsByAid(@RequestParam int aid,
			@RequestParam int pageNum, @RequestParam(defaultValue="5") int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<CommentArticle> comments = cmtService.selectByAid(aid);
		if(comments == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_COMMENT_NOT_FOUND);
		}
		PageInfo<CommentArticle> pageInfo = new PageInfo<>(comments);
		return ResponceFactory.success(pageInfo);
	}
	
	@PostMapping(value = PathConfig.COMMENT_ARTICLE_ADD)
	@ResponseBody
	public BaseResponce reply(@RequestBody CommentArticle record) {
		int res = cmtService.insert(record);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_COMMENT_INSERT);
		}
		return ResponceFactory.success();
	}
	
	@PostMapping(value = PathConfig.COMMENT_ARTICLE_UPDATE)
	@ResponseBody
	public BaseResponce updateSupport(@RequestBody CommentArticle record) {
		if(record.getSupports() == null) {
			return ResponceFactory.success();
		}		int res = cmtService.updateBySupport(record);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_COMMENT_UPDATE);
		}
		return ResponceFactory.success();
	}
	
	@PostMapping(value = PathConfig.COMMENT_ARTICLE_DELETE)
	@ResponseBody
	public BaseResponce delete(@RequestBody CommentArticle record) {
		int res = cmtService.deleteById(record.getId());
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_COMMENT_DELETE);
		}
		return ResponceFactory.success();
	}
}
