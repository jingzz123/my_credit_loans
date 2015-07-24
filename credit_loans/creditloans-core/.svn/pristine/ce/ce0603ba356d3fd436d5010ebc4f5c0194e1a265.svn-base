package cn.creditloans.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.p2p.P2PTzrxx;
import cn.creditloans.tools.page.PageModel;

public interface P2PTzrxxDao {

	/**
	 * 根据P2P贷款投资人信息查询相应结果数，用于分页
	 * @param p2pTzrxx
	 * @return
	 */
	int selectP2PTzrxxPageCount(P2PTzrxx p2pTzrxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param p2pTzrxx
	 * @return
	 */
	List<P2PTzrxx> selectP2PTzrxxPageList(@Param("pm") PageModel<?> pm, @Param("p2pTzrxx") P2PTzrxx p2pTzrxx);
	
	/**
	 * 根据id查询P2P贷款投资人信息
	 * @param id
	 * @return
	 */
	P2PTzrxx selectP2PTzrxxById(int id);
	
	/**
	 * 根据tzrzjlx,tzrzjhm,dkjbxxId,orgCode确定贷款投资人信息唯一性
	 * @param params
	 * @return
	 */
	int selectP2PTzrxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 添加P2P贷款投资人信息
	 * @param p2pTzrxx
	 */
	void insertP2PTzrxx(P2PTzrxx p2pTzrxx);
	
	/**
	 * 更新P2P贷款投资人信息
	 * @param p2pTzrxx
	 */
	void updateP2PTzrxx(P2PTzrxx p2pTzrxx);
	
	/**
	 * 根据id删除P2P贷款投资人信息
	 * @param id
	 */
	void deleteP2PTzrxxById(int id);
	
}
