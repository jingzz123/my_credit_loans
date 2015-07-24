package cn.creditloans.core.service;

import java.util.List;

import cn.creditloans.core.dto.af.BaseVariableExpressionDTO;
import cn.creditloans.core.dto.af.BaseVariableSituationDTO;
import cn.creditloans.tools.page.PageModel;

public interface BaseVariableExpressionService {
	
	/**
	 * 分页展示VariableExpression信息
	 * @param baseVariableExpressionDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<BaseVariableExpressionDTO> getBaseVariableExpressionPageList(BaseVariableExpressionDTO baseVariableExpressionDTO, int currentPage, int pageSize);
	
	/**
	 * 保存VariableExpression
	 * @param baseVariableExpressionDTO
	 * @return
	 */
	int saveBaseVariableExpression(BaseVariableExpressionDTO baseVariableExpressionDTO);
	
	/**
	 * 根据variableExpressionId返回VariableExpression信息
	 * @param variableExpressionId
	 * @return
	 */
	BaseVariableExpressionDTO getBaseVariableExpressionById(int variableExpressionId);
	
	/**
	 * 修改VariableExpression信息
	 * @param baseVariableExpressionDTO
	 */
	void updateBaseVariableExpression(BaseVariableExpressionDTO baseVariableExpressionDTO);
	
	/**
	 * 根据variableExpressionId删除VariableExpression
	 * @param variableExpressionId
	 */
	void deleteBaseVariableExpression(int variableExpressionId);
	/**
	 * 获得所有实体
	* 
	* @param 
	* @return
	 */
	public List<BaseVariableExpressionDTO> getAllInfos();
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
	public List<BaseVariableExpressionDTO> getBaseVariableExpressionByName(String name);
}
