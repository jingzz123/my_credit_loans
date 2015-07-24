package cn.creditloans.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.db.DbZqrjzhtxx;
import cn.creditloans.tools.page.PageModel;

public interface DbZqrjzhtxxDao {

	/**
	 * 根据债权人及主合同信息查询相应结果数，用于分页
	 * @param dbZqrjzhtxx
	 * @return
	 */
	int selectDbZqrjzhtxxPageCount(DbZqrjzhtxx dbZqrjzhtxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param dbZqrjzhtxx
	 * @return
	 */
	List<DbZqrjzhtxx> selectDbZqrjzhtxxPageList(@Param("pm") PageModel<?> pm, @Param("dbZqrjzhtxx") DbZqrjzhtxx dbZqrjzhtxx);
	
	/**
	 * 根据id查询债权人及主合同信息
	 * @param id
	 * @return
	 */
	DbZqrjzhtxx selectDbZqrjzhtxxById(int id);
	
	/**
	 * 根据dkhthm,ywh,jsyhkrq,orgCode确定被债权人及主合同信息唯一性
	 * @param params
	 * @return
	 */
	int selectDbZqrjzhtxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 添加被债权人及主合同信息
	 * @param dbZqrjzhtxx
	 */
	void insertDbZqrjzhtxx(DbZqrjzhtxx dbZqrjzhtxx);
	
	/**
	 * 更新债权人及主合同信息
	 * @param dbZqrjzhtxx
	 */
	void updateDbZqrjzhtxx(DbZqrjzhtxx dbZqrjzhtxx);
	
	/**
	 * 根据债权人及主合同信息
	 * @param id
	 */
	void deleteDbZqrjzhtxxById(int id);
	/**
	 * 
	* 查询主schema是否被关联
	* @param 
	* @return
	 */
	public int selectCountByDbhtxxId(int dbhtxxId);
}
