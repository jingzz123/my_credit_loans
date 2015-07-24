package cn.creditloans.core.service;

import cn.creditloans.core.dto.db.DbFdbrxxDTO;
import cn.creditloans.core.dto.db.DbZqrjzhtxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface DbFdbrxxService {

	/**
	 * 分页查询
	 * @param dbFdbrxxDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<DbFdbrxxDTO> getDbFdbrxxPageList(DbFdbrxxDTO dbFdbrxxDTO, int currentPage, int pageSize);

	/**
	 * 根据id返回反担保人信息
	 * @param id
	 * @return
	 */
	DbFdbrxxDTO getDbFdbrxxById(int id);
	
	/**
	 * 判断反担保人是否在该机构下唯一
	 * @param dbrzjlx
	 * @param dbrzjhm
	 * @param dkhthm
	 * @param orgCode
	 * @return
	 */
	//boolean dbBdbrxxIsOnly(String dbrzjlx, String dbrzjhm, String dkhthm, String orgCode);
	
	/**
	 * 保存反担保人信息
	 * @param dbFdbrxxDTO
	 * @return
	 */
	int addDbFdbrxx(DbFdbrxxDTO dbFdbrxxDTO);
	
	/**
	 * 更新反担保人信息
	 * @param dbFdbrxxDTO
	 */
	void editDbFdbrxx(DbFdbrxxDTO dbFdbrxxDTO);
	
	/**
	 * 根据id删除反担保人信息
	 * @param id
	 */
	void deleteDbFdbrxxById(int id);
}
