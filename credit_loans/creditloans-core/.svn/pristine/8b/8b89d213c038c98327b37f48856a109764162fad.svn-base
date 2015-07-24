package cn.creditloans.core.service;

import cn.creditloans.core.dto.p2p.P2PTsjyxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface P2PTsjyxxService {

	/**
	 * 分页查询
	 * @param p2pTsjyxxDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<P2PTsjyxxDTO> getP2PTsjyxxPageList(P2PTsjyxxDTO p2pTsjyxxDto, int currentPage, int pageSize);

	/**
	 * 根据id返回P2P特殊交易信息
	 * @param id
	 * @return
	 */
	P2PTsjyxxDTO getP2PTsjyxxById(int id);
	
	/**
	 * 判断P2P特殊交易信息是否在该机构下唯一
	 * @param zjlx
	 * @param zjhm
	 * @param fsrq
	 * @param orgCode
	 * @return
	 */
	boolean p2pTsjyxxIsOnly(String zjlx, String zjhm, String fsrq, String orgCode);
	
	/**
	 * 保存P2P特殊交易信息
	 * @param p2pTsjyxxDto
	 * @return
	 */
	int addP2PTsjyxx(P2PTsjyxxDTO p2pTsjyxxDto);
	
	/**
	 * 更新P2P特殊交易信息
	 * @param p2pTsjyxxDto
	 */
	void editP2PTsjyxx(P2PTsjyxxDTO p2pTsjyxxDto);
	
	/**
	 * 根据id删除P2P特殊交易信息
	 * @param id
	 */
	void deleteP2PTsjyxxById(int id);	
}
