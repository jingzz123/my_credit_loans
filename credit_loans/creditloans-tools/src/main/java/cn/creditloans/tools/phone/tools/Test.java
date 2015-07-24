package cn.creditloans.tools.phone.tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;

public class Test {
	public static DefaultHttpClient httpclient = new DefaultHttpClient();
	public static String login_url = "https://bj.ac.10086.cn/ac/cmsso/iloginnew.jsp";
	public static String img_path_url = "https://bj.ac.10086.cn/ac/ValidateNum?smartID=" + System.currentTimeMillis();
	public static String image_save_path = "D:/image/vcode.png";
	public static String update_url = "http://upload.zhuanmi.net/web/upload.do";
	public static String userid = "1883879";
	public static String modify_file_url = "http://newhome.400gb.com/iajax.php?item=file_act";
	public static String delete_file_url = "http://newhome.400gb.com/iajax.php?item=file_act&action=file_delete&task=file_delete&file_id=";

	public static void main(String[] args) throws Exception {
		String cookie = imgCookie();
		System.out.println("img cookie " + cookie);
		String code = identifyImg();

		cookie = loginCookie("", cookie);

		System.out.println("login cookie " + cookie);

	}

	public static String imgCookie() {
		BufferedReader in = null;
		try {
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			HttpGet httpGet = new HttpGet(img_path_url);
			httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					20000);
			HttpResponse response = httpclient.execute(httpGet);
			// 保存图片
			download(response.getEntity().getContent(), image_save_path);
			List<Cookie> cookies = httpclient.getCookieStore().getCookies();
			httpGet.releaseConnection();
			StringBuilder cookiesSB = new StringBuilder();
			System.out.println("第一次cookie");
			if (cookies.isEmpty()) {
				System.out.println("None");
			} else {
				for (int i = 0; i < cookies.size(); i++) {
					// System.out.println("- " + cookies.get(i).toString());
					cookiesSB.append(cookies.get(i).getName()).append("=")
							.append(cookies.get(i).getValue()).append("; ");
				}
			}

			return cookiesSB.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	public static String identifyImg() throws Exception {
		//Tools.download(img_path_url, "image.png", "D:\\image\\");
		return "";
	}

	public static String loginCookie(String code, String cookie) {
		try {
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			// 2 用户登录
			HttpPost httppost = new HttpPost(login_url);
			httppost.setHeader("Cookie", cookie);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("loginMode", "1"));
			nvps.add(new BasicNameValuePair("loginMethod", "1"));
			nvps.add(new BasicNameValuePair("phone", "15101652085"));
			nvps.add(new BasicNameValuePair("password", "771107"));
			nvps.add(new BasicNameValuePair("rnum", "wympc"));
			nvps.add(new BasicNameValuePair("backurl", "https://bj.ac.10086.cn/ac/CmSsoLogin"));
			nvps.add(new BasicNameValuePair("service", ""));
			nvps.add(new BasicNameValuePair("box", ""));

			httppost.setHeader("Host", "www.400gb.com");
			httppost.setHeader("Origin", "http://www.400gb.com");
			httppost.setHeader("Referer", "http://www.400gb.com/index.php");
			httppost.setHeader(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36 CoolNovo/2.0.9.19");

			httppost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
			httppost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					20000);
			HttpResponse response = httpclient.execute(httppost);

			System.out.println("Login form get: "
					+ response.getStatusLine().getStatusCode());

			// System.out.println(sb.toString());

			List<Cookie> cookies = httpclient.getCookieStore().getCookies();
			httppost.releaseConnection();
			StringBuilder cookiesSB = new StringBuilder();
			System.out.println("第一次cookie");
			if (cookies.isEmpty()) {
				System.out.println("None");
			} else {
				for (int i = 0; i < cookies.size(); i++) {
					// System.out.println("- " + cookies.get(i).toString());
					cookiesSB.append(cookies.get(i).getName()).append("=")
							.append(cookies.get(i).getValue()).append("; ");
				}
			}

			httppost.releaseConnection();

			return cookiesSB.toString();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	public static String rend(String cookie) {
		BufferedReader in = null;

		try {
			// 1 获取 _tb_token_
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			HttpGet httpGet = new HttpGet("http://www.400gb.com/index.php");
			httpGet.setHeader("Cookie", cookie);
			httpGet.setHeader("Host", "www.400gb.com");
			httpGet.setHeader("Referer", "http://www.400gb.com/index.php");
			httpGet.setHeader(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36 CoolNovo/2.0.9.19");

			httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					20000);
			HttpResponse response = httpclient.execute(httpGet);

			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent(), "utf-8"));
			StringBuilder sb = new StringBuilder();
			String s = "";
			while ((s = in.readLine()) != null) {
				sb.append(s.trim()).append("\n");
			}

			System.out.println(sb.toString());

			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();

			return null;
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}


	public static void addDesc(String cookie, long fileid, String desc) {
		BufferedReader in = null;
		try {
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			// 2 用户登录
			HttpPost httppost = new HttpPost(modify_file_url);
			httppost.setHeader("Cookie", cookie);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("action", "file_modify"));
			nvps.add(new BasicNameValuePair("task", "file_modify"));
			nvps.add(new BasicNameValuePair("formhash", "a410f69a"));
			nvps.add(new BasicNameValuePair("file_id", String.valueOf(fileid)));
			nvps.add(new BasicNameValuePair("file_name", "vcro3de.png"));
			nvps.add(new BasicNameValuePair("file_description", desc));

			httppost.setHeader("Host", "newhome.400gb.com");
			httppost.setHeader("Origin", "http://newhome.400gb.com");
			httppost.setHeader("Referer",
					"http://newhome.400gb.com/?item=files&action=index");
			httppost.setHeader(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36 CoolNovo/2.0.9.19");

			httppost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));
			httppost.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					20000);
			HttpResponse response = httpclient.execute(httppost);

