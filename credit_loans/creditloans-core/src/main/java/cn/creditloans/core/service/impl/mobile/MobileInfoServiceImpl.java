package cn.creditloans.core.service.impl.mobile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import cn.creditloans.core.dto.mobile.MobilePropDTO;
import cn.creditloans.core.dto.mobile.UnicomHistoryColumnList;
import cn.creditloans.core.dto.mobile.UnicomHistoryDTO;
import cn.creditloans.core.dto.mobile.UnicomHistoryItem;
import cn.creditloans.core.dto.mobile.UnicomPersonInfoDTO;
import cn.creditloans.core.service.MobileInfoService;
import cn.creditloans.tools.phone.mobile.LoginBJMobile;
import cn.creditloans.tools.phone.mobile.LoginGDMobile;
import cn.creditloans.tools.phone.unicom.LoginChinaUnicom;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service("mobileInfoService")
public class MobileInfoServiceImpl implements MobileInfoService {

	@Override
	public String getMobileLoginValidate(MobilePropDTO mobilePropDTO) {
		String province =  mobilePropDTO.getProvince();
		String isp =  mobilePropDTO.getIsp();
		if (isp.contains("移动")) {
			if (province.contains("北京")) {
				return LoginBJMobile.VALIDATE_URL;
			} else if (province.contains("广东")) {
				return LoginGDMobile.VALIDATE_URL;
			} else if (province.contains("四川")) {
				
			} else if (province.contains("浙江")) {
				
			}
		} else if (isp.contains("联通")) {
			return "";
		} else if (isp.contains("电信")) {
			
		}
		return null;
	}

