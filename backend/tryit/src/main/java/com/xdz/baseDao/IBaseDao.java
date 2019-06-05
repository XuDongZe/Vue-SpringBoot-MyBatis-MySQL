package com.xdz.baseDao;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;
import tk.mybatis.mapper.entity.Example;

public interface IBaseDao<T> extends Mapper<T>, MySqlMapper<T> {

}
