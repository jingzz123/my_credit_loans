package cn.creditloans.core.service;

import cn.creditloans.core.dto.db.DbZqrjzhtxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface DbZqrjzhtxxService {

	/**
	 * 分页查询
	 * @param dbZqrjzhtxxDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<DbZqrjzhtxxDTO> getDbZqrjzhtxxPageList(DbZqrjzhtxxDTO dbZqrjzhtxxDTO, int currentPage, int pageSize);

	/**
	 * 根据id返回债权人及主合同信息
	 * @param id
	 * @return
	 */
	DbZqrjzhtxxDTO getDbZqrjzhtxxById(int id);
	
	/**
	 * 判断债权人及主合同是否在该机构下唯一
	 * @param dbrzjlx
	 * @param dbrzjhm
	 * @param dkhthm
	 * @param orgCode
	 * @return
	 */
	//boolean dbBdbrxxIsOnly(String dbrzjlx, String dbrzjhm, String dkhthm, String orgCode);
	
	/**
	 * 保存债权人及主合同信息
	 * @param dbZqrjzhtxxDTO
	 * @return
	 */
	int addDbZqrjzhtxx(DbZqrjzhtxxDTO dbZqrjzhtxxDTO);
	
	/**
	 * 更新债权人及主合同信息
	 * @param dbBdbrxxDTO
	 */
	void editDbZqrjzhtxx(DbZqrjzhtxxDTO dbZqrjzhtxxDTO);
	
	/**
	 * 根据id删除债权人及主合同信息
	 * @param id
	 */
	void deleteDbZqrjzhtxxById(int id);
}
