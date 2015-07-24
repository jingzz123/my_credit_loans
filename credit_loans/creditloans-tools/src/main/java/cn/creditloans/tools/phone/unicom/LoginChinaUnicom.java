package cn.creditloans.tools.phone.unicom;

import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import cn.creditloans.tools.phone.tools.TimeMethodUtil;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author amosli 登录并抓取中国联通数据
 */

public class LoginChinaUnicom {

	private CloseableHttpClient httpClient = null;
	
	private JSONObject loginResultJson;
	
	public JSONObject getLoginResultJson() {
		return loginResultJson;
	}

	public void setLoginResultJson(JSONObject loginResultJson) {
		this.loginResultJson = loginResultJson;
	}
	
	public LoginChinaUnicom () {
		init();
	}

	public void init() {
		try{
	        CookieStore cookieStore = new BasicCookieStore();  
	        //HttpClientContext context = HttpClientContext.create();  
	        //context.setCookieStore(cookieStore);  
	        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();  
	        SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(); 
	        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
	        httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).setDefaultCookieStore(cookieStore).setSSLSocketFactory(sslsf).build();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void close(){
		try{
			httpClient.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean login(String name, String pwd) {
		try {
			//jQuery17209520785977288581_1435309719096
			//jQuery17202691898950318097_1403425938090
			//jQuery17206550459999326946
			StringBuffer urlBuf = new StringBuffer("https://uac.10010.com/portal/Service/MallLogin?callback=jQuery17209520785977288581_");
			urlBuf.append(System.currentTimeMillis()).append("&redirectURL=http%3A%2F%2Fwww.10010.com&userName=");
			urlBuf.append(name).append("&password=").append(pwd);
			urlBuf.append("&pwdType=01&productType=01&redirectType=01&rememberMe=1");
	        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20*1000).setConnectTimeout(20*1000).build();//设置请求和传输超时时间
	        HttpGet httpGet = new HttpGet(urlBuf.toString());
	        httpGet.setConfig(requestConfig);
	        HttpResponse loginResponse = httpClient.execute(httpGet);
			if (loginResponse.getStatusLine().getStatusCode() == 200) {
				for (Header head : loginResponse.getAllHeaders()) {
					System.out.println(head);
				}
				HttpEntity loginEntity = loginResponse.getEntity();
				String loginEntityContent = EntityUtils.toString(loginEntity);
				int indexStart = loginEntityContent.indexOf("{");
				int indexEnd = loginEntityContent.lastIndexOf("}");
				loginEntityContent = loginEntityContent.substring(indexStart, indexEnd + 1);
				indexStart = loginEntityContent.indexOf("<");
				indexEnd = loginEntityContent.lastIndexOf(">");
				if (indexStart != -1 && indexEnd != -1) {
					loginEntityContent = loginEntityContent.replace(loginEntityContent.substring(indexStart, indexEnd + 1), "");
				}
				this.loginResultJson = JSON.parseObject(loginEntityContent);
				System.out.println("登录状态:" + loginEntityContent);
				if (loginEntityContent.contains("resultCode:\"0000\"")) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * 个人基础信息
	 * {"isLogin":true,"result":true,"binding":false,
"userInfo":{
"packageName":"3G-96元后付费基本套餐A","status":"开通",
"expireTime":"1436093288152",
"usernumber":"18600367742","nettype":"02","areaCode":"",
"certnum":"32102819771107641X",
"opendate":"20120802125603",
"productId":"18600367742",
"paytype":"2","provincecode":"011",
"custName":"吴志国","citycode":"110",
"brand":"9","loginType":"01","customid":"6412100827708707",
"currentID":"18600367742","custlvl":"无等级","nickName":"吴志国",
"brand_name":"沃","is_wo":"2","lastLoginTime":"2015-07-05 16:47:22",
"loginCustid":"6412100827708707","verifyState":"","defaultFlag":"00","isINUser":"0000","packageID":"99002138","mapExtraParam_rls":"01","custsex":"1","certtype":"02","certaddr":"未知","subscrbstat":"开通",
"laststatdate":"","is_20":false,"is_36":false}}
	 */
	public JSONObject personInfo(){
		try{
			String staticUrl = "http://iservice.10010.com/e3/static/check/checklogin/?_="
					+ System.currentTimeMillis();
			System.out.println(staticUrl);
			HttpPost httpPost = new HttpPost(staticUrl);
			HttpResponse response = httpClient.execute(httpPost);
			System.out.println("status code is " + response.getStatusLine().getStatusCode());
			if (response.getStatusLine().getStatusCode() == 200) {
				String personInfoEntity = EntityUtils.toString(response.getEntity());
				int indexStart = personInfoEntity.indexOf("{");
				int indexEnd = personInfoEntity.lastIndexOf("}");
				personInfoEntity = personInfoEntity.substring(indexStart, indexEnd + 1);
				return JSON.parseObject(personInfoEntity);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 历史汇总
	 */
	public Map<String, JSONObject> historySummary(int size) {
		Map<String, JSONObject>map = new HashMap<String, JSONObject>();
		try {
			String months[] = new String[size];
			for(int i=1; i<= size; i++){
				months[i-1] = TimeMethodUtil.getProviousNYearMonth(i);
			}
			for (String month : months) {
				// http://iservice.10010.com/ehallService/static/historyBiil/execute/YH102010002/QUERY_YH102010002.processData/QueryYH102010002_Data/201405/undefined?_=1404661790076&menuid=000100020001
				String billurl = "http://iservice.10010.com/ehallService/static/historyBiil/execute/YH102010002/QUERY_YH102010002.processData/QueryYH102010002_Data/"
						+ month + "/undefined";

				HttpPost httpPost = new HttpPost(billurl);
				HttpResponse billresponse = httpClient.execute(httpPost);
				String billEntity = null;
				if (billresponse.getStatusLine().getStatusCode() == 200) {
					billEntity = EntityUtils.toString(billresponse.getEntity());
					int indexStart = billEntity.indexOf("{");
					int indexEnd = billEntity.lastIndexOf("}");
					billEntity = billEntity.substring(indexStart, indexEnd + 1);
				}
				map.put(month, JSON.parseObject(billEntity));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}
	
	/**
	 * 历史详单
	 */
	public void historyDetail(int size) {
		try {
			String monthFirsts[] = new String[size];
			String monthEnds[] = new String[size];
			for(int i=0; i < size; i++){
				monthFirsts[i] = TimeMethodUtil.getPreviousNMonthFirst(i);
				monthEnds[i] = TimeMethodUtil.getPreviousNMonthLast(i);
			}

			for (int i=0; i<monthFirsts.length;i++) {
                String checkUrl = "http://iservice.10010.com/e3/static/check/checklogin?="+System.currentTimeMillis();
                HttpPost checkPost = new HttpPost(checkUrl);
                httpClient.execute(checkPost);
                //HttpEntity checkEntity = checkResponse.getEntity();
                //String userResult = EntityUtils.toString(checkEntity);
				
                String menuid = "000100030001";
                int pageNo = 0;
                int pageSize = 100;
                String beginDate = monthFirsts[i];
                String endDate = monthEnds[i];
                
                int totalPages = 0;
            	int totalCount = 0;
                for(;;){
                	pageNo++;
                	String billUrl = "http://iservice.10010.com/e3/static/query/callDetail?menuid="+menuid+"&pageNo="+pageNo+"&pageSize="+pageSize+"&beginDate="+beginDate+"&endDate="+endDate;
                	HttpPost billPost = new HttpPost(billUrl);
                    HttpResponse billresponse = httpClient.execute(billPost);
                    if (billresponse.getStatusLine().getStatusCode() == 200) {
                    	HttpEntity billEntity = billresponse.getEntity();
                    	String billResult = EntityUtils.toString(billEntity);
                    	Object jsonObject = JSON.parse(billResult);
                    	Object callLogObject = PropertyUtils.getProperty(jsonObject, "pageMap");
                    	totalPages = Integer.parseInt(PropertyUtils.getProperty(callLogObject, "totalPages").toString());
                    	if(pageNo>totalPages){
                    		break;
                    	}
                    	totalCount = Integer.parseInt(PropertyUtils.getProperty(callLogObject, "totalCount").toString());
                    	System.out.println("语音通信详单，共"+totalCount+"条记录，第"+pageNo+"页："+PropertyUtils.getProperty(callLogObject, "result"));
                    }else{
                    	break;
                    }
                }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static void main(String[] args) throws Exception {
		String name = "18600367742";
		String pwd = "771107";
		LoginChinaUnicom obj = new LoginChinaUnicom();
		//obj.init();
		if(obj.login(name, pwd)){
			System.out.println("----------------");
			//obj.historyBill();
//			obj.personInfo();
//			System.out.println(obj.historySummary(2));
			obj.historyDetail(2);
		}
//		String info = "{resultCode:'7007',redirectURL:'http://www.10010.com',errDesc:'null',msg:'用户名或密码不正确，还有3次机会。<a href=\\'https://uac.10010.com/cust/resetpass/resetpassInit\\' target=\\'_blank\\' style=\\'color: #36c;cursor: pointer;text-decoration:underline;\\'>忘记密码？</a>',needvode:'1',errorFrom:'cb'}";
//		int beginIndex = info.indexOf("<");
//		int endIndex = info.lastIndexOf(">");
//		String newInfo = info.substring(beginIndex, endIndex + 1);
//		info = info.replace(newInfo, "");
//		String yyy = "kkkkkkkkkkkkkkkkk<ajhasha>";
//		yyy = yyy.replaceAll("k", "p");
//		String s = (String) JSON.parseObject(info).get("msg");
//		System.out.println(s);
////		java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
////		System.out.println(System.currentTimeMillis());
////        String sb=format.format("1436093288152"); 
////        System.out.println(sb);
//        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//        long now = 1436093288152L;
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(now);
//        System.out.println(now + " = " + formatter.format(calendar.getTime()));
		obj.close();
	}

}


