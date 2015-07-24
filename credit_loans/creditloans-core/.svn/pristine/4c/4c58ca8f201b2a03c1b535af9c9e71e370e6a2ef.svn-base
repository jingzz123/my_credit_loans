package cn.creditloans.core.service;

import cn.creditloans.core.dto.db.DbDcmxxxDTO;
import cn.creditloans.core.dto.db.DbZcmxxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface DbZcmxxxService {

	/**
	 * 分页查询
	 * @param dbZcmxxxDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<DbZcmxxxDTO> getDbZcmxxxPageList(DbZcmxxxDTO dbZcmxxxDTO, int currentPage, int pageSize);

	/**
	 * 根据id返回追偿明细信息
	 * @param id
	 * @return
	 */
	DbZcmxxxDTO getDbZcmxxxById(int id);
	
	/**
	 * 判断追偿明细是否在该机构下唯一
	 * @param dbrzjlx
	 * @param dbrzjhm
	 * @param dkhthm
	 * @param orgCode
	 * @return
	 */
	//boolean dbBdbrxxIsOnly(String dbrzjlx, String dbrzjhm, String dkhthm, String orgCode);
	
	/**
	 * 保存追偿明细信息
	 * @param dbDcmxxxDTO
	 * @return
	 */
	int addDbZcmxxx(DbZcmxxxDTO dbZcmxxxDTO);
	
	/**
	 * 更新追偿明细信息
	 * @param dbDcmxxxDTO
	 */
	void editDbZcmxxx(DbZcmxxxDTO dbZcmxxxDTO);
	
	/**
	 * 根据id删除追偿明细信息
	 * @param id
	 */
	void deleteDbZcmxxxById(int id);
}
