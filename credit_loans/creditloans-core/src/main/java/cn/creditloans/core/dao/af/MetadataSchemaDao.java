package cn.creditloans.core.dao.af;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.dao.share.AbstractEntityDao;
import cn.creditloans.core.entity.af.MetadataRelation;
import cn.creditloans.core.entity.af.MetadataSchema;
import cn.creditloans.tools.page.PageModel;

/**
 * 对af_metadata_schema的DAO，通用的方法进行了继承
 * @author Ash
 *
 */
public interface MetadataSchemaDao extends AbstractEntityDao<MetadataSchema> {
	/**
	 * 
	* @description 查询所有普通schema
	* @param 
	* @return
	 */
	public List<MetadataSchema> selectChildSchema();
	
	/**
	 * 
	* @description 根据id集合查询metadataSchema
	* @param 
	* @return
	 */
	public List<MetadataSchema> selectSchemaListByList(@Param("metadataRelationList") List<MetadataRelation> metadataRelationList);
	/**
	 * 
	* 查询所有的NestSchema
	* @param 
	* @return
	 */
	public List<MetadataSchema> selectMainSchema();
	/**
	 * 
	* 批量插入关系
	* @param 
	* @return
	 */
	public void batchInsertMetadataRelation(List<MetadataRelation> metadataRelationList);
	/**
	 * 
	* 通过子schemaId删除关系
	* @param 
	* @return
	 */
	public void deleteMetadataRelationByChildSchemaId(int childSchemaId);
	/**
	 * 
	* 通过主schemaId删除关系
	* @param 
	* @return
	 */
	public void deleteMetadataRelationByMainSchemaId(int mainSchemaId);
	
	/**
	 * 
	* 根据id查询单个子schema
	* @param 
	* @return
	 */
	public MetadataSchema selectChildById(int schemaId);
	/**
	 * 
	* 查询主schema是否被关联
	* @param 
	* @return
	 */
	public int selectCountBySchemaId(int SchemaId);
	
}
