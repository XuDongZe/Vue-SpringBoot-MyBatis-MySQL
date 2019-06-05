package com.xdz.service;

import java.util.List;

import com.xdz.domain.FavoriteArticle;
import com.xdz.domain.FavoriteProblem;

public interface IFAService {

	int insert(FavoriteArticle record);

	int deleteById(Integer id);

	List<FavoriteArticle>selectByUId(int uid);

	boolean checkByUidAndAid(int uid, int pid);

	int insertByUidAndPid(FavoriteArticle record);

	int deleteByUidAndAid(FavoriteArticle record);

}
