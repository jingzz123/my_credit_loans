package cn.creditloans.core.service;

import cn.creditloans.core.dto.p2p.P2PDksqxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface P2PDksqxxService {

	/**
	 * 分页查询
	 * @param p2pDksqxxDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<P2PDksqxxDTO> getP2PDksqxxPageList(P2PDksqxxDTO p2pDksqxxDto, int currentPage, int pageSize);

	/**
	 * 根据id返回P2P贷款申请信息
	 * @param id
	 * @return
	 */
	P2PDksqxxDTO getP2PDksqxxById(int id);
	
	/**
	 * 判断P2P贷款申请信息是否在该机构下唯一
	 * @param dksqh
	 * @param orgCode
	 * @return
	 */
	boolean p2pDksqxxIsOnly(String dksqh, String orgCode);
	
	/**
	 * 保存P2P贷款申请信息
	 * @param p2pDksqxxDto
	 * @return
	 */
	int addP2PDksqxx(P2PDksqxxDTO p2pDksqxxDto);
	
	/**
	 * 更新P2P贷款申请信息
	 * @param p2pDksqxxDto
	 */
	void editP2PDksqxx(P2PDksqxxDTO p2pDksqxxDto);
	
	/**
	 * 根据id删除P2P贷款申请信息
	 * @param id
	 */
	void deleteP2PDksqxxById(int id);
}
