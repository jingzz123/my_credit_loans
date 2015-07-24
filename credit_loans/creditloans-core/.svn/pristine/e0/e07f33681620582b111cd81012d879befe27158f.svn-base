package cn.creditloans.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.p2p.P2PGrjbxx;
import cn.creditloans.tools.page.PageModel;

public interface P2PGrjbxxDao {
	
	/**
	 * 根据P2P个人基本信息查询相应结果数，用于分页
	 * @param p2pGrjbxx
	 * @return
	 */
	int selectP2PGrjbxxPageCount(P2PGrjbxx p2pGrjbxx);
	
	/**
	 * 分页查询
	 * @param pm
	 * @param p2pGrjbxx
	 * @return
	 */
	List<P2PGrjbxx> selectP2PGrjbxxPageList(@Param("pm") PageModel<?> pm, @Param("p2pGrjbxx") P2PGrjbxx p2pGrjbxx);
	
	/**
	 * 根据id查询P2P个人基本信息
	 * @param id
	 * @return
	 */
	P2PGrjbxx selectP2PGrjbxxById(int id);
	
	/**
	 * 根据zjlx,zjhm,orgCode确定个人基本信息唯一性
	 * @param params
	 * @return
	 */
	int selectP2PGrjbxxCountForUnique(Map<String,Object> params);
	
	/**
	 * 添加P2P个人基本信息
	 * @param p2pGrjbxx
	 */
	void insertP2PGrjbxx(P2PGrjbxx p2pGrjbxx);
	
	/**
	 * 更新P2P个人基本信息
	 * @param p2pGrjbxx
	 */
	void updateP2PGrjbxx(P2PGrjbxx p2pGrjbxx);
	
	/**
	 * 根据id删除P2P个人基本信息
	 * @param id
	 */
	void deleteP2PGrjbxxById(int id);
	
}
