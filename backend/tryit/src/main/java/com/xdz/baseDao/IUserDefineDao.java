package com.xdz.baseDao;

import java.security.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.mapping.StatementType;
import org.apache.ibatis.type.JdbcType;

import com.xdz.domain.Article;
import com.xdz.domain.Customer;
import com.xdz.domain.Problem;

@Mapper
public interface IUserDefineDao {
	@Select("SELECT * FROM tb_problem AS n WHERE n.`id` < #{id} ORDER BY n.`id` DESC  LIMIT 0,1")
	@Results({
		@Result(property="id", column="id"),
		@Result(property="uid", column="uid"),
		@Result(property="title", column="title"),
		@Result(property="description", column="description"),
		@Result(property="optionA", column="option_a"),
		@Result(property="optionB", column="option_b"),
		@Result(property="optionC", column="option_c"),
		@Result(property="optionD", column="option_d"),
		@Result(property="answer", column="answer"),
		@Result(property="favorites", column="favorites"),
		@Result(property="supports", column="supports")
	})
	Problem getPrevProblem(int id);
	
	@Select("SELECT * FROM tb_problem AS n WHERE n.`id` > #{id} ORDER BY n.`id` LIMIT 0,1")
	@Results({
		@Result(property="id", column="id"),
		@Result(property="uid", column="uid"),
		@Result(property="title", column="title"),
		@Result(property="description", column="description"),
		@Result(property="optionA", column="option_a"),
		@Result(property="optionB", column="option_b"),
		@Result(property="optionC", column="option_c"),
		@Result(property="optionD", column="option_d"),
		@Result(property="answer", column="answer"),
		@Result(property="favorites", column="favorites"),
		@Result(property="supports", column="supports")
	})
	Problem getNextProblem(int id);
	
	@Select("SELECT * FROM tb_article AS n WHERE n.`id` < #{id} ORDER BY n.`id` DESC  LIMIT 0,1")
	@Results({
		@Result(property="id", column="id"),
		@Result(property="uid", column="uid"),
		@Result(property="title", column="title"),
		@Result(property="description", column="description"),
		@Result(property="supports", column="supports"),
		@Result(property="favorites", column="favorites"),
		@Result(property="readers", column="readers"),
		@Result(property="time", column="time", javaType=java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP)
	})
	Article getPrevArticle(int id);
	
	@Select("SELECT * FROM tb_article AS n WHERE n.`id` > #{id} ORDER BY n.`id` LIMIT 0,1")
	@Results({
		@Result(property="id", column="id"),
		@Result(property="uid", column="uid"),
		@Result(property="title", column="title"),
		@Result(property="description", column="description"),
		@Result(property="supports", column="supports"),
		@Result(property="favorites", column="favorites"),
		@Result(property="readers", column="readers"),
		@Result(property="time", column="time", javaType=java.sql.Timestamp.class, jdbcType=JdbcType.TIMESTAMP)
	})
	Article getNextArticle(int id);
	
//	@Select({
//			  "call sp_page ("
//			+ "#{pageNum, mode=IN, jdbcType=INTEGER},"
//			+ "#{pageSize, mode=IN, jdbcType=INTEGER},"
//			+ "#{objs, mode=IN, jdbcType=VARCHAR},"
//			+ "#{where, mode=IN, jdbcType=VARCHAR},"
//			+ "#{order, mode=IN, jdbcType=VARCHAR})"
//	})
//	@Options(statementType=StatementType.CALLABLE)
//	@Results({
//		@Result(property="id", column="id"),
//		@Result(property="username", column="username"),
//		@Result(property="password", column="password"),
//		@Result(property="avatar", column="avatar"),
//		@Result(property="gender", column="gender"),
//		@Result(property="description", column="description"),
//		@Result(property="weekRise", column="week_rise"),
//		@Result(property="rank_order", column="rank_order"),
//		@Result(property="achivement", column="achivement"),
//		@Result(property="problems", column="problems"),
//		@Result(property="articles", column="articles"),
//		@Result(property="supports", column="supports")
//	})
//	List<Customer> selectByRankInfo(int pageNum, int pageSize, 
//			String objs, String where, String order);
	
//	@Select("select * from tb_customer order by id desc limit #{pageNum}, #{pageSize}")
//	@Results({
//		@Result(property="id", column="id"),
//		@Result(property="username", column="username"),
//		@Result(property="password", column="password"),
//		@Result(property="avatar", column="avatar"),
//		@Result(property="gender", column="gender"),
//		@Result(property="description", column="description"),
//		@Result(property="weekRise", column="week_rise"),
//		@Result(property="rankOrder", column="rank_order"),
//		@Result(property="achievement", column="achievement"),
//		@Result(property="problems", column="problems"),
//		@Result(property="articles", column="articles"),
//		@Result(property="supports", column="supports")
//	})
//	传递多个参数需要使用 @Param注解, 以区分这些参数
	List<Customer> selectByRankInfo(@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);
}
