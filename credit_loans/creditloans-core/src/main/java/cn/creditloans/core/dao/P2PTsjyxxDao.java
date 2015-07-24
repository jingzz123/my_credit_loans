package cn.creditloans.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.p2p.P2PTsjyxx;
import cn.creditloans.tools.page.PageModel;

public interface P2PTsjyxxDao {

	/**
	 * 根据P2P特殊交易信息查询相应结果数，用于分页
	 * @param p2pTsjyxx
	 * @return
	 */
	int selectP2PTsjyxxPageCount(P2PTsjyxx p2pTsjyxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param p2pTsjyxx
	 * @return
	 */
	List<P2PTsjyxx> selectP2PTsjyxxPageList(@Param("pm") PageModel<?> pm, @Param("p2pTsjyxx") P2PTsjyxx p2pTsjyxx);
	
	/**
	 * 根据id查询P2P特殊交易信息
	 * @param id
	 * @return
	 */
	P2PTsjyxx selectP2PTsjyxxById(int id);
	
	/**
	 * 根据ywh,fsrq,orgCode确定特殊交易信息唯一性
	 * @param params
	 * @return
	 */
	int selectP2PTsjyxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 添加P2P特殊交易信息
	 * @param p2pTsjyxx
	 */
	void insertP2PTsjyxx(P2PTsjyxx p2pTsjyxx);
	
	/**
	 * 更新P2P特殊交易信息
	 * @param p2pTsjyxx
	 */
	void updateP2PTsjyxx(P2PTsjyxx p2pTsjyxx);
	
	/**
	 * 根据id删除P2P特殊交易信息
	 * @param id
	 */
	void deleteP2PTsjyxxById(int id);
	
}
