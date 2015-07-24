package cn.creditloans.core.service;

import cn.creditloans.core.dto.p2p.P2PDkjbxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface P2PDkjbxxService {

	/**
	 * 分页查询
	 * @param p2pDkjbxxDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<P2PDkjbxxDTO> getP2PDkjbxxPageList(P2PDkjbxxDTO p2pDkjbxxDto, int currentPage, int pageSize);

	/**
	 * 根据id返回P2P贷款基本信息
	 * @param id
	 * @return
	 */
	P2PDkjbxxDTO getP2PDkjbxxById(int id);
	
	/**
	 * 判断P2P贷款基本信息是否在该机构下唯一
	 * @param dkhthm
	 * @param ywh
	 * @param jsyhkrq
	 * @param orgCode
	 * @return
	 */
	boolean p2pDkjbxxIsOnly(String dkhthm, String ywh,String jsyhkrq, String orgCode);
	
	/**
	 * 保存P2P贷款基本信息
	 * @param p2pDkjbxxDto
	 * @return
	 */
	int addP2PDkjbxx(P2PDkjbxxDTO p2pDkjbxxDto);
	
	/**
	 * 更新P2P贷款基本信息
	 * @param p2pDkjbxxDto
	 */
	void editP2PDkjbxx(P2PDkjbxxDTO p2pDkjbxxDto);
	
	/**
	 * 根据id删除P2P贷款基本信息
	 * @param id
	 */
	void deleteP2PDkjbxxById(int id);
}
