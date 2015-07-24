package cn.creditloans.core.service;

import java.util.List;

import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.core.dto.af.BaseVariableSimpleDTO;
import cn.creditloans.tools.page.PageModel;

public interface BaseVariableSimpleService {
	
	/**
	 * 分页展示VariableSimple信息
	 * @param baseVariableSimpleDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<BaseVariableSimpleDTO> getBaseVariableSimplePageList(BaseVariableSimpleDTO baseVariableSimpleDTO, int currentPage, int pageSize);
	
	/**
	 * 保存VariableSimple
	 * @param baseVariableSimpleDTO
	 * @return
	 */
	int saveBaseVariableSimple(BaseVariableSimpleDTO baseVariableSimpleDTO);
	
	/**
	 * 根据variableSimpleId返回VariableSimple信息
	 * @param variableSimpleId
	 * @return
	 */
	BaseVariableSimpleDTO getBaseVariableSimpleById(int variableSimpleId);
	
	/**
	 * 修改VariableSimple信息
	 * @param baseVariableSimpleDTO
	 */
	void updateBaseVariableSimple(BaseVariableSimpleDTO baseVariableSimpleDTO);
	
	/**
	 * 根据variableSimpleId删除VariableSimple
	 * @param variableSimpleId
	 */
	void deleteBaseVariableSimple(int variableSimpleId);
	/**
	 * 
	* @description 获取所有实体
	* @param 
	* @return
	 */
	List<BaseVariableSimpleDTO> getAllInfos();
	/**
	 * 
	* 检查名字是否唯一
	* @param 
	* @return
	 */
	boolean checkNameIsExist(String name);
	/**
	 * 通过名字获得实体
	* 
	* @param 
	* @return
	 */
	List<BaseVariableSimpleDTO> getBaseVariableSimpleByName(String name);
}
