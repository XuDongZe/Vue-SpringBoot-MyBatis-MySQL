package com.xdz.controller;

import java.util.ArrayList;
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
import com.xdz.baseDao.IUserDefineDao;
import com.xdz.config.PathConfig;
import com.xdz.domain.CommentProblem;
import com.xdz.domain.ErrorProblem;
import com.xdz.domain.FavoriteProblem;
import com.xdz.domain.Problem;
import com.xdz.domain.Submission;
import com.xdz.domain.TagProblem;
import com.xdz.responce.domain.BaseException;
import com.xdz.responce.domain.BaseResponce;
import com.xdz.responce.domain.ErrorCodeAndMsg;
import com.xdz.responce.util.ResponceFactory;
import com.xdz.service.ICmtProblemService;
import com.xdz.service.IErrorProblemService;
import com.xdz.service.IFPService;
import com.xdz.service.IProblemService;
import com.xdz.service.ISubmissionService;
import com.xdz.service.ITagProblemService;
import com.xdz.utils.PagerUtil;

@RestController
public class ProblemController {
//  题库服务
	@Autowired
	private IProblemService problemSerivce;
//  知识点管理服务
	@Autowired
	private ITagProblemService tpService;
//  收藏夹服务	
	@Autowired
	private IFPService fPService;
//  提交记录服务	
	@Autowired
	private ISubmissionService sbService;
//	错题本服务
	@Autowired
	private IErrorProblemService errorService;
//  评论服务
	@Autowired
	private ICmtProblemService cmtService;
	
//  下面是题目列表(题库)服务
	@PostMapping(value = PathConfig.PROBLEM_ADD)
	@ResponseBody
	@Transactional
	public BaseResponce addProblem(@RequestBody Problem problem) {
		if(problemSerivce.check(problem)) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_ALREADY_EXISTED);
		}
		int res = problemSerivce.insert(problem);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_INSERT);
		}
		return ResponceFactory.success();
	}
	
	@PostMapping(value = PathConfig.PROBLEM_DELETE)
	@ResponseBody
	@Transactional
	public BaseResponce deleteProblem(@RequestBody Problem problem) {
		int res = problemSerivce.delete(problem);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_DELETE);
		}
		return ResponceFactory.success();
	}
	
	@PostMapping(value = PathConfig.PROBLEM_UPDATE)
	@ResponseBody
	@Transactional
	public BaseResponce updateProblem(@RequestBody Problem problem) {
		System.out.println(problem);
		int res = problemSerivce.update(problem);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_UPDATE);
		}
		return ResponceFactory.success();
	}
	
	@GetMapping(value = PathConfig.Problem)
	@ResponseBody
	@Transactional
	public BaseResponce getProblem(
		@RequestParam int id) {
		Problem problem = problemSerivce.selectByPrimaryKey(id);
		if(problem == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_NOT_EXISTED);
		}
		return ResponceFactory.success(problem);
	}
	
	@GetMapping(value = PathConfig.PROBLEM_PREV)
	@ResponseBody
	@Transactional
	public BaseResponce getPrevProblem(
		@RequestParam int id) {
		Problem problem = problemSerivce.getPrevProblem(id);
		if(problem == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_NOT_EXISTED);
		}
		return ResponceFactory.success(problem);
	}
	
	@GetMapping(value = PathConfig.PROBLEM_NEXT)
	@ResponseBody
	@Transactional
	public BaseResponce getNextProblem(
		@RequestParam int id) {
		Problem problem = problemSerivce.getNextProblem(id);
		if(problem == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_NOT_EXISTED);
		}
		return ResponceFactory.success(problem);
	}
	
	@GetMapping(value = PathConfig.Problems)
	@ResponseBody
	@Transactional
	public BaseResponce<PageInfo<Problem>> getPageProblems(
		@RequestParam(name="pageNum") int pageNum,
		@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Problem> problems = problemSerivce.selectAll();
		if(problems == null) {
			String str = "pageNum: " + pageNum + " pageSize: " + pageSize + ": ";
			throw new BaseException(str, ErrorCodeAndMsg.ERROR_NOTFOUND);
		}
		PageInfo<Problem> pageInfo = new PageInfo<>(problems);
		return ResponceFactory.success(pageInfo);
	}
	
	@GetMapping(value = PathConfig.CustomerProblems)
	@ResponseBody
	@Transactional
	public BaseResponce getProblemsByUid(
		@RequestParam int uid,
		@RequestParam(name="pageNum") int pageNum,
		@RequestParam(name="pageSize", defaultValue="10") int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Problem> problems = problemSerivce.selectAllByUserId(uid);
		if(problems == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_NOT_EXISTED);
		}
		PageInfo<Problem> pageInfo = new PageInfo<>(problems);
		return ResponceFactory.success(pageInfo);
	}
	
	@GetMapping(value = PathConfig.HotProblems)
	@ResponseBody
	@Transactional
	public BaseResponce<List<Problem>> getHotProblems
	(@RequestParam(name="hotNums", defaultValue="1") int hotNums) {
		PageHelper.startPage(1, hotNums);
		List<Problem> problems = problemSerivce.selectByHotNums(hotNums);
		if(problems == null ) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_NOTFOUND);
		}
		PageInfo<Problem> pageInfo = new PageInfo<>(problems);
		return ResponceFactory.success(pageInfo.getList());
	}

