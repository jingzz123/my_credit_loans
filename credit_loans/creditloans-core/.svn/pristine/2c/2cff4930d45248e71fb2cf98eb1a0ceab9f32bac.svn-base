package cn.creditloans.core.service;

import cn.creditloans.core.dto.db.DbBdbrxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface DbBdbrxxService {

	/**
	 * 分页查询
	 * @param dbBdbrxxDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<DbBdbrxxDTO> getDbBdbrxxPageList(DbBdbrxxDTO dbBdbrxxDTO, int currentPage, int pageSize);

	/**
	 * 根据id返回被担保人信息
	 * @param id
	 * @return
	 */
	DbBdbrxxDTO getDbBdbrxxById(int id);
	
	/**
	 * 判断被担保人是否在该机构下唯一
	 * @param dbrzjlx
	 * @param dbrzjhm
	 * @param dkhthm
	 * @param orgCode
	 * @return
	 */
	//boolean dbBdbrxxIsOnly(String dbrzjlx, String dbrzjhm, String dkhthm, String orgCode);
	
	/**
	 * 保存被担保人信息
	 * @param dbBdbrxxDto
	 * @return
	 */
	int addDbBdbrxx(DbBdbrxxDTO dbBdbrxxDTO);
	
	/**
	 * 更新被担保人信息
	 * @param dbBdbrxxDTO
	 */
	void editDbBdbrxx(DbBdbrxxDTO dbBdbrxxDTO);
	
	/**
	 * 根据id删除被担保人信息
	 * @param id
	 */
	void deleteDbBdbrxxById(int id);
}
