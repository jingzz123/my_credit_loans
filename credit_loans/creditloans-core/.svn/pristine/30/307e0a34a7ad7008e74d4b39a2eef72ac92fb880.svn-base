package cn.creditloans.core.service;

import cn.creditloans.core.dto.db.DbDcmxxxDTO;
import cn.creditloans.core.dto.db.DbFdbrxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface DbDcmxxxService {

	/**
	 * 分页查询
	 * @param dbDcmxxxDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<DbDcmxxxDTO> getDbDcmxxxPageList(DbDcmxxxDTO dbDcmxxxDTO, int currentPage, int pageSize);

	/**
	 * 根据id返回代偿明细信息
	 * @param id
	 * @return
	 */
	DbDcmxxxDTO getDbDcmxxxById(int id);
	
	/**
	 * 判断代偿明细是否在该机构下唯一
	 * @param dbrzjlx
	 * @param dbrzjhm
	 * @param dkhthm
	 * @param orgCode
	 * @return
	 */
	//boolean dbBdbrxxIsOnly(String dbrzjlx, String dbrzjhm, String dkhthm, String orgCode);
	
	/**
	 * 保存代偿明细信息
	 * @param dbDcmxxxDTO
	 * @return
	 */
	int addDbDcmxxx(DbDcmxxxDTO dbDcmxxxDTO);
	
	/**
	 * 更新代偿明细信息
	 * @param dbDcmxxxDTO
	 */
	void editDbDcmxxx(DbDcmxxxDTO dbDcmxxxDTO);
	
	/**
	 * 根据id删除代偿明细信息
	 * @param id
	 */
	void deleteDbDcmxxxById(int id);
}
