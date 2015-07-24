package cn.creditloans.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.db.DbBdbrxx;
import cn.creditloans.core.entity.db.Dbjbxx;
import cn.creditloans.core.entity.p2p.P2PDkjbxx;
import cn.creditloans.tools.page.PageModel;

public interface DbBdbrxxDao {

	/**
	 * 根据被担保人信息查询相应结果数，用于分页
	 * @param dbBdbrxx
	 * @return
	 */
	int selectDbBdbrxxPageCount(DbBdbrxx dbBdbrxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param dbBdbrxx
	 * @return
	 */
	List<DbBdbrxx> selectDbBdbrxxPageList(@Param("pm") PageModel<?> pm, @Param("dbBdbrxx") DbBdbrxx dbBdbrxx);
	
	/**
	 * 根据id查询被担保人信息
	 * @param id
	 * @return
	 */
	DbBdbrxx selectDbBdbrxxById(int id);
	
	/**
	 * 根据dkhthm,ywh,jsyhkrq,orgCode确定被担保人信息唯一性
	 * @param params
	 * @return
	 */
	int selectDbBdbrxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 添加被担保人信息
	 * @param dbBdbrxx
	 */
	void insertDbBdbrxx(DbBdbrxx dbBdbrxx);
	
	/**
	 * 更新被担保人信息
	 * @param dbBdbrxx
	 */
	void updateDbBdbrxx(DbBdbrxx dbBdbrxx);
	
	/**
	 * 根据被担保人信息
	 * @param id
	 */
	void deleteDbBdbrxxById(int id);
	/**
	 * 
	* 查询主schema是否被关联
	* @param 
	* @return
	 */
	public int selectCountByDbhtxxId(int dbhtxxId);
}
