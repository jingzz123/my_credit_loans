package cn.creditloans.core.service.impl.enterprise;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.creditloans.core.dao.BlackInfoDao;
import cn.creditloans.core.dao.EnterpriseDao;
import cn.creditloans.core.dto.enterprise.BlackInfoResultDTO;
import cn.creditloans.core.dto.enterprise.QueryCompareCondition;
import cn.creditloans.core.dto.enterprise.QueryCompareResultItem;
import cn.creditloans.core.dto.enterprise.SingleQueryResultDTO;
import cn.creditloans.core.entity.enterprise.BlackInfo;
import cn.creditloans.core.entity.enterprise.Enterprise;
import cn.creditloans.core.service.BasicCacheQueryService;
import cn.creditloans.tools.cache.redis.HomeAddressCache;
import cn.creditloans.tools.cache.redis.HomeTelCache;
import cn.creditloans.tools.cache.redis.IdNumberCache;
import cn.creditloans.tools.cache.redis.MobileCache;
import cn.creditloans.tools.cache.redis.NameCache;
import cn.creditloans.tools.cache.redis.WorkAddressCache;
import cn.creditloans.tools.cache.redis.WorkNameCache;
import cn.creditloans.tools.cache.redis.WorkTelCache;

/**
 * 查询比对查缓存
 * @author Administrator
 *
 */
@Service("basicCacheQueryService")
public class BasicCacheQueryServiceImpl extends AbstractQueryBaseService implements BasicCacheQueryService {
	
	/**
	 * 用作redis缓存的key的前缀
	 */
	private static final String KEY_PREFIX = "BLACK_TYPE_";
	
	@Autowired
	private EnterpriseDao enterpriseDao;
	
	@Autowired
	private BlackInfoDao blackInfoDao;

