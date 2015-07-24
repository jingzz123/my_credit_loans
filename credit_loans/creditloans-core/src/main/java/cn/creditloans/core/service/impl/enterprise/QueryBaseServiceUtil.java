package cn.creditloans.core.service.impl.enterprise;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.creditloans.core.dto.enterprise.BlackInfoResultDTO;
import cn.creditloans.core.dto.platform.MatchItem;
import cn.creditloans.core.entity.enterprise.BlackInfo;

public class QueryBaseServiceUtil {
	
	public List<MatchItem> handleMatchItems(List<BlackInfo> queryResultList, Map<Integer, Date> dateConditionMap, Map<Integer, Object> bankIdCondition) {
		// 返回值
		List<MatchItem> matchItemList = new ArrayList<MatchItem>();
		
		if (queryResultList != null && queryResultList.size() > 0) {
			// 记录n天内比中的记录条数
			int _10hitRecordCount = 0;
			int _30hitRecordCount = 0;
			int _90hitRecordCount = 0;
			int _365hitRecordCount = 0;
			int _allhitRecordCount = 0;

			// 记录n天内比中的记录中涉及的银行id
			Set<Integer> _10hitBankCountSet = new HashSet<Integer>();
			Set<Integer> _30hitBankCountSet = new HashSet<Integer>();
			Set<Integer> _90hitBankCountSet = new HashSet<Integer>();
			Set<Integer> _365hitBankCountSet = new HashSet<Integer>();
			Set<Integer> _allhitBankCountSet = new HashSet<Integer>();
			
			// 记录n天内比中的记录的id
			Set<Integer> _10hitRecordIdSet = new HashSet<Integer>();
			Set<Integer> _30hitRecordIdSet = new HashSet<Integer>();
			Set<Integer> _90hitRecordIdSet = new HashSet<Integer>();
			Set<Integer> _365hitRecordIdSet = new HashSet<Integer>();
			Set<Integer> _allhitRecordIdSet = new HashSet<Integer>();

			// 日期条件
			Date date10 = dateConditionMap.get(0); // 10天内
			Date date30 = dateConditionMap.get(1); // 30天内
			Date date90 = dateConditionMap.get(2); // 90天内
			Date date365 = dateConditionMap.get(3); // 365天内
			
			// 如果queryResultList都没有满足bankIdCondition这个条件, 则就不要生成matchItem了
			boolean flag = false;

			// 处理
			for (BlackInfo resultItem : queryResultList) {
				int bankId = resultItem.getDepId();
				
				// 判断银行id是否在查询条件中
				if (bankIdCondition.containsKey(bankId)) {
					flag = true;
					
					Date producedOn = resultItem.getConfirmedDate();
					int itemId = resultItem.getId();

					// 所有
					_allhitRecordCount++;
					_allhitBankCountSet.add(bankId);
					_allhitRecordIdSet.add(itemId);

					// 按 n 天分开处理
					if (producedOn.compareTo(date10) > 0) { // 10天内
						_10hitBankCountSet.add(bankId);
						_10hitRecordCount++;
						_10hitRecordIdSet.add(itemId);
					}

					if (producedOn.compareTo(date30) > 0) { // 30 天内
						_30hitBankCountSet.add(bankId);
						_30hitRecordCount++;
						_30hitRecordIdSet.add(itemId);
					}

					if (producedOn.compareTo(date90) > 0) { // 90 天内
						_90hitBankCountSet.add(bankId);
						_90hitRecordCount++;
						_90hitRecordIdSet.add(itemId);
					}

					if (producedOn.compareTo(date365) > 0) { // 365 天内
						_365hitBankCountSet.add(bankId);
						_365hitRecordCount++;
						_365hitRecordIdSet.add(itemId);
					}
				} // if

			} // for

			if (flag) {
				MatchItem _10MatchItem = new MatchItem();
				_10MatchItem.setType(0);
				_10MatchItem.setMatchCount(_10hitRecordCount);
				_10MatchItem.setReferBankCount(_10hitBankCountSet.size());
				_10MatchItem.setItemIdList(_10hitRecordIdSet);
				matchItemList.add(_10MatchItem);

				MatchItem _30MatchItem = new MatchItem();
				_30MatchItem.setType(0);
				_30MatchItem.setMatchCount(_30hitRecordCount);
				_30MatchItem.setReferBankCount(_30hitBankCountSet.size());
				_30MatchItem.setItemIdList(_30hitRecordIdSet);
				matchItemList.add(_30MatchItem);

				MatchItem _90MatchItem = new MatchItem();
				_90MatchItem.setType(0);
				_90MatchItem.setMatchCount(_90hitRecordCount);
				_90MatchItem.setReferBankCount(_90hitBankCountSet.size());
				_90MatchItem.setItemIdList(_90hitRecordIdSet);
				matchItemList.add(_90MatchItem);

				MatchItem _365MatchItem = new MatchItem();
				_365MatchItem.setType(0);
				_365MatchItem.setMatchCount(_365hitRecordCount);
				_365MatchItem.setReferBankCount(_365hitBankCountSet.size());
				_365MatchItem.setItemIdList(_365hitRecordIdSet);
				matchItemList.add(_365MatchItem);

				MatchItem _allMatchItem = new MatchItem();
				_allMatchItem.setType(0);
				_allMatchItem.setMatchCount(_allhitRecordCount);
				_allMatchItem.setReferBankCount(_allhitBankCountSet.size());
				_allMatchItem.setItemIdList(_allhitRecordIdSet);
				matchItemList.add(_allMatchItem);
			} // if
			
		}
		
		// FIXME : 以后有时间改为null吧，主要是查询比对要改
//		if (matchItemList.size() > 0) {
//			return matchItemList;
//		} else {
//			return null;
//		}
		return matchItemList;
	}
	
