package cn.creditloans.core.service;

import cn.creditloans.core.dto.p2p.P2PGrjbxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface P2PGrjbxxService {

	/**
	 * 分页查询
	 * @param p2pGrjbxxDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<P2PGrjbxxDTO> getP2PGrjbxxPageList(P2PGrjbxxDTO p2pGrjbxxDto, int currentPage, int pageSize);

	/**
	 * 根据id返回P2P个人基本信息
	 * @param id
	 * @return
	 */
	P2PGrjbxxDTO getP2PGrjbxxById(int id);
	
	/**
	 * 判断P2P个人基本信息是否在该机构下唯一
	 * @param zjlx
	 * @param zjhm
	 * @param orgCode
	 * @return
	 */
	boolean p2pGrjbxxIsOnly(String zjlx, String zjhm, String orgCode);
	
	/**
	 * 保存P2P个人基本信息
	 * @param p2pGrjbxxDto
	 * @return
	 */
	int addP2PGrjbxx(P2PGrjbxxDTO p2pGrjbxxDto);
	
	/**
	 * 更新P2P个人基本信息
	 * @param p2pGrjbxxDto
	 */
	void editP2PGrjbxx(P2PGrjbxxDTO p2pGrjbxxDto);
	
	/**
	 * 根据id删除P2P个人基本信息
	 * @param id
	 */
	void deleteP2PGrjbxxById(int id);
}
