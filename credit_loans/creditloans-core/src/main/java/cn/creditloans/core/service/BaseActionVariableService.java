package cn.creditloans.core.service;

import java.util.List;
import java.util.Map;

import cn.creditloans.core.dto.af.BaseActionVariableDTO;
import cn.creditloans.tools.page.PageModel;

public interface BaseActionVariableService {
	
	/**
	 * 分页展示ActionVariable信息
	 * @param baseActionVariableDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<BaseActionVariableDTO> getBaseActionVariablePageList(BaseActionVariableDTO baseActionVariableDTO, int currentPage, int pageSize);
	
	/**
	 * 保存ActionName
	 * @param baseActionNameDTO
	 * @return
	 */
	int saveBaseActionVariable(BaseActionVariableDTO baseActionVariableDTO);
	
	/**
	 * 根据actionVariableId返回ActionVariable信息
	 * @param actionVariableId
	 * @return
	 */
	 Map<String, Object> getBaseActionVariableById(int actionVariableId);
	
	/**
	 * 修改Variable信息
	 * @param baseActionVariableDTO
	 */
	void updateBaseActionVariable(BaseActionVariableDTO baseActionVariableDTO);
	
	/**
	 * 根据actionVariableId删除ActionVariable
	 * @param actionVariableId
	 */
	void deleteBaseActionVariable(int actionVariableId);
	/**
	 * 
	* 获得所有实体
	* @param 
	* @return
	 */
	List<BaseActionVariableDTO> selectAllInfos();
	
	/**
	 * 
	* 检查名字是否唯一
	* @param 
	* @return
	 */
	boolean checkNameIsExist(String name);
	/**
	 * 获得所有的简单变量和包
	* 
	* @param 
	* @return
	 */
	Map<String, Object> getAllElementAndSimple();
}
