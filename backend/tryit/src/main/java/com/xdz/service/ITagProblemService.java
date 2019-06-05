package com.xdz.service;

import java.util.List;

import com.xdz.domain.Problem;
import com.xdz.domain.TagProblem;

public interface ITagProblemService {
	
	int insert(TagProblem tp);
	
	int insertList(List<TagProblem> list);

	int deleteById(Integer id);

	int update(TagProblem item);
	
	List<TagProblem> selectByPId(int pid);
	List<TagProblem> selectByName(String name);
	
	TagProblem selectById(int id);

}
