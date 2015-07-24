package cn.creditloans.core.dao.af;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.dao.share.AbstractEntityDao;
import cn.creditloans.core.entity.af.MetadataField;
import cn.creditloans.tools.page.PageModel;

/**
 * 对af_metadata_field的DAO，通用的方法进行了继承
 * @author Ash
 *
 */
public interface MetadataFieldDao extends AbstractEntityDao<MetadataField> {
	/**
	 * 
	* 查询主schema是否被关联
	* @param 
	* @return
	 */
	public int selectCountBySchemaId(int SchemaId);
}
