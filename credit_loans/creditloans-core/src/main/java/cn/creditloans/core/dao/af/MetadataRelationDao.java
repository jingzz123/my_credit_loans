package cn.creditloans.core.dao.af;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.dao.share.AbstractEntityDao;
import cn.creditloans.core.entity.af.MetadataRelation;
import cn.creditloans.core.entity.af.MetadataSchema;
import cn.creditloans.tools.page.PageModel;

/**
 * 对af_metadata_relation的DAO，通用的方法进行了继承
 * 这里必须新增一些方法
 * @author Ash
 *
 */
public interface MetadataRelationDao extends AbstractEntityDao<MetadataRelation> {
	
	/**
	 * 根据mainSchemaId获取所有metadataRelation实体信息
	 * @param id
	 * @return
	 */
	public List<MetadataRelation> selectByMainSchemaId(int mainSchemaId);
	/**
	 * 根据sequence查询sequence是否已存在
	 * @param sequence
	 * @return
	 */
	int selectSequence(Map<String, Object> param);
	
	/**
	 * 
	* @description 检查名字是否唯一
	* @param 
	* @return
	 */
	public int selectChildSchemaName(Map<String, Object> param);
	
	/**
	 * 根据mainSchemaId，childSchemaId返回metadataRelation
	 * @param mainSchemaId,childSchemaId
	 */
	public MetadataRelation selectByIds(Map<String, Object> param);
	/**
	 * 根据mainSchemaId，childSchemaId 删除relation
	 */
	
	public void deleteByIds(Map<String, Object> param);
}
