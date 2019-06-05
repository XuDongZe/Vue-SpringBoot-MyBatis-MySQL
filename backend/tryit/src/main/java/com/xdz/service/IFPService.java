package com.xdz.service;

import java.util.List;

import com.xdz.domain.FavoriteProblem;
import com.xdz.responce.domain.BaseResponce;

public interface IFPService {
	List<FavoriteProblem>selectByUId(int uid);

	FavoriteProblem selectByUIdAndPId(int uid, int pid);
	
	int insertByUidAndPid(FavoriteProblem record);

	int deleteByPrimaryKey(FavoriteProblem record);

	boolean checkByUidAndPid(int uid, int pid);

	int deleteByUidAndPid(FavoriteProblem record);
}
