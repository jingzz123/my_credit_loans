package cn.creditloans.tools.phone.unicom;

import com.alibaba.fastjson.JSON;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;

public class LoginChinaUnicomSuccess {
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        String name =  "18600367742";
        String pwd = "771107";
        
        String url = "https://uac.10010.com/portal/Service/MallLogin?callback=jQuery17202691898950318097_1403425938090&redirectURL=http%3A%2F%2Fwww.10010.com&userName=" + name + "&password=" + pwd + "&pwdType=01&productType=01&redirectType=01&rememberMe=1";
        
        CookieStore cookieStore = new BasicCookieStore();  
        
        HttpClientContext context = HttpClientContext.create();  
        context.setCookieStore(cookieStore);  
          
        RequestConfig globalConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.BROWSER_COMPATIBILITY).build();  
          
        SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build(); 
  
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext,new String[] { "TLSv1" },null,SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);  
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(globalConfig).setDefaultCookieStore(cookieStore).setSSLSocketFactory(sslsf).build();
        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20*1000).setConnectTimeout(20*1000).build();//设置请求和传输超时时间
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        
        HttpResponse loginResponse = httpClient.execute(httpGet);

        if (loginResponse.getStatusLine().getStatusCode() == 200) {
            for (Header head : loginResponse.getAllHeaders()) {
                System.out.println(head);
            }
            HttpEntity loginEntity = loginResponse.getEntity();
            String loginEntityContent = EntityUtils.toString(loginEntity);
            System.out.println("登录状态:" + loginEntityContent);
            System.out.println("-------------------------------------------------------------");
            //如果登录成功
            if (loginEntityContent.contains("resultCode:\"0000\"")) {
                
                String checkUrl = "http://iservice.10010.com/e3/static/check/checklogin";
                HttpPost checkPost = new HttpPost(checkUrl);
                HttpResponse checkResponse = httpClient.execute(checkPost);
                HttpEntity checkEntity = checkResponse.getEntity();
                String userResult = EntityUtils.toString(checkEntity);
                System.out.println("用户信息："+userResult);
                System.out.println("-------------------------------------------------------------");
                
                
                //菜单id
                String menuid = "000100030001";
                int pageNo = 0;
                int pageSize = 100;
                String beginDate = "2015-06-01";
                String endDate = "2015-06-29";
                
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
        }

    }

}