// 下面是知识点管理
	@GetMapping(value = PathConfig.TagProblems)
	@ResponseBody
	@Transactional
	public BaseResponce getProblemsByName
	(@RequestParam String name, @RequestParam int pageNum, @RequestParam int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<TagProblem> tps = tpService.selectByName(name);
		if(tps == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_NOTFOUND);
		}
		List<Problem> problems = new ArrayList<>();
		tps.forEach(tp -> {
			problems.add(problemSerivce.selectByPrimaryKey(tp.getPid()) );
		});
		PageInfo<TagProblem> pageInfo = new PageInfo<>(tps);
		return ResponceFactory.success(problems, PagerUtil.getEasyPageInfo(pageInfo));
	}
	
// 现在是用户收藏夹
	@GetMapping(value = PathConfig.CHECK_PROBLEM_FAVORITE)
	@ResponseBody
	public BaseResponce checkProblemFavorited(
			@RequestParam int uid, @RequestParam int pid) {
		return  ResponceFactory.success( fPService.checkByUidAndPid(uid, pid) );
	}
	
//	收藏夹展示
	@GetMapping(value = PathConfig.FavoriteProblems)
	@ResponseBody
	public BaseResponce getFavoriteProblems(
			@RequestParam int uid, @RequestParam int pageNum, @RequestParam int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<FavoriteProblem> fps = fPService.selectByUId(uid);
		if(fps == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_NOT_EXISTED);
		}
		fps.forEach(item -> 
			item.setProblem(problemSerivce.selectByPrimaryKey(item.getPid())) );
		PageInfo<FavoriteProblem> pageInfo = new PageInfo<>(fps);
		return ResponceFactory.success(pageInfo);
	}

//收藏夹编辑: -> 增加题目
	
//	/check/favorite
	@GetMapping(PathConfig.FavoriteProblem)
	@ResponseBody
	public BaseResponce checkIsFavorited(
			@RequestParam int uid, @RequestParam int pid) {
		return ResponceFactory.success(fPService.checkByUidAndPid(uid, pid));
	}
	
	@PostMapping(value = PathConfig.FAVORITE_PROBLEM_ADD)
	@ResponseBody
	@Transactional
	public BaseResponce insertFavoriteProblem(@RequestBody FavoriteProblem record) {
		if(fPService.checkByUidAndPid(record.getUid(), record.getPid())) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_ALREADY_EXISTED);
		}
		int res = fPService.insertByUidAndPid(record);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_INSERT);
		}
		return ResponceFactory.success();
	}

