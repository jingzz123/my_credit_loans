package cn.creditloans.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.p2p.P2PDksqxx;
import cn.creditloans.tools.page.PageModel;

public interface P2PDksqxxDao {

	/**
	 * 根据P2P贷款申请信息查询相应结果数，用于分页
	 * @param p2pDksqxx
	 * @return
	 */
	int selectP2PDksqxxPageCount(P2PDksqxx p2pDksqxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param p2pDksqxx
	 * @return
	 */
	List<P2PDksqxx> selectP2PDksqxxPageList(@Param("pm") PageModel<?> pm, @Param("p2pDksqxx") P2PDksqxx p2pDksqxx);
	
	/**
	 * 根据id查询P2P贷款申请信息
	 * @param id
	 * @return
	 */
	P2PDksqxx selectP2PDksqxxById(int id);
	
	/**
	 * 根据dksqh,orgCode确定贷款申请信息唯一性
	 * @param params
	 * @return
	 */
	int selectP2PDksqxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 添加P2P贷款申请信息
	 * @param p2pDksqxx
	 */
	void insertP2PDksqxx(P2PDksqxx p2pDksqxx);
	
	/**
	 * 更新P2P贷款申请信息
	 * @param p2pDksqxx
	 */
	void updateP2PDksqxx(P2PDksqxx p2pDksqxx);
	
	/**
	 * 根据id删除P2P贷款申请信息
	 * @param id
	 */
	void deleteP2PDksqxxById(int id);
	
}
