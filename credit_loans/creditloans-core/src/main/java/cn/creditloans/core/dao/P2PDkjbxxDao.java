package cn.creditloans.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.p2p.P2PDkjbxx;
import cn.creditloans.tools.page.PageModel;

public interface P2PDkjbxxDao {

	/**
	 * 根据P2P贷款基本信息查询相应结果数，用于分页
	 * @param p2pDkjbxx
	 * @return
	 */
	int selectP2PDkjbxxPageCount(P2PDkjbxx p2pDkjbxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param p2pDkjbxx
	 * @return
	 */
	List<P2PDkjbxx> selectP2PDkjbxxPageList(@Param("pm") PageModel<?> pm, @Param("p2pDkjbxx") P2PDkjbxx p2pDkjbxx);
	
	/**
	 * 根据id查询P2P贷款基本信息
	 * @param id
	 * @return
	 */
	P2PDkjbxx selectP2PDkjbxxById(int id);
	
	/**
	 * 根据dkhthm,ywh,jsyhkrq,orgCode确定贷款基本信息唯一性
	 * @param params
	 * @return
	 */
	int selectP2PDkjbxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 根据唯一标识（dkhthm,ywh,jsyhkrq,orgCode）查询当月还款状态
	 * @param params
	 * @return
	 */
	String selectByhkztByUniqueId(Map<String,Object> params); 
	
	/**
	 * 添加P2P贷款基本信息
	 * @param p2pDkjbxx
	 */
	void insertP2PDkjbxx(P2PDkjbxx p2pDkjbxx);
	
	/**
	 * 更新P2P贷款基本信息
	 * @param p2pDkjbxx
	 */
	void updateP2PDkjbxx(P2PDkjbxx p2pDkjbxx);
	
	/**
	 * 根据id删除P2P贷款基本信息
	 * @param id
	 */
	void deleteP2PDkjbxxById(int id);
	
}
