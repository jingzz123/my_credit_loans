package cn.creditloans.ui.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/*
 * 模拟登录系统的简单示例
 * */

public class Test {
	static String sURL="https://bj.ac.10086.cn/ac/CmSsoLogin";
	static String sURL2="https://bj.ac.10086.cn/login";
	static String responseCookie;//标示Session必须
	
	//测试登录功能，返回“自动”登录后的页面
	public static String login(String pwd) throws IOException
	{
		StringBuilder sbR = new StringBuilder();
		
		//访问URL，并把信息存入sb中
		//如果服务端登录成功后，服务端的代码调用下面的代码 
		//response.sendRedirect("welcome.jsp");
		//则会不成功，原因(Step2，没有上传jsessionid值，导致没session)如下
		//Step1[login.jsp登录成功]->转到->
		//Step2[welcome.jsp不能得到session，判断没有登录成功]->转到->Step3[login.jsp要求用户登录]
		
		URL url = new URL(sURL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);//允许连接提交信息		
		connection.setRequestMethod("POST");//网页默认“GET”提交方式

		StringBuffer sb = new StringBuffer();
		sb.append("loginMode=1");
		sb.append("&loginMethod=1");
		sb.append("&user=15101652085");
		sb.append("&phone=15101652085");
		sb.append("&password=771107");
		sb.append("&rnum="+pwd);
		connection.setRequestProperty("Content-Length", 
				String.valueOf(sb.toString().length()));   
		
		OutputStream os = connection.getOutputStream();
		os.write(sb.toString().getBytes());
		os.close();

		//取Cookie
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		responseCookie = connection.getHeaderField("Set-Cookie");//取到所用的Cookie
		System.out.println("cookie:" + responseCookie);
		
		//取返回的页面
		String line = br.readLine();
		while (line != null) {
			sbR.append(line);
			line = br.readLine();
			System.out.println(line);
		}
		
		return sbR.toString();
	}
	
	//返回页面
	public static String viewPage() throws IOException
	{
		StringBuilder sbR = new StringBuilder();
		
		//打开URL连接
		URL url1 = new URL(sURL2);
		HttpURLConnection connection1 = (HttpURLConnection) url1.openConnection();
		
		//给服务器送登录后的cookie
		connection1.setRequestProperty("Cookie", responseCookie);
		
		//读取返回的页面信息到br1
		BufferedReader br1 = new BufferedReader(new InputStreamReader(connection1.getInputStream()));

		//取返回的页面,br1转sbR
		String line1= br1.readLine();
		while (line1 != null) {
			sbR.append(line1);
			line1 = br1.readLine();
		}
		
		return sbR.toString();	
	}	
	
	public static void main(String[] args) throws Exception {
		//viewPage();
//		login("mp3y");
		System.out.println("--");
		download("https://bj.ac.10086.cn/ac/ValidateNum?smartID=" + System.currentTimeMillis(), "51bi.png","D:\\image\\");
		
	}
	
	 public static void download(String urlString, String filename,String savePath) throws Exception {  
	        // 构造URL  
	        URL url = new URL(urlString);  
	        // 打开连接  
	        URLConnection con = url.openConnection();  
	        //设置请求超时为5s  
	        con.setConnectTimeout(5*1000);  
	        // 输入流  
	        InputStream is = con.getInputStream();  
	      
	        // 1K的数据缓冲  
	        byte[] bs = new byte[1024];  
	        // 读取到的数据长度  
	        int len;  
	        // 输出的文件流  
	       File sf=new File(savePath);  
	       if(!sf.exists()){  
	           sf.mkdirs();  
	       }  
	       OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);  
	        // 开始读取  
	        while ((len = is.read(bs)) != -1) {  
	          os.write(bs, 0, len);  
	        }  
	        // 完毕，关闭所有链接  
	        os.close();  
	        is.close();  
	    }
}