			System.out.println("add description code: "
					+ response.getStatusLine().getStatusCode());

			in = new BufferedReader(new InputStreamReader(response.getEntity()
					.getContent(), "utf-8"));
			StringBuilder sb = new StringBuilder();
			String s = "";
			while ((s = in.readLine()) != null) {
				sb.append(s.trim()).append("\n");
			}

			httppost.releaseConnection();
			// 返回OK 就证明修改成功，否则有可能文件重名了
			System.out.println("update desc html " + sb.toString());

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

	public static void deleteFile(String cookie, long fileid) {
		try {
			httpclient.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 20000);
			HttpGet httpGet = new HttpGet(delete_file_url + fileid);
			httpGet.setHeader("Cookie", cookie);
			httpGet.setHeader("Host", "newhome.400gb.com");
			httpGet.setHeader("Referer",
					"http://newhome.400gb.com/?item=files&action=index");
			httpGet.setHeader(
					"User-Agent",
					"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36 CoolNovo/2.0.9.19");

			httpGet.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					20000);
			HttpResponse response = httpclient.execute(httpGet);
			System.out.println("delete file code "
					+ response.getStatusLine().getStatusCode());
			httpGet.releaseConnection();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}

	public static boolean download(InputStream in, String path) {
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(path);
			byte b[] = new byte[1024];
			int j = 0;
			while ((j = in.read(b)) != -1) {
				out.write(b, 0, j);
			}
			out.flush();
			File file = new File(path);
			if (file.exists() && file.length() == 0)
				return false;
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if ("FileNotFoundException".equals(e.getClass().getSimpleName()))
				System.err.println("download FileNotFoundException");
			if ("SocketTimeoutException".equals(e.getClass().getSimpleName()))
				System.err.println("download SocketTimeoutException");
			else
				e.printStackTrace();
		} finally {

			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return false;
	}

	/**
	 * 采集
	 * 
	 * @param url
	 *            ：指定URL
	 * @param times
	 *            ：如果采集失败，采集最少次数（2次）
	 * @return
	 */
	public static boolean download(String urlstr, String path) {
		if (urlstr == null || "".equals(urlstr.trim()))
			return false;

		InputStream in = null;
		FileOutputStream out = null;
		try {
			System.out.println("download url " + urlstr);
			URL url = new URL(urlstr);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(5000);// jdk 1.5换成这个,连接超时
			// connection.setReadTimeout(5000);//jdk 1.5换成这个,读操作超时
			connection.setDoOutput(true);

			out = new FileOutputStream(path);
			in = connection.getInputStream();
			byte b[] = new byte[1024];
			int j = 0;
			while ((j = in.read(b)) != -1) {
				out.write(b, 0, j);
			}
			out.flush();
			File file = new File(path);
			if (file.exists() && file.length() == 0)
				return false;
			return true;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			if ("FileNotFoundException".equals(e.getClass().getSimpleName()))
				System.err.println("download FileNotFoundException");
			if ("SocketTimeoutException".equals(e.getClass().getSimpleName()))
				System.err.println("download SocketTimeoutException");
			else
				e.printStackTrace();
		} finally {

			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return false;
	}

	public static InputStream getUrlImg(String URLName) throws Exception {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		int HttpResult = 0; // 服务器返回的状态
		URL url = new URL(URLName); // 创建URL
		URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码urlconn.connect();
		HttpURLConnection httpconn = (HttpURLConnection) urlconn;
		HttpResult = httpconn.getResponseCode();
		System.out.println(HttpResult);
		if (HttpResult != HttpURLConnection.HTTP_OK) { // 不等于HTTP_OK说明连接不成功
			System.out.print("连接失败！");
		} else {
			int filesize = urlconn.getContentLength(); // 取数据长度
			System.out.println(filesize);
			BufferedInputStream bis = new BufferedInputStream(
					urlconn.getInputStream());
			BufferedOutputStream bos = new BufferedOutputStream(os);
			byte[] buffer = new byte[1024]; // 创建存放输入流的缓冲
			int num = -1; // 读入的字节数
			while (true) {
				num = bis.read(buffer); // 读入到缓冲区
				if (num == -1) {
					bos.flush();
					break; // 已经读完
				}
				bos.flush();
				bos.write(buffer, 0, num);
			}
			bos.close();
			bis.close();
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(os.toByteArray());
		return bis;
	}
}
