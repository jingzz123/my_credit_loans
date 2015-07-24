package cn.creditloans.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.db.Dbjbxx;
import cn.creditloans.core.entity.p2p.P2PDkjbxx;
import cn.creditloans.tools.page.PageModel;

public interface DbjbxxDao {

	/**
	 * 根据担保基本信息查询相应结果数，用于分页
	 * @param dbjbxx
	 * @return
	 */
	int selectDbjbxxPageCount(Dbjbxx dbjbxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param dbjbxx
	 * @return
	 */
	List<Dbjbxx> selectDbjbxxPageList(@Param("pm") PageModel<?> pm, @Param("dbjbxx") Dbjbxx dbjbxx);
	
	/**
	 * 根据id查询担保基本信息
	 * @param id
	 * @return
	 */
	Dbjbxx selectDbjbxxById(int id);
	
	/**
	 * 根据dkhthm,ywh,jsyhkrq,orgCode确定担保基本信息唯一性
	 * @param params
	 * @return
	 */
	int selectDbjbxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 添加担保基本信息
	 * @param dbjbxx
	 */
	void insertDbkjbxx(Dbjbxx dbjbxx);
	
	/**
	 * 更新担保基本信息
	 * @param dbjbxx
	 */
	void updateDbjbxx(Dbjbxx dbjbxx);
	
	/**
	 * 根据id删除担保基本信息
	 * @param id
	 */
	void deleteDbjbxxById(int id);
	
}
