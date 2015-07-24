package cn.creditloans.core.service;

import java.util.List;

import cn.creditloans.core.dto.af.BaseActionNameDTO;
import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.tools.page.PageModel;

public interface BaseActionNameService {
	
	/**
	 * 分页展示ActionName信息
	 * @param baseActionNameDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<BaseActionNameDTO> getBaseActionNamePageList(BaseActionNameDTO baseActionNameDTO, int currentPage, int pageSize);
	
	/**
	 * 保存ActionName
	 * @param baseActionNameDTO
	 * @return
	 */
	int saveBaseActionName(BaseActionNameDTO baseActionNameDTO);
	
	/**
	 * 根据actionNameId返回ActionName信息
	 * @param actionNameId
	 * @return
	 */
	BaseActionNameDTO getBaseActionNameById(int actionNameId);
	
	/**
	 * 修改element信息
	 * @param baseElementDTO
	 */
	void updateBaseActionName(BaseActionNameDTO baseActionNameDTO);
	
	/**
	 * 根据actionNameId删除ActionName
	 * @param actionNameId
	 */
	void deleteBaseActionName(int actionNameId);
	/**
	 * 返回所有实体
	* 
	* @param 
	* @return
	 */
	List<BaseActionNameDTO> selectAllInfos();
	/**
	 * 
	* 检查名字是否唯一
	* @param 
	* @return
	 */
	boolean checkNameIsExist(String name);
}
