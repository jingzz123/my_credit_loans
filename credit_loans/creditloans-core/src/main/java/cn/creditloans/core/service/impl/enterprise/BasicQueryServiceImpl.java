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
import cn.creditloans.core.service.BasicQueryService;

/**
 * 查询比对 查库
 * @author Administrator
 *
 */
@Service("basicQueryService")
public class BasicQueryServiceImpl extends AbstractQueryBaseService implements BasicQueryService {
	
	@Autowired
	private BlackInfoDao blackInfoDao;
	
	@Autowired
	private EnterpriseDao enterpriseDao;

	@Override
	public QueryCompareResultItem queryName(String name, Map<Integer, Date> dateConditionMap,
			List<Integer> bankIdCondition) {
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField("姓名");
		queryCompareResultItem.setBlackTypeName(name);
		List<BlackInfo> blackInfoList = blackInfoDao.selectBlackInfoByName(name);
		queryCompareResultItem.setMatchItemList(handleMatchItems(blackInfoList, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}

	@Override
	public QueryCompareResultItem queryIdNumber(String idNumber,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField("证件号码");
		queryCompareResultItem.setBlackTypeName(idNumber);
		List<BlackInfo> blackInfoList = blackInfoDao.selectBlackInfoByIdNumber(idNumber);
		queryCompareResultItem.setMatchItemList(handleMatchItems(blackInfoList, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}

	@Override
	public QueryCompareResultItem queryMobile(String mobile,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField("手机");
		queryCompareResultItem.setBlackTypeName(mobile);
		List<BlackInfo> blackInfoList = blackInfoDao.selectBlackInfoByMobile(mobile);
		queryCompareResultItem.setMatchItemList(handleMatchItems(blackInfoList, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}

	@Override
	public QueryCompareResultItem queryTell(String tell,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField("电话");
		queryCompareResultItem.setBlackTypeName(tell);
		List<BlackInfo> blackInfoList = blackInfoDao.selectBlackInfoByTell(tell);
		queryCompareResultItem.setMatchItemList(handleMatchItems(blackInfoList, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}

	@Override
	public QueryCompareResultItem queryAddress(String address,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField("地址");
		queryCompareResultItem.setBlackTypeName(address);
		List<BlackInfo> blackInfoList = blackInfoDao.selectBlackInfoByAddress(address);
		queryCompareResultItem.setMatchItemList(handleMatchItems(blackInfoList, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}

	@Override
	public QueryCompareResultItem queryWorkName(String workName,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField("单位名称");
		queryCompareResultItem.setBlackTypeName(workName);
		List<BlackInfo> blackInfoList = blackInfoDao.selectBlackInfoByWorkName(workName);
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
		List<BlackInfo> blackInfoList;
		List<BlackInfoResultDTO> blackInfoResultDTOList;
		if (name != null && !"".equals(name)) {
			singleQueryResultDTO = new SingleQueryResultDTO();
			blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
			blackInfoList = blackInfoDao.selectBlackInfoByName(name);
			singleQueryResultDTO.setMeetCondition("姓名");
			singleQueryResultDTO.setMeetConditionVal(name);
			addToblackInfoResultDTOList(blackInfoResultDTOList, blackInfoList, depCondition, depId);
			singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
			list.add(singleQueryResultDTO);
		}
		if (idNumber != null && !"".equals(idNumber)) {
			singleQueryResultDTO = new SingleQueryResultDTO();
			blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
			blackInfoList = blackInfoDao.selectBlackInfoByIdNumber(idNumber);
			singleQueryResultDTO.setMeetCondition("证件号码");
			singleQueryResultDTO.setMeetConditionVal(idNumber);
			addToblackInfoResultDTOList(blackInfoResultDTOList, blackInfoList, depCondition, depId);
			singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
			list.add(singleQueryResultDTO);
		}
		if (phone != null && !"".equals(phone)) {
			singleQueryResultDTO = new SingleQueryResultDTO();
			blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
			blackInfoList = blackInfoDao.selectBlackInfoByMobile(phone);
			singleQueryResultDTO.setMeetCondition("手机");
			singleQueryResultDTO.setMeetConditionVal(phone);
			addToblackInfoResultDTOList(blackInfoResultDTOList, blackInfoList, depCondition, depId);
			singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
			list.add(singleQueryResultDTO);
		}
		if (tel != null && !"".equals(tel)) {
			singleQueryResultDTO = new SingleQueryResultDTO();
			blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
			blackInfoList = blackInfoDao.selectBlackInfoByTell(tel);
			singleQueryResultDTO.setMeetCondition("电话");
			singleQueryResultDTO.setMeetConditionVal(tel);
			addToblackInfoResultDTOList(blackInfoResultDTOList, blackInfoList, depCondition, depId);
			singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
			list.add(singleQueryResultDTO);
		}
		if (address != null && !"".equals(address)) {
			singleQueryResultDTO = new SingleQueryResultDTO();
			blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
			blackInfoList = blackInfoDao.selectBlackInfoByAddress(address);
			singleQueryResultDTO.setMeetCondition("地址");
			singleQueryResultDTO.setMeetConditionVal(address);
			addToblackInfoResultDTOList(blackInfoResultDTOList, blackInfoList, depCondition, depId);
			singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
			list.add(singleQueryResultDTO);
		}
		if (companyName != null && !"".equals(companyName)) {
			singleQueryResultDTO = new SingleQueryResultDTO();
			blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
			blackInfoList = blackInfoDao.selectBlackInfoByWorkName(companyName);
			singleQueryResultDTO.setMeetCondition("单位名称");
			singleQueryResultDTO.setMeetConditionVal(companyName);
			addToblackInfoResultDTOList(blackInfoResultDTOList, blackInfoList, depCondition, depId);
			singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
			list.add(singleQueryResultDTO);
		}
		return list;
	}
	
	/**
	 * 过滤本部门/非本部门
	 * @param blackInfoResultDTOList
	 * @param blackInfoList
	 * @param depCondition
	 * @param depId
	 */
	private void addToblackInfoResultDTOList(List<BlackInfoResultDTO> blackInfoResultDTOList, List<BlackInfo> blackInfoList, int depCondition, int depId) {
		for (BlackInfo blackInfo : blackInfoList) {
			int depId2 = blackInfo.getDepId();
			if (depCondition == 2 && depId2 == depId) {// 本部门
				blackInfoResultDTOList.add(QueryBaseServiceUtil.convertBlackInfo2BlackInfoResult(blackInfo));
			} else if (depCondition == 3 && depId2 != depId) {// 除了本部门
				blackInfoResultDTOList.add(QueryBaseServiceUtil.convertBlackInfo2BlackInfoResult(blackInfo));
			} else {
				blackInfoResultDTOList.add(QueryBaseServiceUtil.convertBlackInfo2BlackInfoResult(blackInfo));
			}
		}
	}

	// 对外接口查询 查询比对 联合查询
	@SuppressWarnings("unused")
	@Override
	public SingleQueryResultDTO jointSingleQuery(QueryCompareCondition queryCompareCondition) {
		Enterprise enterprise = enterpriseDao.selectEnterpriseByCode(queryCompareCondition.getDepCode());
		int depId = enterprise.getId();
		if(enterprise == null) {
			return null;
		}
		String name = queryCompareCondition.getName(); // 姓名
		String idNumber = queryCompareCondition.getIdNumber(); // 身份证号
		String phone = queryCompareCondition.getMobile(); // 手机
		String tel = queryCompareCondition.getTell(); // 固定电话
		String address = queryCompareCondition.getAddress(); // 地址
		String companyName = queryCompareCondition.getWorkName(); // 单位名称
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		if (name != null && !"".equals(name)) {
			builder.append("姓名");
			builder.append(",");
			builder2.append(name);
			builder2.append(",");
		}
		if (idNumber != null && !"".equals(idNumber)) {
			builder.append("证件号码");
			builder.append(",");
			builder2.append(idNumber);
			builder2.append(",");
		}
		if (phone != null && !"".equals(phone)) {
			builder.append("手机");
			builder.append(",");
			builder2.append(phone);
			builder2.append(",");
		}
		if (tel != null && !"".equals(tel)) {
			builder.append("电话");
			builder.append(",");
			builder2.append(tel);
			builder2.append(",");
		}
		if (address != null && !"".equals(address)) {
			builder.append("地址");
			builder.append(",");
			builder2.append(address);
			builder2.append(",");
		}
		if (companyName != null && !"".equals(companyName)) {
			builder.append("单位名称");
			builder2.append(companyName);
		}
		int depCondition = queryCompareCondition.getDepCondition();
		List<BlackInfo> blackInfoList = blackInfoDao.selectBlackInfoByQueryCompareCondition(queryCompareCondition);
		List<BlackInfoResultDTO> blackInfoResultDTOList = new ArrayList<BlackInfoResultDTO>();
		addToblackInfoResultDTOList(blackInfoResultDTOList, blackInfoList, depCondition, depId);
		SingleQueryResultDTO singleQueryResultDTO = new SingleQueryResultDTO();
		singleQueryResultDTO.setMeetCondition(builder.toString());
		singleQueryResultDTO.setMeetConditionVal(builder2.toString());
		singleQueryResultDTO.setBlackInfoResultDTOList(blackInfoResultDTOList);
		return singleQueryResultDTO;
	}

	// 查询比对 联合查询
	@Override
	public QueryCompareResultItem jointQueryBlackInfo(
			QueryCompareCondition queryCompareCondition,
			Map<Integer, Date> dateConditionMap, List<Integer> bankIdCondition) {
		List<BlackInfo> blackInfoList = blackInfoDao.selectBlackInfoByQueryCompareCondition(queryCompareCondition);
		if (blackInfoList.size() == 0) {
			return null;
		}
		String name = queryCompareCondition.getName(); // 姓名
		String idNumber = queryCompareCondition.getIdNumber(); // 身份证号
		String phone = queryCompareCondition.getMobile(); // 手机
		String tel = queryCompareCondition.getTell(); // 固定电话
		String address = queryCompareCondition.getAddress(); // 地址
		String companyName = queryCompareCondition.getWorkName(); // 单位名称
		StringBuilder builder = new StringBuilder();
		StringBuilder builder2 = new StringBuilder();
		if (name != null && !"".equals(name)) {
			builder.append("姓名");
			builder.append(",");
			builder2.append(name);
			builder2.append(",");
		}
		if (idNumber != null && !"".equals(idNumber)) {
			builder.append("证件号码");
			builder.append(",");
			builder2.append(idNumber);
			builder2.append(",");
		}
		if (phone != null && !"".equals(phone)) {
			builder.append("手机");
			builder.append(",");
			builder2.append(phone);
			builder2.append(",");
		}
		if (tel != null && !"".equals(tel)) {
			builder.append("电话");
			builder.append(",");
			builder2.append(tel);
			builder2.append(",");
		}
		if (address != null && !"".equals(address)) {
			builder.append("地址");
			builder.append(",");
			builder2.append(address);
			builder2.append(",");
		}
		if (companyName != null && !"".equals(companyName)) {
			builder.append("单位名称");
			builder2.append(companyName);
		}
		QueryCompareResultItem queryCompareResultItem = new QueryCompareResultItem();
		queryCompareResultItem.setField(builder.toString());
		queryCompareResultItem.setBlackTypeName(builder2.toString());
		queryCompareResultItem.setMatchItemList(handleMatchItems(blackInfoList, dateConditionMap, bankIdCondition));
		return queryCompareResultItem;
	}
	
}
