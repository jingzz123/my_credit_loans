package cn.creditloans.core.service;

import java.util.List;
import cn.creditloans.core.dto.enterprise.EnterpriseDTO;
import cn.creditloans.tools.page.PageModel;

public interface EnterpriseService {

	/**
	 * 获取所有企业List
	 * 
	 * @return
	 */
	List<EnterpriseDTO> getAllEnterpriseDtoList();

	/**
	 * 获取企业信息
	 * @param enterpriseDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<EnterpriseDTO> enterpriseInfoList(EnterpriseDTO enterpriseDto, int currentPage, int pageSize);

	/*
	 * 检查企业名称是否一存在
	 */
	boolean checkEnterpriseName(String name);

	/**
	 * 保存企业账户信息
	 * 
	 * @param enterprise
	 * @return
	 */
	boolean saveEnterpriseUserInfo(EnterpriseDTO enterprise);

	/**
	 * 更新企业账户信息
	 * 
	 * @param enterprise
	 * @return
	 */
	boolean updateEnterpriseUserInfo(EnterpriseDTO enterprise);

	/**
	 * 保存企业信息
	 * 
	 * @param enterprise
	 * @return
	 */
	boolean saveEnterpriseInfo(EnterpriseDTO enterprise);

	/**
	 * 更新企业信息
	 * 
	 * @param enterprise
	 * @return
	 */
	boolean updateEnterpriseInfo(EnterpriseDTO enterprise);

	/**
	 * 删除企业信息
	 * 
	 * @param id
	 * @return
	 */
	int deleteEnterpriseInfo(int id);

	/**
	 * 根据id获取企业账户信息
	 * 
	 * @param id
	 * @return
	 */
	EnterpriseDTO selectEnterpriseUserInfo(int id);

	/**
	 * 根据id获取企业信息
	 * 
	 * @param id
	 * @return
	 */
	EnterpriseDTO selectEnterpriseInfo(int id);

	/**
	 * 检查企业编号是否唯一
	 * 
	 * @param code
	 * @return
	 */
	boolean checkEnterpriseCodeIsExist(String code);

	/**
	 * 根据fid返回企业list
	 * 
	 * @param fid
	 * @return
	 */
	List<EnterpriseDTO> getEnterpriseDtoListByFid(int fid);

	/**
	 * 根据企业id返回该企业及其下面的所有子企业
	 * 
	 * @param enterpriseId
	 * @return
	 */
	List<EnterpriseDTO> getEnterpriseDtoListByEnterpriseId(int enterpriseId);

	/**
	 * 企业及其子企业
	 * 
	 * @param enterpriseDto
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageModel<EnterpriseDTO> subsidiaryInfoList(EnterpriseDTO enterpriseDto, int currentPage, int pageSize);

	/**
	 * 根据企业ID获取企业企业信息
	 * 
	 * @param id
	 * @return
	 */
	EnterpriseDTO getEnterpriseById(int id);

	/**
	 * 根据企业ID获取企业信息和子企业信息
	 * 
	 * @param id
	 * @return
	 */

	List<EnterpriseDTO> selectEnterpriseDtoList(int id);

	/**
	 * 序列号
	 * @return
	 */
	String sequenceNumber(); 
}
