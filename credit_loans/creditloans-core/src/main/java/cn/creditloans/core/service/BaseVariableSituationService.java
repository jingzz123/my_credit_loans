package cn.creditloans.core.service;

import java.util.List;

import cn.creditloans.core.dto.af.BaseVariableSituationDTO;
import cn.creditloans.tools.page.PageModel;

public interface BaseVariableSituationService {
	
	/**
	 * 分页展示VariableSituation信息
	 * @param baseVariableSituationDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<BaseVariableSituationDTO> getBaseVariableSituationPageList(BaseVariableSituationDTO baseVariableSituationDTO, int currentPage, int pageSize);
	
	/**
	 * 保存VariableSituation
	 * @param baseVariableSituationDTO
	 * @return
	 */
	int saveBaseVariableSituation(BaseVariableSituationDTO baseVariableSituationDTO);
	
	/**
	 * 根据variableSituationId返回VariableSituation信息
	 * @param variableSituationId
	 * @return
	 */
	BaseVariableSituationDTO getBaseVariableSituationById(int variableSituationId);
	
	/**
	 * 修改VariableSituation信息
	 * @param baseVariableSituationDTO
	 */
	void updateBaseVariableSituation(BaseVariableSituationDTO baseVariableSituationDTO);
	
	/**
	 * 根据variableSituationId删除VariableSituation
	 * @param variableSituationId
	 */
	void deleteBaseVariableSituation(int variableSituationId);
	/**
	 * 获得所有实体
	* 
	* @param 
	* @return
	 */
	public List<BaseVariableSituationDTO> getAllInfos();
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
	public List<BaseVariableSituationDTO> getBaseVariableSituationByName(String name);
}
