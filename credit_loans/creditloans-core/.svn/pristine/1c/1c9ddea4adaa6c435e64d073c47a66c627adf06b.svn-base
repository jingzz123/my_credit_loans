package cn.creditloans.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.creditloans.core.entity.enterprise.Enterprise;
import cn.creditloans.core.entity.enterprise.EnterpriseOwnMenu;
import cn.creditloans.tools.page.PageModel;

/**
 * 企业信息Dao
 * @author Austin
 *
 */
public interface EnterpriseDao {
	
	/**
	 * 根据id获取企业对象
	 * @param id
	 * @return
	 */
	Enterprise selectEnterpriseById(int id);
	
	/**
	 * 根据id获取级联查询role_menu
	 * @param id
	 * @return
	 */
	Enterprise selectCascadeEnterpriseById(int id);
	
	/**
	 * 获取所有企业list
	 * @return
	 */
	List<Enterprise> selectEnterpriseList();
	
	/**
	 * 根据idList获取相应的企业list
	 * @param idList
	 * @return
	 */
	List<Enterprise> selectEnterprisesByIds(List<Integer> idList);
	
	/**
	 * 根据fid获取相应子企业list
	 * @param fid
	 * @return
	 */
	List<Enterprise> selectEnterpriseListByFid(int fid);
	
	/**
	 * 根据企业id返回该企业及其下面的所有子企业
	 * @param id
	 * @return
	 */
	List<Enterprise> selectEnterprisesListById(int id);
	
	/**
	 * 根据code获取企业对象
	 * @param code
	 * @return
	 */
	Enterprise selectEnterpriseByCode(String code);
	
	/**
	 * 根据id获取子企业个数
	 * @param enterpriseId
	 * @return
	 */
	int selectEnterpriseCountByFid(int id);
	
	/**
	 * 根据查询对象，获取相应企业信息个数
	 * @param enterprise
	 * @return
	 */
	int selectEnterprisePageCount(Enterprise enterprise);
	
	/**
	 * 根据查询对象和分页条件，分页获取相应企业list
	 * @param pm
	 * @param enterprise
	 * @return
	 */
	List<Enterprise> selectEnterprisePageList(@Param("pm") PageModel<?> pm, @Param("enterprise") Enterprise enterprise);
	
	/**
	 * 根据id查询企业code
	 * @param id
	 * @return
	 */
	Enterprise selectEnterpriseCodeById(int id);
	
	/**
	 * 添加企业对象
	 * @param enterprise
	 */
	void insertEnterprise(Enterprise enterprise);
	
	/**
	 * 更新企业对象
	 * @param enterprise
	 */
	void updateEnterprise(Enterprise enterprise);
	
	/**
	 * 删除企业对象
	 * @param enterpriseId
	 */
	void deleteEnterpriseById(int id);
	
	/**
	 * 根据企业名称查询企业是否已存在
	 * @param name 企业名称
	 * @param id   企业信息在数据库中唯一标识
	 * @return
	 */
	int selectEnterpriseName(Map<String, Object> param);
	
	/**
	 * 企业信息是否和用户已做关联
	 * @param id
	 * @return
	 */
	int selectEnterpriseUser(int id);
	
	/**
	 * 查询企业及子企业数量
	 * @param id
	 * @return
	 */
	int selectSubsidiaryInfoCount(Enterprise enterprise);
	
	/**
	 * 查询企业和子企业信息
	 * @param pm
	 * @param enterprise
	 * @return
	 */
	List<Enterprise> selectSubsidiaryPageList(@Param("pm") PageModel<?> pm, @Param("enterprise") Enterprise enterprise);
	
	/**
	 * 根据ID获取企业信息
	 * @param id
	 * @return
	 * FIXME : 方法名体现查询条件byId
	 */
	Enterprise selectSubsidiaryById(int id);
	
	/**
	 * 批量插入EnterpriseOwnMenu
	 * @param enterpriseOwnMenuList
	 */
	void batchInsertEnterpriseOwnMenu(List<EnterpriseOwnMenu> enterpriseOwnMenuList);
	
	/**
	 * 根据企业id批量删除EnterpriseOwnMenu
	 * @param enterpriseId
	 */
	void deleteEnterpriseOwnMenuByEnterpriseId(int enterpriseId);
}