	@Override
	public Map<String, Object> getMobileResult(MobilePropDTO mobilePropDTO) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String province =  mobilePropDTO.getProvince();
		String isp =  mobilePropDTO.getIsp();
		String phone = mobilePropDTO.getMobile();
		String password = mobilePropDTO.getPassword();
		String imagCaptcha = mobilePropDTO.getImagCaptcha();
		if (isp.contains("移动")) {
			if (province.contains("北京")) {
				LoginBJMobile.getInfo(phone, password, imagCaptcha);
			} else if (province.contains("广东")) {
				
			} else if (province.contains("四川")) {
				
			} else if (province.contains("浙江")) {
				
			}
		} else if (isp.contains("联通")) {
			LoginChinaUnicom obj = new LoginChinaUnicom();
			if(obj.login(phone, password)) {
				map.put("phoneInfo", mobilePropDTO);
				map.put("loginInfo", "");
				JSONObject jsonObject = obj.personInfo();
				UnicomPersonInfoDTO unicomPersonInfoDTO = createUnicomPersonInfoDTOByJson(jsonObject);
				map.put("personInfo", unicomPersonInfoDTO);
				Map<String, JSONObject> historyMap = obj.historySummary(5);
				map.put("historyMonth", historyMap.keySet());
				map.put("historySummary", createUnicomHistoryDTOByJSONArray(historyMap));
//				map.put("historySummary", obj.historySummary(5));
				//obj.historyDetail(5);
				map.put("historyDetail", null);
			} else {
				map.put("loginInfo", obj.getLoginResultJson().get("msg"));
			}
		} else if (isp.contains("电信")) {
			
		}
		return map;
	}
	
	/**
	 * 将Json转换为对象
	 * @param jsonObject
	 * @return
	 */
	private UnicomPersonInfoDTO createUnicomPersonInfoDTOByJson(JSONObject jsonObject) {
		UnicomPersonInfoDTO unicomPersonInfoDTO = new UnicomPersonInfoDTO();
		jsonObject = (JSONObject) jsonObject.get("userInfo");
		unicomPersonInfoDTO.setBrandName(jsonObject.getString("brand_name"));
		unicomPersonInfoDTO.setOpendate(jsonObject.getString("opendate"));
		unicomPersonInfoDTO.setCertaddr(jsonObject.getString("certaddr"));
		unicomPersonInfoDTO.setCustName(jsonObject.getString("custName"));
		unicomPersonInfoDTO.setCustsex(jsonObject.getString("custsex"));
		unicomPersonInfoDTO.setIdNumber(jsonObject.getString("certnum"));
		unicomPersonInfoDTO.setLastLoginTime(jsonObject.getString("lastLoginTime"));
		unicomPersonInfoDTO.setLoginCustid(jsonObject.getString("loginCustid"));
		unicomPersonInfoDTO.setPackageName(jsonObject.getString("packageName"));
		unicomPersonInfoDTO.setPaytype(jsonObject.getString("paytype"));
		unicomPersonInfoDTO.setStatus(jsonObject.getString("status"));
		unicomPersonInfoDTO.setUserumber(jsonObject.getString("usernumber"));
		return unicomPersonInfoDTO;
	}
	
	private List<UnicomHistoryDTO> createUnicomHistoryDTOByJSONArray(Map<String, JSONObject> map) {
		List<UnicomHistoryDTO> unicomHistoryDTOList = new ArrayList<UnicomHistoryDTO>();
		Set<String>set = map.keySet();
		JSONObject jSONObject = null;
		for (String string : set) {
			UnicomHistoryDTO unicomHistoryDTO = new UnicomHistoryDTO();
			unicomHistoryDTO.setMonthKey(string);
			jSONObject = map.get(string);
			jSONObject = jSONObject.getJSONObject("rspPublicArgs");
			unicomHistoryDTO.setUserNumber(jSONObject.getString("userNumber"));
			jSONObject = jSONObject.getJSONObject("rspArgs");
			unicomHistoryDTO.setCustomName(jSONObject.getString("customName"));
			unicomHistoryDTO.setDiscountFee(jSONObject.getString("discountFee"));
			unicomHistoryDTO.setMonth(jSONObject.getString("month"));
			unicomHistoryDTO.setPayTotal(jSONObject.getString("payTotal"));
			unicomHistoryDTO.setPreMonthInterral(jSONObject.getString("preMonthInterral"));
			unicomHistoryDTO.setSumFee(jSONObject.getString("sumFee"));
			unicomHistoryDTO.setUsedIntegral(jSONObject.getString("usedIntegral"));
			unicomHistoryDTO.setUseIntegral(jSONObject.getString("useIntegral"));
			JSONArray itemJSONArray = jSONObject.getJSONArray("list");
			JSONObject itemJSONObject = null;
			List<List<UnicomHistoryItem>>itemLists = new ArrayList<List<UnicomHistoryItem>>();
			List<UnicomHistoryItem>itemList = new ArrayList<UnicomHistoryItem>();
			for (int i = 0; i < itemJSONArray.size(); i++) {
				itemJSONObject = itemJSONArray.getJSONObject(i);
				if (!"".equals(itemJSONObject.getString("style"))) {
					itemList = new ArrayList<UnicomHistoryItem>();
					itemLists.add(itemList);
				} else {
					UnicomHistoryItem unicomHistoryItem = new UnicomHistoryItem();
					unicomHistoryItem.setFee(itemJSONObject.getString("fee"));
					unicomHistoryItem.setName(itemJSONObject.getString("name"));
					itemList.add(unicomHistoryItem);
				}
			}
			JSONArray itemListJSONArray = jSONObject.getJSONArray("itemList");
			JSONObject itemListJSONObject = null;
			List<UnicomHistoryColumnList> unicomHistoryColumnLists = new ArrayList<UnicomHistoryColumnList>();
			int size = itemLists.size();
			for (int i = 0; i < itemListJSONArray.size(); i++) {
				UnicomHistoryColumnList unicomHistoryColumnList = new UnicomHistoryColumnList();
				itemListJSONObject = itemListJSONArray.getJSONObject(i);
				unicomHistoryColumnList.setFee(itemListJSONObject.getString("fee"));
				unicomHistoryColumnList.setName(itemListJSONObject.getString("name"));
				if (i < size) {
					unicomHistoryColumnList.setItemList(itemLists.get(i));
				}
				unicomHistoryColumnLists.add(unicomHistoryColumnList);
			}
			unicomHistoryDTO.setList(unicomHistoryColumnLists);
			unicomHistoryDTOList.add(unicomHistoryDTO);
		}
		return unicomHistoryDTOList;
	}
	
//	public static void main(String[] args) {
//		String st = "{'isLogin':true,'result':true,'binding':false," +
//"'userInfo':{" + 
//"'packageName':'3G-96元后付费基本套餐A','status':'开通'," +
//"'expireTime':'1436093288152'," +
//"'usernumber':'18600367742','nettype':'02','areaCode':''," +
//"'certnum':'32102819771107641X'," +
//"'opendate':'20120802125603'," +
//"'productId':'18600367742'," +
//"'paytype':'2','provincecode':'011'," +
//"'custName':'吴志国','citycode':'110'," +
//"'brand':'9','loginType':'01','customid':'6412100827708707'," +
//"'currentID':'18600367742','custlvl':'无等级','nickName':'吴志国'," +
//"'brand_name':'沃','is_wo':'2','lastLoginTime':'2015-07-05 16:47:22'," +
//"'loginCustid':'6412100827708707','verifyState':'','defaultFlag':'00','isINUser':'0000','packageID':'99002138','mapExtraParam_rls':'01','custsex':'1','certtype':'02','certaddr':'未知','subscrbstat':'开通'," +
//"'laststatdate':'','is_20':false,'is_36':false}}";
//		JSONObject jsonObject = JSON.parseObject(st);
//		UnicomPersonInfoDTO unicomPersonInfoDTO = new UnicomPersonInfoDTO();
//		jsonObject = (JSONObject)jsonObject.get("userInfo");
//		BeanUtils.copyProperties(jsonObject, unicomPersonInfoDTO);
//		System.out.println(unicomPersonInfoDTO);
//	}

}
