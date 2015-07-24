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

import cn.creditloans.tools.phone.tools.Tools;
 
 
public class DailySign1 {
 
    public static final String URL_LOGIN = "https://bj.ac.10086.cn/ac/cmsso/iloginnew.jsp";
    public static final String URL_IMG = "https://bj.ac.10086.cn/ac/ValidateNum?smartID=" + System.currentTimeMillis();
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
        //创建登陆post请求
        HttpPost validatePost = new HttpPost(URL_IMG);
        HttpResponse validateResponse = client.execute(validatePost);
        Tools.download(URL_IMG, "10086.png", "D:/image/");
        close(validateResponse);
        String authStr = getCookieInfo();
        //创建登陆form
        UrlEncodedFormEntity formEntity = generateLoginFormEntity();   
        HttpPost loginPost = new HttpPost("https://bj.ac.10086.cn/ac/CmSsoLogin");
        loginPost.setEntity(formEntity);
        loginPost.setHeader("Host", "www.bj.10086.cn");
        loginPost.setHeader("Connection", "keep-alive");
        loginPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
        loginPost.setHeader("Cookie", "WT_FPC=id=22f7ffc8032bc1ca7161437292459657:lv=1437294394096:ss=1437292464163; CmLocation=100|100; CmProvid=bj; SSOTime=2015-07-19 16:26:33; mobileNo1=dac0b3613419d05f517ed0d432b71a1f2adb6135@@5bea3bc4903c2915b55d8e40559ba00ac2db0619@@1437292699144; Webtrends=211.90.30.169.1437292699365025; continue=http://www.bj.10086.cn/my; continuelogout=http://www.bj.10086.cn/; JSESSIONID=0000Ey_BdKsHotbjlMoP2OzifE1:14pprnmes; cmtokenid=2c9d82fb4e210db1014ea56bf8fb5024@bj.ac.10086.cn; CmWebtokenid=15101652085,bj; charset=b0c1ded730336a1d00b98894fcb04a5");
        HttpResponse loginResponse = client.execute(loginPost);
//        authStr = getCookieInfo();
//        close(loginResponse);
//        HttpPost tPost = new HttpPost("http://www.bj.10086.cn/www/mybusiness?cmdFlag=phone1");
//        HttpResponse tesponse = client.execute(tPost);
        showHtml(loginResponse);
        //close(loginResponse);
//        //处理登陆响应
//        processResponse(loginResponse);     
//        //登陆成功获取cookie
//        authStr = getCookieInfo();       
//        //创建签到请求
//        HttpGet signGet = new HttpGet(URL_SIGN+authStr);
//        //执行签到请求
//        HttpResponse signResponse = client.execute(signGet);
//        //处理响应
//        showResult(signResponse);
//        //创建领取下载豆请求
//        HttpGet freeCreditsGet = new HttpGet(URL_FREE_CREDITS+authStr);
//        //执行领取下载豆请求
//        HttpResponse freeCreditsResponse = client.execute(freeCreditsGet);
//        //处理响应
//        showResult(freeCreditsResponse);
    }
 
    /**
     * 创建登陆form
     */
    private static UrlEncodedFormEntity generateLoginFormEntity() throws UnsupportedEncodingException {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        formparams.add(new BasicNameValuePair("loginMode", "1"));
        formparams.add(new BasicNameValuePair("loginMethod", "1"));
        formparams.add(new BasicNameValuePair("phone", "15101652085"));
        formparams.add(new BasicNameValuePair("password", "771107"));
        formparams.add(new BasicNameValuePair("rnum", "wbrb"));
//        formparams.add(new BasicNameValuePair("service", ""));
//        formparams.add(new BasicNameValuePair("backurl", ""));
//        formparams.add(new BasicNameValuePair("box", ""));
        formparams.add(new BasicNameValuePair("smsNum", ""));
//        formparams.add(new BasicNameValuePair("user", ""));
//        formparams.add(new BasicNameValuePair("style", ""));
//        formparams.add(new BasicNameValuePair("target", "" ));
//        formparams.add(new BasicNameValuePair("continue", "http://www.bj.10086.cn/my"));
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
    
    public static void close(HttpResponse response) throws IllegalStateException, IOException {
    	try {
            // Get hold of the response entity
            HttpEntity entity = response.getEntity();
            // If the response does not enclose an entity, there is no need
            // to bother about connection release
            if (entity != null) {
                InputStream instream = entity.getContent();
                try {
                    instream.read();
                    // do something useful with the response
                } catch (IOException ex) {
                    // In case of an IOException the connection will be released
                    // back to the connection manager automatically
                    throw ex;
                } finally {
                    // Closing the input stream will trigger connection release
                    instream.close();
                }
            }
        } finally {
        }
    }
    
    public static void showHtml(HttpResponse response) throws ParseException, IOException {
    	HttpEntity entity = response.getEntity();
    	String str = EntityUtils.toString(entity);
    	System.out.println(str);
    }
}
