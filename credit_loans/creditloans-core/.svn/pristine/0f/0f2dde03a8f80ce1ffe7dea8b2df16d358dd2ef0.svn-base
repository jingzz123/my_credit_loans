package cn.creditloans.core.service;

import cn.creditloans.core.dto.db.DbjbxxDTO;
import cn.creditloans.core.dto.p2p.P2PDkjbxxDTO;
import cn.creditloans.tools.page.PageModel;

public interface DbjbxxService {

	/**
	 * 分页查询
	 * @param dbjbxxDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<DbjbxxDTO> getDbjbxxPageList(DbjbxxDTO dbjbxxDto,
			int currentPage, int pageSize);

	/**
	 * 根据id返回担保基本信息
	 * @param id
	 * @return
	 */
	DbjbxxDTO getDbjbxxById(int id);
	
	/**
	 * 判断担保基本信息是否在该机构下唯一
	 * @param dkhthm
	 * @param ywh
	 * @param jsyhkrq
	 * @param orgCode
	 * @return
	 */
	
	/**
	 * 保存担保基本信息
	 * @param dbjbxxDto
	 * @return
	 */
	int addDbjbxx(DbjbxxDTO dbjbxxDto);
	
	/**
	 * 更新担保基本信息
	 * @param dbjbxxDto
	 */
	void editDbjbxx(DbjbxxDTO dbjbxxDto);
	
	/**
	 * 根据id删除担保基本信息
	 * @param id
	 */
	int deleteDbjbxxById(int id);

	
}
