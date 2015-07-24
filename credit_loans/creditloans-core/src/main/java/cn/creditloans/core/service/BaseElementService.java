package cn.creditloans.core.service;

import java.util.List;
import java.util.Map;

import cn.creditloans.core.dto.af.BaseElementDTO;
import cn.creditloans.tools.page.PageModel;

public interface BaseElementService {
	
	/**
	 * 分页展示element信息
	 * @param baseElementDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<BaseElementDTO> getBaseElementPageList(BaseElementDTO baseElementDTO, int currentPage, int pageSize);
	
	/**
	 * 保存element
	 * @param baseElementDTO
	 * @return
	 */
	int saveBaseElement(BaseElementDTO baseElementDTO);
	
	/**
	 * 根据elementId返回element信息
	 * @param elementId
	 * @return
	 */
	Map<String, Object> getBaseElementById(int elementId);
	
	/**
	 * 修改element信息
	 * @param baseElementDTO
	 */
	void updateBaseElement(BaseElementDTO baseElementDTO);
	
	/**
	 * 根据elementId删除element
	 * @param elementId
	 */
	int deleteBaseElement(int elementId);
	/**
	 * 得到所有实体
	* 
	* @param 
	* @return
	 */
	List<BaseElementDTO> selectAllInfos();
	
	/**
	 * 获得所有相关实体
	* 
	* @param 
	* @return
	 */
	Map<String, Object> getAllRelationEntity();
	/**
	 * 
	* 检查名字是否唯一
	* @param 
	* @return
	 */
	boolean checkNameIsExist(String name);
}
