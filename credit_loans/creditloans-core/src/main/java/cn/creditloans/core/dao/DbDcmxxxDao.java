package cn.creditloans.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.db.DbDcmxxx;
import cn.creditloans.core.entity.db.DbFdbrxx;
import cn.creditloans.tools.page.PageModel;

public interface DbDcmxxxDao {

	/**
	 * 根据代偿明细信息查询相应结果数，用于分页
	 * @param dbDcmxxx
	 * @return
	 */
	int selectDbDcmxxxPageCount(DbDcmxxx dbDcmxxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param dbDcmxxx
	 * @return
	 */
	List<DbDcmxxx> selectDbDcmxxxPageList(@Param("pm") PageModel<?> pm, @Param("dbDcmxxx") DbDcmxxx dbDcmxxx);
	
	/**
	 * 根据id查询代偿明细信息
	 * @param id
	 * @return
	 */
	DbDcmxxx selectDbDcmxxxById(int id);
	
	/**
	 * 根据dkhthm,ywh,jsyhkrq,orgCode确定代偿明细信息唯一性
	 * @param params
	 * @return
	 */
//	int selectDbFdbrxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 添加代偿明细信息
	 * @param dbDcmxxx
	 */
	void insertDbDcmxxx(DbDcmxxx dbDcmxxx);
	
	/**
	 * 更新代偿明细信息
	 * @param dbDcmxxx
	 */
	void updateDbDcmxxx(DbDcmxxx dbDcmxxx);
	
	/**
	 * 根据代偿明细信息
	 * @param id
	 */
	void deleteDbDcmxxxById(int id);
	/**
	 * 
	* 查询主schema是否被关联
	* @param 
	* @return
	 */
	public int selectCountByDbhtxxId(int dbhtxxId);
}
