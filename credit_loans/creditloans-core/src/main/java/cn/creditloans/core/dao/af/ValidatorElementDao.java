package cn.creditloans.core.dao.af;

import cn.creditloans.core.dao.share.AbstractEntityDao;
import cn.creditloans.core.entity.af.ValidatorElement;

/**
 * 对af_validator的DAO，通用的方法进行了继承
 * @author Ash
 *
 */
public interface ValidatorElementDao extends AbstractEntityDao<ValidatorElement> {
	/**
	 * 
	* 查询包是否被关联
	* @param 
	* @return
	 */
	public int selectCountByBaseId(int baseId);
}
