package cn.creditloans.core.service;

import java.util.List;
import java.util.Map;

import cn.creditloans.core.dto.af.BaseVariableTransactionDTO;
import cn.creditloans.tools.page.PageModel;

public interface BaseVariableTransactionService {
	
	/**
	 * 分页展示VariableTransaction信息
	 * @param baseVariableTransactionDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<BaseVariableTransactionDTO> getBaseVariableTransactionPageList(BaseVariableTransactionDTO baseVariableTransactionDTO, int currentPage, int pageSize);
	
	/**
	 * 保存VariableTransaction
	 * @param baseVariableTransactionDTO
	 * @return
	 */
	int saveBaseVariableTransaction(BaseVariableTransactionDTO baseVariableTransactionDTO);
	
	/**
	 * 根据variableTransactionId返回VariableTransaction信息
	 * @param variableTransactionId
	 * @return
	 */
	Map<String, Object> getBaseVariableTransactionById(int variableTransactionId);
	
	/**
	 * 修改VariableTransaction信息
	 * @param baseVariableTransactionDTO
	 */
	void updateBaseVariableTransaction(BaseVariableTransactionDTO baseVariableTransactionDTO);
	
	/**
	 * 根据variableTransactionId删除VariableTransaction
	 * @param variableTransactionId
	 */
	void deleteBaseVariableTransaction(int variableTransactionId);
	/**
	 * 
	* 检查名字是否唯一
	* @param 
	* @return
	 */
	boolean checkNameIsExist(String name);
	/**
	 * 获得 所有实体
	* 
	* @param 
	* @return
	 */
	List<BaseVariableTransactionDTO> getAllInfos();
	/**
	 * 通过名字获得所有实体
	* 
	* @param 
	* @return
	 */
	public List<BaseVariableTransactionDTO> getBaseVariableTransactionByName(String name);
	/**
	 * 获得所有关联实体
	* 
	* @param 
	* @return
	 */
	Map<String, Object> getAllRelationEntity(); 
}