	// 从缓存查询姓名
	@Override
	public QueryCompareResultItem queryName(String name,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField("姓名");
		String key = KEY_PREFIX + name;
		List<String> _valuelist = NameCache.getInstance().hvals(key);
		List<BlackInfo> blackInfoList = handleValueFromCache(_valuelist);
		queryCompareResultItem.setMatchItemList(handleMatchItems(blackInfoList, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}

	// 从缓存查询证件号码
	@Override
	public QueryCompareResultItem queryIdNumber(String idNumber,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField("证件号码");
		String key = KEY_PREFIX + idNumber;
		List<String> _valuelist = IdNumberCache.getInstance().hvals(key);
		List<BlackInfo> blackInfoList = handleValueFromCache(_valuelist);
		queryCompareResultItem.setMatchItemList(handleMatchItems(blackInfoList, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}

	// 从缓存查询手机
	@Override
	public QueryCompareResultItem queryMobile(String mobile,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField("手机");
		String key = KEY_PREFIX + mobile;
		List<String> _valuelist = MobileCache.getInstance().hvals(key);
		List<BlackInfo> blackInfoList = handleValueFromCache(_valuelist);
		queryCompareResultItem.setMatchItemList(handleMatchItems(blackInfoList, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}

	// 从缓存查询电话
	@Override
	public QueryCompareResultItem queryTell(String tell,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField("电话");
		String key = KEY_PREFIX + tell;
		List<String> _valuelist = WorkTelCache.getInstance().hvals(key);
		_valuelist.addAll(HomeTelCache.getInstance().hvals(key));
		List<BlackInfo> blackInfoList = handleValueFromCache(_valuelist);
		queryCompareResultItem.setMatchItemList(handleMatchItems(blackInfoList, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}

	// 从缓存查询地址
	@Override
	public QueryCompareResultItem queryAddress(String address,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField("地址");
		String key = KEY_PREFIX + address;
		List<String> _valuelist = HomeAddressCache.getInstance().hvals(key);
		_valuelist.addAll(WorkAddressCache.getInstance().hvals(key));
		List<BlackInfo> blackInfoList = handleValueFromCache(_valuelist);
		queryCompareResultItem.setMatchItemList(handleMatchItems(blackInfoList, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}

	// 从缓存查询单位名称
	@Override
	public QueryCompareResultItem queryWorkName(String workName,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField("单位名称");
		String key = KEY_PREFIX + workName;
		List<String> _valuelist = WorkNameCache.getInstance().hvals(key);
		List<BlackInfo> blackInfoList = handleValueFromCache(_valuelist);
		queryCompareResultItem.setMatchItemList(handleMatchItems(blackInfoList, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}

	// 对外接口查询 查询比对
	@SuppressWarnings("unused")
	@Override
	public List<SingleQueryResultDTO> singleQuery(QueryCompareCondition queryCompareConditionDTO) {
		List<SingleQueryResultDTO> list = new ArrayList<SingleQueryResultDTO>();
		String name = queryCompareConditionDTO.getName(); // 姓名
		String idNumber = queryCompareConditionDTO.getIdNumber(); // 身份证号
		String phone = queryCompareConditionDTO.getMobile(); // 手机
		String tel = queryCompareConditionDTO.getTell(); // 固定电话
		String address = queryCompareConditionDTO.getAddress(); // 地址
		String companyName = queryCompareConditionDTO.getWorkName(); // 单位名称
		Enterprise enterprise = enterpriseDao.selectEnterpriseByCode(queryCompareConditionDTO.getDepCode());
		int depId = enterprise.getId();
		if(enterprise == null) {
			return null;
		}
		int depCondition = queryCompareConditionDTO.getDepCondition();
		SingleQueryResultDTO singleQueryResultDTO;
		List<BlackInfoResultDTO> blackInfoResultDTOList;
		if(name != null && !"".equals(name)) {
			blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
			singleQueryResultDTO = new SingleQueryResultDTO();
			singleQueryResultDTO.setMeetCondition("姓名");
			singleQueryResultDTO.setMeetConditionVal(name);
			String key = KEY_PREFIX + name;
			List<String> _valuelist = NameCache.getInstance().hvals(key);
			List<BlackInfo> blackInfoList = handleValueFromCache(_valuelist);
			List<Integer> blackIdList = getBlackInfoId(blackInfoList, depId, depCondition);
			blackInfoList = blackInfoDao.selectBlackInfoByIdList(blackIdList);
			for (BlackInfo blackInfo : blackInfoList) {
				blackInfoResultDTOList.add(QueryBaseServiceUtil.convertBlackInfo2BlackInfoResult(blackInfo));
			}
			singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
			list.add(singleQueryResultDTO);
		}
		if(idNumber != null && !"".equals(idNumber)) {
			blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
			singleQueryResultDTO = new SingleQueryResultDTO();
			singleQueryResultDTO.setMeetCondition("证件号码");
			singleQueryResultDTO.setMeetConditionVal(idNumber);
			String key = KEY_PREFIX + idNumber;
			List<String> _valuelist = IdNumberCache.getInstance().hvals(key);
			List<BlackInfo> blackInfoList = handleValueFromCache(_valuelist);
			List<Integer> blackIdList = getBlackInfoId(blackInfoList, depId, depCondition);
			blackInfoList = blackInfoDao.selectBlackInfoByIdList(blackIdList);
			for (BlackInfo blackInfo : blackInfoList) {
				blackInfoResultDTOList.add(QueryBaseServiceUtil.convertBlackInfo2BlackInfoResult(blackInfo));
			}
			singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
			list.add(singleQueryResultDTO);
		}
		if(phone != null && !"".equals(phone)) {
			blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
			singleQueryResultDTO = new SingleQueryResultDTO();
			singleQueryResultDTO.setMeetCondition("手机");
			singleQueryResultDTO.setMeetConditionVal(phone);
			String key = KEY_PREFIX + phone;
			List<String> _valuelist = MobileCache.getInstance().hvals(key);
			List<BlackInfo> blackInfoList = handleValueFromCache(_valuelist);
			List<Integer> blackIdList = getBlackInfoId(blackInfoList, depId, depCondition);
			blackInfoList = blackInfoDao.selectBlackInfoByIdList(blackIdList);
			for (BlackInfo blackInfo : blackInfoList) {
				blackInfoResultDTOList.add(QueryBaseServiceUtil.convertBlackInfo2BlackInfoResult(blackInfo));
			}
			singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
			list.add(singleQueryResultDTO);
		}
		if(tel != null && !"".equals(tel)) {
			blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
			singleQueryResultDTO = new SingleQueryResultDTO();
			singleQueryResultDTO.setMeetCondition("电话");
			singleQueryResultDTO.setMeetConditionVal(tel);
			String key = KEY_PREFIX + tel;
			List<String> _valuelist = HomeTelCache.getInstance().hvals(key);
			_valuelist.addAll(WorkTelCache.getInstance().hvals(key));
			List<BlackInfo> blackInfoList = handleValueFromCache(_valuelist);
			List<Integer> blackIdList = getBlackInfoId(blackInfoList, depId, depCondition);
			blackInfoList = blackInfoDao.selectBlackInfoByIdList(blackIdList);
			for (BlackInfo blackInfo : blackInfoList) {
				blackInfoResultDTOList.add(QueryBaseServiceUtil.convertBlackInfo2BlackInfoResult(blackInfo));
			}
			singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
			list.add(singleQueryResultDTO);
		}
		if(address != null && !"".equals(address)) {
			blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
			singleQueryResultDTO = new SingleQueryResultDTO();
			singleQueryResultDTO.setMeetCondition("地址");
			singleQueryResultDTO.setMeetConditionVal(address);
			String key = KEY_PREFIX + address;
			List<String> _valuelist = HomeAddressCache.getInstance().hvals(key);
			_valuelist.addAll(WorkAddressCache.getInstance().hvals(key));
			List<BlackInfo> blackInfoList = handleValueFromCache(_valuelist);
			List<Integer> blackIdList = getBlackInfoId(blackInfoList, depId, depCondition);
			blackInfoList = blackInfoDao.selectBlackInfoByIdList(blackIdList);
			for (BlackInfo blackInfo : blackInfoList) {
				blackInfoResultDTOList.add(QueryBaseServiceUtil.convertBlackInfo2BlackInfoResult(blackInfo));
			}
			singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
			list.add(singleQueryResultDTO);
		}
		if(companyName != null && !"".equals(companyName)) {
			blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
			singleQueryResultDTO = new SingleQueryResultDTO();
			singleQueryResultDTO.setMeetCondition("单位名称");
			singleQueryResultDTO.setMeetConditionVal(companyName);
			String key = KEY_PREFIX + companyName;
			List<String> _valuelist = WorkNameCache.getInstance().hvals(key);
			List<BlackInfo> blackInfoList = handleValueFromCache(_valuelist);
			List<Integer> blackIdList = getBlackInfoId(blackInfoList, depId, depCondition);
			blackInfoList = blackInfoDao.selectBlackInfoByIdList(blackIdList);
			for (BlackInfo blackInfo : blackInfoList) {
				blackInfoResultDTOList.add(QueryBaseServiceUtil.convertBlackInfo2BlackInfoResult(blackInfo));
			}
			singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
			list.add(singleQueryResultDTO);
		}
		return list;
	}
	
	@SuppressWarnings("unused")
	public SingleQueryResultDTO jointSingleQuery(QueryCompareCondition queryCompareConditionDTO) {
		String name = queryCompareConditionDTO.getName(); // 姓名
		String idNumber = queryCompareConditionDTO.getIdNumber(); // 身份证号
		String phone = queryCompareConditionDTO.getMobile(); // 手机
		String tel = queryCompareConditionDTO.getTell(); // 固定电话
		String address = queryCompareConditionDTO.getAddress(); // 地址
		String companyName = queryCompareConditionDTO.getWorkName(); // 单位名称
		Enterprise enterprise = enterpriseDao.selectEnterpriseByCode(queryCompareConditionDTO.getDepCode());
		int depId = enterprise.getId();
		if(enterprise == null) {
			return null;
		}
		int depCondition = queryCompareConditionDTO.getDepCondition();
		SingleQueryResultDTO singleQueryResultDTO = new SingleQueryResultDTO();
		List<BlackInfoResultDTO> blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
		singleQueryResultDTO.setMeetCondition("姓名");
		singleQueryResultDTO.setMeetConditionVal(name);
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		List<Integer> idList = new ArrayList<Integer>();
		List<Integer> commonIdList = new ArrayList<Integer>();
		List<BlackInfo> blackInfoList;
		if(name != null && !"".equals(name)) {
			builder.append("姓名,");
			builder2.append(name);
			builder2.append(",");
			String key = KEY_PREFIX + name;
			List<String> _valuelist = NameCache.getInstance().hvals(key);
			blackInfoList = handleValueFromCache(_valuelist);
			List<Integer> blackIdList = getBlackInfoId(blackInfoList, depId, depCondition);
			if(idList.size() == 0) {
				idList = blackIdList;
			} else {
				for (Integer integer : blackIdList) {
					if (idList.contains(integer)) {
						commonIdList.add(integer);
					}
				}
				idList = new ArrayList<Integer>();
				idList.addAll(commonIdList);
				commonIdList = new ArrayList<Integer>();
			}
		}
		if(idNumber != null && !"".equals(idNumber)) {
			builder.append("证件号码,");
			builder2.append(idNumber);
			builder2.append(",");
			String key = KEY_PREFIX + idNumber;
			List<String> _valuelist = IdNumberCache.getInstance().hvals(key);
			blackInfoList = handleValueFromCache(_valuelist);
			List<Integer> blackIdList = getBlackInfoId(blackInfoList, depId, depCondition);
			if(idList.size() == 0) {
				idList = blackIdList;
			} else {
				for (Integer integer : blackIdList) {
					if (idList.contains(integer)) {
						commonIdList.add(integer);
					}
				}
				idList = new ArrayList<Integer>();
				idList.addAll(commonIdList);
				commonIdList = new ArrayList<Integer>();
			}
		}
		if(phone != null && !"".equals(phone)) {
			builder.append("手机,");
			builder2.append(phone);
			builder2.append(",");
			String key = KEY_PREFIX + phone;
			List<String> _valuelist = MobileCache.getInstance().hvals(key);
			blackInfoList = handleValueFromCache(_valuelist);
			List<Integer> blackIdList = getBlackInfoId(blackInfoList, depId, depCondition);
			if(idList.size() == 0) {
				idList = blackIdList;
			} else {
				for (Integer integer : blackIdList) {
					if (idList.contains(integer)) {
						commonIdList.add(integer);
					}
				}
				idList = new ArrayList<Integer>();
				idList.addAll(commonIdList);
				commonIdList = new ArrayList<Integer>();
			}
		}
		if(tel != null && !"".equals(tel)) {
			builder.append("电话,");
			builder2.append(tel);
			builder2.append(",");
			String key = KEY_PREFIX + tel;
			List<String> _valuelist = HomeTelCache.getInstance().hvals(key);
			_valuelist.addAll(WorkTelCache.getInstance().hvals(key));
			blackInfoList = handleValueFromCache(_valuelist);
			List<Integer> blackIdList = getBlackInfoId(blackInfoList, depId, depCondition);
			if(idList.size() == 0) {
				idList = blackIdList;
			} else {
				for (Integer integer : blackIdList) {
					if (idList.contains(integer)) {
						commonIdList.add(integer);
					}
				}
				idList = new ArrayList<Integer>();
				idList.addAll(commonIdList);
				commonIdList = new ArrayList<Integer>();
			}
		}
		if(address != null && !"".equals(address)) {
			builder.append("地址,");
			builder2.append(address);
			builder2.append(",");
			String key = KEY_PREFIX + address;
			List<String> _valuelist = HomeAddressCache.getInstance().hvals(key);
			_valuelist.addAll(WorkAddressCache.getInstance().hvals(key));
			blackInfoList = handleValueFromCache(_valuelist);
			List<Integer> blackIdList = getBlackInfoId(blackInfoList, depId, depCondition);
			if(idList.size() == 0) {
				idList = blackIdList;
			} else {
				for (Integer integer : blackIdList) {
					if (idList.contains(integer)) {
						commonIdList.add(integer);
					}
				}
				idList = new ArrayList<Integer>();
				idList.addAll(commonIdList);
				commonIdList = new ArrayList<Integer>();
			}
		}
		if(companyName != null && !"".equals(companyName)) {
			builder.append("单位名称");
			builder2.append(companyName);
			String key = KEY_PREFIX + companyName;
			List<String> _valuelist = WorkNameCache.getInstance().hvals(key);
			blackInfoList = handleValueFromCache(_valuelist);
			List<Integer> blackIdList = getBlackInfoId(blackInfoList, depId, depCondition);
			if(idList.size() == 0) {
				idList = blackIdList;
			} else {
				for (Integer integer : blackIdList) {
					if (idList.contains(integer)) {
						commonIdList.add(integer);
					}
				}
				idList = new ArrayList<Integer>();
				idList.addAll(commonIdList);
				commonIdList = new ArrayList<Integer>();
			}
		}
		if(idList == null || idList.size() == 0) {
			return null;
		}
		blackInfoList = blackInfoDao.selectBlackInfoByIdList(idList);
		for (BlackInfo blackInfo : blackInfoList) {
			blackInfoResultDTOList.add(QueryBaseServiceUtil.convertBlackInfo2BlackInfoResult(blackInfo));
		}
		singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
		return singleQueryResultDTO;
	}

	/**
	 * 根据选择本机构/非本机构 来过滤Id
	 * @param blackInfoList
	 * @param depId
	 * @param depCondition
	 * @return
	 */
	private List<Integer> getBlackInfoId(List<BlackInfo> blackInfoList, int depId, int depCondition) {
		List<Integer> idList = new ArrayList<Integer>(); 
		for (BlackInfo blackInfo : blackInfoList) {
			int depId2 = blackInfo.getDepId();
			int id = blackInfo.getId();
			if (depCondition == 2 && depId2 == depId) {// 本部门
				idList.add(id);
			} else if (depCondition == 3 && depId2 != depId) {// 除了本部门
				idList.add(id);
			} else {
				idList.add(id);
			} 
		}
		return idList;
	}

	// 查询比对 联合查询
	@Override
	public QueryCompareResultItem jointQueryBlackInfo(QueryCompareCondition queryCompareConditionDTO, 
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		String name = queryCompareConditionDTO.getName(); // 姓名
		String idNumber = queryCompareConditionDTO.getIdNumber(); // 身份证号
		String phone = queryCompareConditionDTO.getMobile(); // 手机
		String tel = queryCompareConditionDTO.getTell(); // 固定电话
		String address = queryCompareConditionDTO.getAddress(); // 地址
		String companyName = queryCompareConditionDTO.getWorkName(); // 单位名称
		List<BlackInfo> list = new ArrayList<BlackInfo>();
		List<BlackInfo> commonList = new ArrayList<BlackInfo>();
		List<BlackInfo> blackInfoList;
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		if(name != null && !"".equals(name)) {
			builder.append("姓名,");
			builder2.append(name);
			builder2.append(",");
			String key = KEY_PREFIX + name;
			List<String> _valuelist = NameCache.getInstance().hvals(key);
			blackInfoList = handleValueFromCache(_valuelist);
			if(list.size() == 0) {
				list = blackInfoList;
			} else {
				for (BlackInfo black : blackInfoList) {
					if (list.contains(black)) {
						commonList.add(black);
					}
				}
				list = new ArrayList<BlackInfo>();
				list.addAll(commonList);
				commonList = new ArrayList<BlackInfo>();
			}
		}
		if(idNumber != null && !"".equals(idNumber)) {
			builder.append("证件号码,");
			builder2.append(idNumber);
			builder2.append(",");
			String key = KEY_PREFIX + idNumber;
			List<String> _valuelist = IdNumberCache.getInstance().hvals(key);
			blackInfoList = handleValueFromCache(_valuelist);
			if(list.size() == 0) {
				list = blackInfoList;
			} else {
				for (BlackInfo black : blackInfoList) {
					if (list.contains(black)) {
						commonList.add(black);
					}
				}
				list = new ArrayList<BlackInfo>();
				list.addAll(commonList);
				commonList = new ArrayList<BlackInfo>();
			}
		}
		if(phone != null && !"".equals(phone)) {
			builder.append("手机,");
			builder2.append(phone);
			builder2.append(",");
			String key = KEY_PREFIX + phone;
			List<String> _valuelist = MobileCache.getInstance().hvals(key);
			blackInfoList = handleValueFromCache(_valuelist);
			if(list.size() == 0) {
				list = blackInfoList;
			} else {
				for (BlackInfo black : blackInfoList) {
					if (list.contains(black)) {
						commonList.add(black);
					}
				}
				list = new ArrayList<BlackInfo>();
				list.addAll(commonList);
				commonList = new ArrayList<BlackInfo>();
			}
		}
		if(tel != null && !"".equals(tel)) {
			builder.append("电话,");
			builder2.append(tel);
			builder2.append(",");
			String key = KEY_PREFIX + tel;
			List<String> _valuelist = HomeTelCache.getInstance().hvals(key);
			_valuelist.addAll(WorkTelCache.getInstance().hvals(key));
			blackInfoList = handleValueFromCache(_valuelist);
			if(list.size() == 0) {
				list = blackInfoList;
			} else {
				for (BlackInfo black : blackInfoList) {
					if (list.contains(black)) {
						commonList.add(black);
					}
				}
				list = new ArrayList<BlackInfo>();
				list.addAll(commonList);
				commonList = new ArrayList<BlackInfo>();
			}
		}
		if(address != null && !"".equals(address)) {
			builder.append("地址,");
			builder2.append(address);
			builder2.append(",");
			String key = KEY_PREFIX + address;
			List<String> _valuelist = HomeAddressCache.getInstance().hvals(key);
			_valuelist.addAll(WorkAddressCache.getInstance().hvals(key));
			blackInfoList = handleValueFromCache(_valuelist);
			if(list.size() == 0) {
				list = blackInfoList;
			} else {
				for (BlackInfo black : blackInfoList) {
					if (list.contains(black)) {
						commonList.add(black);
					}
				}
				list = new ArrayList<BlackInfo>();
				list.addAll(commonList);
				commonList = new ArrayList<BlackInfo>();
			}
		}
		if(companyName != null && !"".equals(companyName)) {
			builder.append("单位名称");
			builder2.append(companyName);
			String key = KEY_PREFIX + companyName;
			List<String> _valuelist = WorkNameCache.getInstance().hvals(key);
			blackInfoList = handleValueFromCache(_valuelist);
			if(list.size() == 0) {
				list = blackInfoList;
			} else {
				for (BlackInfo black : blackInfoList) {
					if (list.contains(black)) {
						commonList.add(black);
					}
				}
				list = new ArrayList<BlackInfo>();
				list.addAll(commonList);
				commonList = new ArrayList<BlackInfo>();
			}
		}
		if(list == null || list.size() == 0) {
			return null;
		}
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField(builder.toString());
		queryCompareResultItem.setBlackTypeName(builder2.toString());
		queryCompareResultItem.setMatchItemList(handleMatchItems(list, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}

}
