package cn.creditloans.core.service.impl.mobile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
 
 
public class DailySign {
 
    public static final String URL_LOGIN = "http://home.51cto.com/index.php?s=/Index/doLogin";
    public static final String URL_SIGN = "http://home.51cto.com/index.php?s=/Home/toSign";
    public static final String URL_FREE_CREDITS = "http://down.51cto.com/download.php?do=getfreecredits&t="+System.currentTimeMillis();
     
     
    /**
     * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     * 请修改成自己的用户名跟密码 
     */
    private static String USER = "jingzhan1216@163.com";
    private static String PASSWD = "jingzhan_1216";
     
    public static final DefaultHttpClient client = new DefaultHttpClient();
     
    public static void main(String[] args) throws Exception {
        //创建登陆form
        UrlEncodedFormEntity formEntity = generateLoginFormEntity();        
        //创建登陆post请求
        HttpPost loginPost = new HttpPost(URL_LOGIN);
        loginPost.setEntity(formEntity);        
        //执行登陆请求
        HttpResponse loginResponse = client.execute(loginPost);
        //处理登陆响应
        processResponse(loginResponse);     
        //登陆成功获取cookie
        String authStr = getCookieInfo();       
        //创建签到请求
        HttpGet signGet = new HttpGet(URL_SIGN+authStr);
        //执行签到请求
        HttpResponse signResponse = client.execute(signGet);
        //处理响应
        showResult(signResponse);
        //创建领取下载豆请求
        HttpGet freeCreditsGet = new HttpGet(URL_FREE_CREDITS+authStr);
        //执行领取下载豆请求
        HttpResponse freeCreditsResponse = client.execute(freeCreditsGet);
        //处理响应
        showResult(freeCreditsResponse);
    }
 
    /**
     * 创建登陆form
     */
    private static UrlEncodedFormEntity generateLoginFormEntity() throws UnsupportedEncodingException {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("email", USER));
        formparams.add(new BasicNameValuePair("passwd", PASSWD));
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
        return formEntity;
    }
    
    /**
     * 从cookie中获取SESSIONID,token等信息,从而保证下个请求能够通过登陆验证
     */
    private static String getCookieInfo() {
        List<Cookie> cookies = client.getCookieStore().getCookies();
        String str = "";
        for (Cookie cookie : cookies) {
            str += "&"+cookie.getName()+"="+cookie.getValue();
        }
        return str;
    }
 
    /**
     * 处理登陆响应
     * 登陆成功后，会经过一个过渡页面，这个页面或想其他的子站点发请求来告知是否通过登陆验证
     * 
     */
    private static void processResponse(HttpResponse response) throws IllegalStateException, IOException{
        HttpEntity entity = response.getEntity();
        if(entity != null){
            //读取相应内容
            InputStream instream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            String line = null;
            while((line = reader.readLine()) != null){
                line = new String(line.getBytes(),"UTF-8");
                int begin = line.indexOf("http://");
                int end = line.indexOf("\"></script>", begin);
                HttpGet get = null;
                //截取子站点的网址并发送请求
                while(begin != -1){
                    String url = line.substring(begin, end);
                    System.out.println(url);
                    get = new HttpGet(url);
                    response = client.execute(get);
                    System.out.println(response.getStatusLine().getStatusCode());
                    get.abort();
                    line = line.substring(end);
                    begin = line.indexOf("http://");
                    end = line.indexOf("\"></script>", begin);
                }               
            }
        }
        EntityUtils.consume(entity);
    }
 
    /**
     * 读取相应内容并输出
     */
    public static void showResult(HttpResponse response) throws IOException,
            UnsupportedEncodingException {
        int status = response.getStatusLine().getStatusCode();
        System.out.println(status);
        HttpEntity entity = response.getEntity();
        InputStream instream = null;
        if(entity != null){     
            instream  = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
            String line = null;
            while((line = reader.readLine()) != null){
                line = new String(line.getBytes(),"UTF-8");
                System.out.println(line);
            }
        }
        instream.close();
        EntityUtils.consume(entity);
    }
    
    public static void showHtml(HttpResponse response) throws ParseException, IOException {
    	HttpEntity entity = response.getEntity();
    	String str = EntityUtils.toString(entity);
    	System.out.println(str);
    }
}
