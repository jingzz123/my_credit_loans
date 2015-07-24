package cn.creditloans.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.creditloans.core.dto.enterprise.QueryCompareCondition;
import cn.creditloans.core.dto.enterprise.QueryCompareResultItem;
import cn.creditloans.core.dto.enterprise.SingleQueryResultDTO;

/**
 * 查询比对查库
 * @author Administrator
 *
 */
public interface BasicQueryService {
	
	/**
	 * 查询姓名
	 * @param name 姓名
	 * @param dateConditionMap 日期分组
	 * @param bankIdCondition 银行条件
	 * @return
	 */
	public QueryCompareResultItem queryName(String name, Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);
	
	/**
	 * 查询身份证
	 * @param idNumber 身份证号码
	 * @param dateConditionMap 日期分组
	 * @param bankIdCondition  银行条件
	 * @return
	 */
	public QueryCompareResultItem queryIdNumber(String idNumber, Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);
	
	/**
	 * 查询手机
	 * @param mobile 手机号码
	 * @param dateConditionMap 日期分组
	 * @param bankIdCondition  银行条件
	 * @return
	 */
	public QueryCompareResultItem queryMobile(String mobile, Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);
	
	/**
	 * 查询电话
	 * @param tell 电话
	 * @param dateConditionMap 日期分组
	 * @param bankIdCondition  银行条件
	 * @return
	 */
	public QueryCompareResultItem queryTell(String tell, Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);
	
	/**
	 * 查询地址
	 * @param address 地址
	 * @param dateConditionMap 日期分组
	 * @param bankIdCondition  银行条件
	 * @return
	 */
	public QueryCompareResultItem queryAddress(String address, Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);
	
	/**
	 * 查询单位名称
	 * @param workName 单位名称
	 * @param dateConditionMap 日期分组
	 * @param bankIdCondition  银行条件
	 * @return
	 */
	public QueryCompareResultItem queryWorkName(String workName, Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);
	
	/**
	 * 对外接口查询 查询比对
	 * @return
	 */
	public List<SingleQueryResultDTO> singleQuery(QueryCompareCondition queryCompareCondition);
	
	/**
	 *  对外接口查询 查询比对 联合查询
	 * @param queryCompareCondition
	 * @return
	 */
	public SingleQueryResultDTO jointSingleQuery(QueryCompareCondition queryCompareCondition);

	/**
	 * 查询比对 联合查询
	 * @param queryCompareConditionDTO
	 * @param dateConditionMap
	 * @param bankIdCondition
	 * @return
	 */
	public QueryCompareResultItem jointQueryBlackInfo(
			QueryCompareCondition queryCompareConditionDTO,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);

}
