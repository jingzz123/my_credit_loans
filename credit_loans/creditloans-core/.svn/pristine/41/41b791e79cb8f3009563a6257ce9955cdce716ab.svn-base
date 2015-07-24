package cn.creditloans.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.p2p.P2PDbxx;
import cn.creditloans.tools.page.PageModel;

public interface P2PDbxxDao {

	/**
	 * 根据P2P贷款担保信息查询相应结果数，用于分页
	 * @param p2pDbxx
	 * @return
	 */
	int selectP2PDbxxPageCount(P2PDbxx p2pDbxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param p2pDbxx
	 * @return
	 */
	List<P2PDbxx> selectP2PDbxxPageList(@Param("pm") PageModel<?> pm, @Param("p2pDbxx") P2PDbxx p2pDbxx);
	
	/**
	 * 根据id查询P2P贷款担保信息
	 * @param id
	 * @return
	 */
	P2PDbxx selectP2PDbxxById(int id);
	
	/**
	 * 根据dbrzjlx,dbrzjhm,dkjbxxId,orgCode确定贷款担保信息唯一性
	 * @param params
	 * @return
	 */
	int selectP2PDbxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 添加P2P贷款担保信息
	 * @param p2pDbxx
	 */
	void insertP2PDbxx(P2PDbxx p2pDbxx);
	
	/**
	 * 更新P2P贷款担保信息
	 * @param p2pDbxx
	 */
	void updateP2PDbxx(P2PDbxx p2pDbxx);
	
	/**
	 * 根据id删除P2P贷款担保信息
	 * @param id
	 */
	void deleteP2PDbxxById(int id);
	
}
