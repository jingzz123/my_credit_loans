package cn.creditloans.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.db.DbFdbrxx;
import cn.creditloans.core.entity.db.DbZqrjzhtxx;
import cn.creditloans.tools.page.PageModel;

public interface DbFdbrxxDao {

	/**
	 * 根据反担保人信息查询相应结果数，用于分页
	 * @param dbFdbrxx
	 * @return
	 */
	int selectDbFdbrxxPageCount(DbFdbrxx dbFdbrxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param dbFdbrxx
	 * @return
	 */
	List<DbFdbrxx> selectDbFdbrxxPageList(@Param("pm") PageModel<?> pm, @Param("dbFdbrxx") DbFdbrxx dbFdbrxx);
	
	/**
	 * 根据id查询反担保人信息
	 * @param id
	 * @return
	 */
	DbFdbrxx selectDbFdbrxxById(int id);
	
	/**
	 * 根据dkhthm,ywh,jsyhkrq,orgCode确定反担保人信息唯一性
	 * @param params
	 * @return
	 */
//	int selectDbFdbrxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 添加反担保人信息
	 * @param dbFdbrxx
	 */
	void insertDbFdbrxx(DbFdbrxx dbFdbrxx);
	
	/**
	 * 更新反担保人信息
	 * @param dbFdbrxx
	 */
	void updateDbFdbrxx(DbFdbrxx dbFdbrxx);
	
	/**
	 * 根据反担保人信息
	 * @param id
	 */
	void deleteDbFdbrxxById(int id);
	/**
	 * 
	* 查询主schema是否被关联
	* @param 
	* @return
	 */
	public int selectCountByDbhtxxId(int dbhtxxId);
}
