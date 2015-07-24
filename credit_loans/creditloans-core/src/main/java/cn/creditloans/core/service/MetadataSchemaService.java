package cn.creditloans.core.service;

import java.util.List;
import java.util.Map;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import cn.creditloans.core.dto.af.MetadataSchemaDTO;
import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.tools.page.PageModel;

public interface MetadataSchemaService {
	
	/**
	 * 分页展示schema信息
	 * @param metadataSchemaDTO
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<MetadataSchemaDTO> getMetadataSchemaPageList(MetadataSchemaDTO metadataSchemaDTO, int currentPage, int pageSize);
	
	/**
	 * 保存schema
	 * @param metadataSchemaDTO
	 * @return
	 */
	int saveMetadataSchema(MetadataSchemaDTO metadataSchemaDTO);
	
	/**
	 * 根据schemaId返回schema信息
	 * @param schemaId
	 * @return
	 */
	Map<String, Object> getMetadataSchemaById(int schemaId, String isNested);
	
	/**
	 * 修改schema信息
	 * @param metadataSchemaDTO
	 */
	void updateMetadataSchema(MetadataSchemaDTO metadataSchemaDTO);
	
	/**
	 * 根据schemaId删除schema
	 * @param schemaId
	 */
	int deleteMetadataSchema(int schemaId, String isNested);
	
	/**
	 * 查询所有的schema
	 */
	 List<MetadataSchemaDTO> selectAllInfos();
	 /**
	  * 查询所有NestSchema
	  */
	 List<MetadataSchemaDTO> selectMainSchema();
	 /**
	  * 
	 * 检查名字唯一性
	 * @param 
	 * @return
	  */
	 boolean checkNameIsExist(String name);
	 /**
		 * 获得所有相关实体
		* 
		* @param 
		* @return
		 */
	Map<String, Object> getAllRelationEntity();
}
