package cn.creditloans.core.dao;

import java.util.List;

import cn.creditloans.core.dto.enterprise.QueryCompareCondition;
import cn.creditloans.core.entity.enterprise.BlackInfo;

/**
 * 黑名单 DAO
 * @author Administrator
 *
 */
public interface BlackInfoDao {
	
	/**
	 * 保存 BlackInfo
	 * @param blackInfo
	 */
	public void saveBlackInfo(BlackInfo blackInfo);
	
	/**
	 * 根据姓名查询黑名单信息
	 * @param name
	 * @return
	 */
	public List<BlackInfo> selectBlackInfoByName(String name);
	
	/**
	 * 根据身份证查询
	 * @param idNumber
	 * @return
	 */
	public List<BlackInfo> selectBlackInfoByIdNumber(String idNumber);
	
	/**
	 * 根据手机查询
	 * @param mobile
	 * @return
	 */
	public List<BlackInfo> selectBlackInfoByMobile(String mobile);
	
	/**
	 * 根据电话查询
	 * @param mobile
	 * @return
	 */
	public List<BlackInfo> selectBlackInfoByTell(String tell);
	
	/**
	 * 根据地址查询
	 * @param mobile
	 * @return
	 */
	public List<BlackInfo> selectBlackInfoByAddress(String address);
	
	/**
	 * 根据单位名称查询
	 * @param mobile
	 * @return
	 */
	public List<BlackInfo> selectBlackInfoByWorkName(String workName);
	
	/**
	 * 根据id集合批量查询BlackInfo
	 * @param idList
	 * @return
	 */
	public List<BlackInfo> selectBlackInfoByIdList(List<Integer> idList);
	
	/**
	 * 根据条件查询
	 * @param queryCompareCondition
	 * @return
	 */
	public List<BlackInfo> selectBlackInfoByQueryCompareCondition(QueryCompareCondition queryCompareCondition);

	/**
	 * 批量保存
	 * @param agentInfosList
	 */
	public void batchInsertBlackInfos(List<BlackInfo> blackInfosList);

	/**
	 * 根据批量上传Id 查询
	 * @param uploadBatchId
	 * @return
	 */
	public List<BlackInfo> selectBlackInfosListByUploadBatchId(int uploadBatchId);

}
