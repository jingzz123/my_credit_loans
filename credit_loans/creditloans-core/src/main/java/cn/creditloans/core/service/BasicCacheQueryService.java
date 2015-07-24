package cn.creditloans.core.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.creditloans.core.dto.enterprise.QueryCompareCondition;
import cn.creditloans.core.dto.enterprise.QueryCompareResultItem;
import cn.creditloans.core.dto.enterprise.SingleQueryResultDTO;

/**
 * 从缓存中查询
 * @author Administrator
 *
 */
public interface BasicCacheQueryService {

	/**
	 * 从缓存查询姓名
	 * @param name
	 * @param dateConditionMap 日期条件
	 * @param bankIdCondition 银行id
	 * @return
	 */
	public QueryCompareResultItem queryName(String name, Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);
	
	/**
	 * 从缓存查询身份证
	 * @param name
	 * @param dateConditionMap 日期条件
	 * @param bankIdCondition 银行id
	 * @return
	 */
	public QueryCompareResultItem queryIdNumber(String idNumber, Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);
	
	/**
	 * 从缓存查询手机
	 * @param mobile
	 * @param dateConditionMap 日期条件
	 * @param bankIdCondition 银行id
	 * @return
	 */
	public QueryCompareResultItem queryMobile(String mobile, Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);
	
	/**
	 * 从缓存查询电话
	 * @param tell
	 * @param dateConditionMap 日期条件
	 * @param bankIdCondition 银行id
	 * @return
	 */
	public QueryCompareResultItem queryTell(String tell, Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);
	
	/**
	 * 从缓存查询地址
	 * @param address
	 * @param dateConditionMap 日期条件
	 * @param bankIdCondition 银行id
	 * @return
	 */
	public QueryCompareResultItem queryAddress(String address, Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);
	
	/**
	 * 从缓存查询单位名称
	 * @param workName
	 * @param dateConditionMap
	 * @param bankIdCondition
	 * @return
	 */
	public QueryCompareResultItem queryWorkName(String workName, Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition);
	
	/**
	 * 对外接口查询 查询比对
	 * @param queryCompareCondition
	 * @return
	 */
	public List<SingleQueryResultDTO> singleQuery(QueryCompareCondition queryCompareCondition);
	
	/**
	 * 对外接口查询 查询比对 联合查询
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