	/**
	 * 将黑名单对象 BlackInfo 转换为要显示的对象
	 * @param blackInfo
	 * @return
	 */
	public static BlackInfoResultDTO convertBlackInfo2BlackInfoResult(BlackInfo blackInfo) {
		BlackInfoResultDTO blackInfoResultDTO = new BlackInfoResultDTO();
		blackInfoResultDTO.setName(blackInfo.getName());
		blackInfoResultDTO.setIdNumber(blackInfo.getIdNumber());
		blackInfoResultDTO.setHomeAddress(blackInfo.getHomeAddress());
		blackInfoResultDTO.setHomeTell(blackInfo.getHomeTell());
		blackInfoResultDTO.setWorkName(blackInfo.getWorkName());
		blackInfoResultDTO.setWorkAddress(blackInfo.getWorkAddress());
		blackInfoResultDTO.setWorkTell(blackInfo.getWorkTell());
		blackInfoResultDTO.setLoanType(blackInfo.getLoanType());
		blackInfoResultDTO.setConfirmedType(blackInfo.getConfirmedType());
		blackInfoResultDTO.setConfirmedDetails(blackInfo.getConfirmedDetails());
		blackInfoResultDTO.setAppliedOn(blackInfo.getAppliedOn());
		blackInfoResultDTO.setConfirmedDate(blackInfo.getConfirmedDate());
		blackInfoResultDTO.setComments(blackInfo.getComments());
		blackInfoResultDTO.setContactName1(blackInfo.getContactName1());
		blackInfoResultDTO.setContactTell1(blackInfo.getContactTell1());
		blackInfoResultDTO.setContactWork1(blackInfo.getContactWork1());
		blackInfoResultDTO.setContactRelationship1(blackInfo.getContactRelationship1());
		blackInfoResultDTO.setContactName2(blackInfo.getContactName2());
		blackInfoResultDTO.setContactTell2(blackInfo.getContactTell2());
		blackInfoResultDTO.setContactWork2(blackInfo.getContactWork2());
		blackInfoResultDTO.setContactRelationship2(blackInfo.getContactRelationship2());
		blackInfoResultDTO.setContactName3(blackInfo.getContactName3());
		blackInfoResultDTO.setContactTell3(blackInfo.getContactTell3());
		blackInfoResultDTO.setContactWork3(blackInfo.getContactWork3());
		blackInfoResultDTO.setContactRelationship3(blackInfo.getContactRelationship3());
		return blackInfoResultDTO;
	}

}
