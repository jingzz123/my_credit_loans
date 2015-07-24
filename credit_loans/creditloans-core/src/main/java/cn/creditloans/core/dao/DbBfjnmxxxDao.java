package cn.creditloans.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.db.DbBfjnmxxx;
import cn.creditloans.core.entity.db.DbZcmxxx;
import cn.creditloans.tools.page.PageModel;

public interface DbBfjnmxxxDao {

	/**
	 * 根据保费缴纳 明细信息查询相应结果数，用于分页
	 * @param dbBfjnmxxx
	 * @return
	 */
	int selectDbBfjnmxxxPageCount(DbBfjnmxxx dbBfjnmxxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param dbBfjnmxxx
	 * @return
	 */
	List<DbBfjnmxxx> selectDbBfjnmxxxPageList(@Param("pm") PageModel<?> pm, @Param("dbBfjnmxxx") DbBfjnmxxx dbBfjnmxxx);
	
	/**
	 * 根据id查询保费缴纳明细信息
	 * @param id
	 * @return
	 */
	DbBfjnmxxx selectDbBfjnmxxxById(int id);
	
	/**
	 * 根据dkhthm,ywh,jsyhkrq,orgCode确定被保费缴纳明细信息唯一性
	 * @param params
	 * @return
	 */
//	int selectDbFdbrxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 添加保费缴纳明细信息
	 * @param dbBfjnmxxx
	 */
	void insertDbBfjnmxxx(DbBfjnmxxx dbBfjnmxxx);
	
	/**
	 * 更新保费缴纳明细信息
	 * @param dbBfjnmxxx
	 */
	void updateDbBfjnmxxx(DbBfjnmxxx dbBfjnmxxx);
	
	/**
	 * 根据保费缴纳明细信息
	 * @param id
	 */
	void deleteDbBfjnmxxxById(int id);
	/**
	 * 
	* 查询主schema是否被关联
	* @param 
	* @return
	 */
	public int selectCountByDbhtxxId(int dbhtxxId);
}
