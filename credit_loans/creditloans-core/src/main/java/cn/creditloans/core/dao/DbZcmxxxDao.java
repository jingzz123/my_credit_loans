package cn.creditloans.core.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.db.DbDcmxxx;
import cn.creditloans.core.entity.db.DbZcmxxx;
import cn.creditloans.tools.page.PageModel;

public interface DbZcmxxxDao {

	/**
	 * 根据追偿明细信息查询相应结果数，用于分页
	 * @param dbZcmxxx
	 * @return
	 */
	int selectDbZcmxxxPageCount(DbZcmxxx dbZcmxxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param dbZcmxxx
	 * @return
	 */
	List<DbZcmxxx> selectDbZcmxxxPageList(@Param("pm") PageModel<?> pm, @Param("dbZcmxxx") DbZcmxxx dbZcmxxx);
	
	/**
	 * 根据id查询追偿明细信息
	 * @param id
	 * @return
	 */
	DbZcmxxx selectDbZcmxxxById(int id);
	
	/**
	 * 根据dkhthm,ywh,jsyhkrq,orgCode确定被追偿明细信息唯一性
	 * @param params
	 * @return
	 */
//	int selectDbFdbrxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 添加追偿明细信息
	 * @param dbZcmxxx
	 */
	void insertDbZcmxxx(DbZcmxxx dbZcmxxx);
	
	/**
	 * 更新追偿明细信息
	 * @param dbDcmxxx
	 */
	void updateDbZcmxxx(DbZcmxxx dbZcmxxx);
	
	/**
	 * 根据追偿明细信息
	 * @param id
	 */
	void deleteDbZcmxxxById(int id);
	/**
	 * 
	* 查询主schema是否被关联
	* @param 
	* @return
	 */
	public int selectCountByDbhtxxId(int dbhtxxId);
}
