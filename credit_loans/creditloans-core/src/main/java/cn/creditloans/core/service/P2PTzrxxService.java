package cn.creditloans.core.service;

import cn.creditloans.core.dto.p2p.P2PTzrxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface P2PTzrxxService {

	/**
	 * 分页查询
	 * @param p2pTzrxxDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<P2PTzrxxDTO> getP2PTzrxxPageList(P2PTzrxxDTO p2pTzrxxDto, int currentPage, int pageSize);

	/**
	 * 根据id返回P2P贷款投资人信息
	 * @param id
	 * @return
	 */
	P2PTzrxxDTO getP2PTzrxxById(int id);
	
	/**
	 * 判断P2P贷款投资人信息是否在该机构下唯一
	 * @param tzrzjlx
	 * @param tzrzjhm
	 * @param dkjbxxId
	 * @param orgCode
	 * @return
	 */
	boolean p2pTzrxxIsOnly(String tzrzjlx, String tzrzjhm, int dkjbxxId, String orgCode);
	
	/**
	 * 保存P2P贷款投资人信息
	 * @param p2pTzrxxDto
	 * @return
	 */
	int addP2PTzrxx(P2PTzrxxDTO p2pTzrxxDto);
	
	/**
	 * 更新P2P贷款投资人信息
	 * @param p2pTzrxxDto
	 */
	void editP2PTzrxx(P2PTzrxxDTO p2pTzrxxDto);
	
	/**
	 * 根据id删除P2P贷款投资人信息
	 * @param id
	 */
	void deleteP2PTzrxxById(int id);
}
