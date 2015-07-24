package cn.creditloans.core.service;

import cn.creditloans.core.dto.db.DbBfjnmxxxDTO;
import cn.creditloans.core.dto.db.DbZcmxxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface DbBfjnmxxxService {

	/**
	 * 分页查询
	 * @param dbBnjfmxxxDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<DbBfjnmxxxDTO> getDbBfjnmxxxPageList(DbBfjnmxxxDTO dbBfjnmxxxDTO, int currentPage, int pageSize);

	/**
	 * 根据id返回保费缴纳明细信息
	 * @param id
	 * @return
	 */
	DbBfjnmxxxDTO getDbBfjnmxxxById(int id);
	
	/**
	 * 判断保费缴纳明细是否在该机构下唯一
	 * @param dbrzjlx
	 * @param dbrzjhm
	 * @param dkhthm
	 * @param orgCode
	 * @return
	 */
	//boolean dbBdbrxxIsOnly(String dbrzjlx, String dbrzjhm, String dkhthm, String orgCode);
	
	/**
	 * 保存保费缴纳明细信息
	 * @param dbBfjnmxxxDTO
	 * @return
	 */
	int addDbBfjnmxxx(DbBfjnmxxxDTO dbBfjnmxxxDTO);
	
	/**
	 * 更新保费缴纳明细信息
	 * @param dbBfjnmxxxDTO
	 */
	void editDbBfjnmxxx(DbBfjnmxxxDTO dbBfjnmxxxDTO);
	
	/**
	 * 根据id删除保费缴纳明细信息
	 * @param id
	 */
	void deleteDbBfjnmxxxById(int id);
}
