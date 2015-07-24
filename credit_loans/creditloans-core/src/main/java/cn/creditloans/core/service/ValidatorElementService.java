package cn.creditloans.core.service;

import java.util.Map;

import cn.creditloans.core.dto.af.ValidatorElementDTO;
import cn.creditloans.tools.page.PageModel;

public interface ValidatorElementService {
	
	/**
	 * 分页展示ValidatorElement信息
	 * @param validatorElementDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<ValidatorElementDTO> getValidatorElementPageList(ValidatorElementDTO validatorElementDTO, int currentPage, int pageSize);
	
	/**
	 * 保存ValidatorElement
	 * @param ValidatorElementDTO
	 * @return
	 */
	int saveValidatorElement(ValidatorElementDTO validatorElementDTO);
	
	/**
	 * 根据ValidatorElementId返回ValidatorElement信息
	 * @param ValidatorElementId
	 * @return
	 */
	Map<String, Object> getValidatorElementById(int validatorElementId);
	
	/**
	 * 修改ValidatorElement信息
	 * @param ValidatorElementDTO
	 */
	void updateValidatorElement(ValidatorElementDTO validatorElementDTO);
	
	/**
	 * 根据ValidatorElementId删除ValidatorElement
	 * @param validatorElementId
	 */
	void deleteValidatorElement(int validatorElementId);
	/**
	 * 
	* 检查名字是否唯一
	* @param 
	* @return
	 */
	boolean checkNameIsExist(String name);
	/**
	 * 获得相关联的所有实体
	* 
	* @param 
	* @return
	 */
	Map<String, Object> getAllInfos();
	
}
