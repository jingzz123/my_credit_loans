package cn.creditloans.core.service;

import cn.creditloans.core.dto.p2p.P2PDbxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface P2PDbxxService {

	/**
	 * 分页查询
	 * @param p2pDbxxDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<P2PDbxxDTO> getP2PDbxxPageList(P2PDbxxDTO p2pDbxxDto, int currentPage, int pageSize);

	/**
	 * 根据id返回P2P贷款担保信息
	 * @param id
	 * @return
	 */
	P2PDbxxDTO getP2PDbxxById(int id);
	
	/**
	 * 判断P2P贷款担保信息是否在该机构下唯一
	 * @param dbrzjlx
	 * @param dbrzjhm
	 * @param dkhthm
	 * @param orgCode
	 * @return
	 */
	boolean p2pDbxxIsOnly(String dbrzjlx, String dbrzjhm, int dkjbxxId, String orgCode);
	
	/**
	 * 保存P2P贷款担保信息
	 * @param p2pDbxxDto
	 * @return
	 */
	int addP2PDbxx(P2PDbxxDTO p2pDbxxDto);
	
	/**
	 * 更新P2P贷款担保信息
	 * @param p2pDbxxDto
	 */
	void editP2PDbxx(P2PDbxxDTO p2pDbxxDto);
	
	/**
	 * 根据id删除P2P贷款担保信息
	 * @param id
	 */
	void deleteP2PDbxxById(int id);
}
