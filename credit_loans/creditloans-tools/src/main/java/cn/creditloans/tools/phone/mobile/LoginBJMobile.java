package cn.creditloans.tools.phone.mobile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.creditloans.tools.phone.tools.Tools;

/**
 * Created by amosli on 14-7-8.
 */
public class LoginBJMobile {
	
	private static final Log logger = LogFactory.getLog(LoginBJMobile.class);
	/** 登录 URL */
	public static final String LOGIN_URL = "https://bj.ac.10086.cn/ac/cmsso/iloginnew.jsp";
	/** 验证图片 URL */
	public static final String VALIDATE_URL = "https://bj.ac.10086.cn/ac/ValidateNum?smartID=" + System.currentTimeMillis();
	
	public static void getInfo(String phone, String password, String imagCaptcha) throws Exception {
		HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = null;
        List<NameValuePair> params = new ArrayList<NameValuePair>();

        HttpGet httpGet = new HttpGet(LOGIN_URL);
        HttpResponse loginResponse = httpClient.execute(httpGet);
        String result = EntityUtils.toString(loginResponse.getEntity());
        int index = result.indexOf("id='NumImage'");
        test(httpClient, httpPost, params, phone, password, imagCaptcha);
	}
	
	public static void test (HttpClient httpClient, HttpPost httpPost, List<NameValuePair> params, String phone, String password, String imagCaptcha) throws Exception {
		String result = "";
//		Tools.download("https://bj.ac.10086.cn/ac/ValidateNum?smartID=" + System.currentTimeMillis(), "51bi.png","D:\\image\\");
//        Tools.saveToLocal(loginResponse.getEntity(), "GDMOBile.imagCaptcha." + System.currentTimeMillis() + ".png");
		do {
			String url = "https://bj.ac.10086.cn/ac/CmSsoLogin";
			params.add(new BasicNameValuePair("loginMode", "1"));
			params.add(new BasicNameValuePair("loginMethod", "1"));
			params.add(new BasicNameValuePair("phone", phone));
			params.add(new BasicNameValuePair("password", "771107"));
			params.add(new BasicNameValuePair("rnum", "wympc"));
			params.add(new BasicNameValuePair("service", ""));
			params.add(new BasicNameValuePair("backurl", LOGIN_URL));
			params.add(new BasicNameValuePair("box", ""));
			params.add(new BasicNameValuePair("smsNum", ""));
			params.add(new BasicNameValuePair("user", ""));
			params.add(new BasicNameValuePair("style", ""));
			params.add(new BasicNameValuePair("target", ""));
			params.add(new BasicNameValuePair("continue", "http://www.bj.10086.cn/my"));
			
			httpPost = new HttpPost(url);
//            httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			httpPost.setEntity(new UrlEncodedFormEntity(params));
			result = EntityUtils.toString(httpClient.execute(httpPost).getEntity());
		}while(result.contains("验证码不正确"));
		logger.debug("result:" + result);
		
	}
	
	public static void main(String[] args) {
		System.out.println(System.currentTimeMillis());
	}

}