// 收藏夹编辑: 删除题目
	@PostMapping(value = PathConfig.FAVORITE_PROBLEM_DELETE)
	@ResponseBody
	@Transactional
	public BaseResponce deleteFavoriteProblem(@RequestBody FavoriteProblem record) {
		int res = fPService.deleteByUidAndPid(record);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_DELETE);
		}else {
			return ResponceFactory.success();
		}
	}
	
// 下面是提交记录(读 插入)
	@ResponseBody
	@GetMapping(value = PathConfig.Submissions)
	@Transactional
	public BaseResponce getSubmissions(@RequestParam int uid, @RequestParam int pid,
			@RequestParam int pageNum, @RequestParam(defaultValue="10") int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<Submission> submissions = sbService.selectByUidAndPid(uid, pid);
		if(submissions == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_SUBMISSION_NOT_EXISTED);
		}
		PageInfo<Submission> pageInfo = new PageInfo<>(submissions);
		return  ResponceFactory.success(pageInfo);
	}
	
	@ResponseBody
	@PostMapping(value = PathConfig.SUBMISSION_ADD)
	@Transactional
	public BaseResponce addSubmission(@RequestBody Submission submission) {
		int res = sbService.insert(submission);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_SUBMISSION_INSERT);
		}
		if(submission.getStatus().equals(Submission.STATUS_ERROR)) {
			res = errorService.insertBySubmission(submission);
			if(res == 0) {
				throw new BaseException(ErrorCodeAndMsg.ERROR_SUBMISSION_INSERT);
			}
		}
		return ResponceFactory.success();
	}

//  下面是错题本
	@ResponseBody
	@GetMapping(value = PathConfig.ErrorProblems)
	public BaseResponce getErrorProblems(@RequestParam int uid,
			@RequestParam int pageNum, @RequestParam(defaultValue="10") int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<ErrorProblem> errors = errorService.selectByUid(uid);
		if(errors == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_ERRORS_NOT_FOUND);
		}
		PageInfo<ErrorProblem> pageInfo = new PageInfo<>(errors);
		return ResponceFactory.success(pageInfo);
	}
	
	@ResponseBody
	@PostMapping(value = PathConfig.ERROR_PROBLEM_DELETE)
	public BaseResponce deleteError(@RequestBody ErrorProblem record) {
		int res = errorService.deleteById(record.getId());
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_PROBLEM_DELETE);
		}else {
			return ResponceFactory.success();
		}
	}

// 下面是评论
	@GetMapping(value = PathConfig.CommentProblems)
	@ResponseBody
	public BaseResponce getCommentsByPid(@RequestParam int pid,
			@RequestParam int pageNum, @RequestParam(defaultValue="5") int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<CommentProblem> comments = cmtService.selectByPid(pid);
		if(comments == null) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_COMMENT_NOT_FOUND);
		}
		PageInfo<CommentProblem> pageInfo = new PageInfo<>(comments);
		return ResponceFactory.success(pageInfo);
	}
	
	@PostMapping(value = PathConfig.COMMENT_PROBLEM_ADD)
	@ResponseBody
	public BaseResponce reply(@RequestBody CommentProblem record) {
		int res = cmtService.insert(record);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_COMMENT_INSERT);
		}
		return ResponceFactory.success();
	}
	
	@PostMapping(value = PathConfig.COMMENT_PROBLEM_UPDATE)
	@ResponseBody
	public BaseResponce updateSupport(@RequestBody CommentProblem record) {
		if(record.getSupports() == null) {
			return ResponceFactory.success();
		}		int res = cmtService.updateBySupport(record);
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_COMMENT_UPDATE);
		}
		return ResponceFactory.success();
	}
	
	@PostMapping(value = PathConfig.COMMENT_PROBLEM_DELETE)
	@ResponseBody
	public BaseResponce delete(@RequestBody CommentProblem record) {
		int res = cmtService.deleteById(record.getId());
		if(res == 0) {
			throw new BaseException(ErrorCodeAndMsg.ERROR_COMMENT_DELETE);
		}
		return ResponceFactory.success();
	}
	
// 下面是题解(增删改查)
}
