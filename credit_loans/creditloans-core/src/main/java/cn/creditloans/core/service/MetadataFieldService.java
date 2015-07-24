package cn.creditloans.core.service;

import java.util.List;

import cn.creditloans.core.dto.af.MetadataFieldDTO;
import cn.creditloans.core.entity.af.MetadataField;
import cn.creditloans.tools.page.PageModel;

public interface MetadataFieldService {

	/**
	 * 分页展示field信息
	 * @param metadataFieldDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<MetadataFieldDTO> getMetadataFieldPageList(
			MetadataFieldDTO metadataFieldDTO, int currentPage, int pageSize);

	/**
	 * 保存field
	 * @param metadataFieldDTO
	 * @return
	 */
	int saveMetadataField(MetadataFieldDTO metadataFieldDTO);
	
	/**
	 * 根据fieldId返回field信息
	 * @param fieldId
	 * @return
	 */
	MetadataFieldDTO getMetadataFieldById(int fieldId);
	
	/**
	 * 修改field信息
	 * @param metadataFieldDTO
	 */
	void updateMetadataField(MetadataFieldDTO metadataFieldDTO);
	
	/**
	 * 根据fieldId删除field
	 * @param fieldId
	 */
	void deleteMetadataField(int fieldId);
	/**
	 * 
	* @description 获得全部metadataField
	* @param 
	* @return
	 */
	List<MetadataFieldDTO> getAllInfos();
	/**
	 * 
	* 检查名字是否唯一
	* @param 
	* @return
	 */
	boolean checkNameIsExist(String name);
}
