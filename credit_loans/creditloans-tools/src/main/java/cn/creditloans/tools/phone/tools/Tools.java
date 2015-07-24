package cn.creditloans.tools.phone.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;


/**
 * Created by amosli on 14-6-25.
 */
public class Tools {
	
//	static String str = Tools.class.getClassLoader().getResource("/data").getPath();
	static String str = "";

	/**
	 * 写文件到本地
	 * 
	 * @param httpEntity
	 * @param filename
	 */
	public static void saveToLocal(HttpEntity httpEntity, String filename) {

		try {

			File dir = new File(str);
			if (!dir.isDirectory()) {
				dir.mkdir();
			}

			File file = new File(dir.getAbsolutePath() + "/" + filename);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			InputStream inputStream = httpEntity.getContent();

			byte[] bytes = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(bytes)) > 0) {
				fileOutputStream.write(bytes, 0, length);
			}
			inputStream.close();
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 写文件到本地
	 * 
	 * @param bytes
	 * @param filename
	 */
	public static void saveToLocalByBytes(byte[] bytes, String filename) {

		try {

			File dir = new File(str);
			if (!dir.isDirectory()) {
				dir.mkdir();
			}

			File file = new File(dir.getAbsolutePath() + "/" + filename);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(bytes);
			// fileOutputStream.write(bytes, 0, bytes.length);
			fileOutputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 输出
	 * 
	 * @param string
	 */
	public static void println(String string) {
		System.out.println("string:" + string);
	}

	/**
	 * 输出
	 * 
	 * @param string
	 */
	public static void printlnerr(String string) {
		System.err.println("string:" + string);
	}

	/**
	 * 使用ssl通道并设置请求重试处理
	 * 
	 * @return
	 */
	public static CloseableHttpClient createSSLClientDefault() {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();

			// 自定义的https请求
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext);

			// 设置请求重试处理,重试机制,这里如果请求失败会重试5次
			HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
				@Override
				public boolean retryRequest(IOException exception,
						int executionCount, HttpContext context) {
					if (executionCount >= 5) {
						// Do not retry if over max retry count
						return false;
					}
					if (exception instanceof InterruptedIOException) {
						// Timeout
						return false;
					}
					if (exception instanceof UnknownHostException) {
						// Unknown host
						return false;
					}
					if (exception instanceof ConnectTimeoutException) {
						// Connection refused
						return false;
					}
					if (exception instanceof SSLException) {
						// SSL handshake exception
						return false;
					}
					HttpClientContext clientContext = HttpClientContext
							.adapt(context);
					HttpRequest request = clientContext.getRequest();
					boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
					if (idempotent) {
						// Retry if the request is considered idempotent
						return true;
					}
					return false;
				}
			};

			// 请求参数设置,设置请求超时时间为20秒,连接超时为10秒,不允许循环重定向
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(20000)
					.setConnectTimeout(20000)
					.setCircularRedirectsAllowed(false)
					.setRedirectsEnabled(false).build();

			return HttpClients
					.custom()
					.setSSLSocketFactory(sslsf)
					.setUserAgent(
							"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36")
					.setMaxConnPerRoute(25).setMaxConnPerRoute(256)
					.setRetryHandler(retryHandler)
					.setRedirectStrategy(new SelfRedirectStrategy())
					.setDefaultRequestConfig(requestConfig).build();

		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

	/**
	 * 带cookiestore
	 * 
	 * @param cookieStore
	 * @return
	 */

	public static CloseableHttpClient createSSLClientDefaultWithCookie(
			CookieStore cookieStore) {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(
					null, new TrustStrategy() {
						// 信任所有
						public boolean isTrusted(X509Certificate[] chain,
								String authType) throws CertificateException {
							return true;
						}
					}).build();

			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
					sslContext);

			// 设置请求重试处理,重试机制,这里如果请求失败会重试5次
			HttpRequestRetryHandler retryHandler = new HttpRequestRetryHandler() {
				@Override
				public boolean retryRequest(IOException exception,
						int executionCount, HttpContext context) {
					if (executionCount >= 5) {
						// Do not retry if over max retry count
						return false;
					}
					if (exception instanceof InterruptedIOException) {
						// Timeout
						return false;
					}
					if (exception instanceof UnknownHostException) {
						// Unknown host
						return false;
					}
					if (exception instanceof ConnectTimeoutException) {
						// Connection refused
						return false;
					}
					if (exception instanceof SSLException) {
						// SSL handshake exception
						return false;
					}
					HttpClientContext clientContext = HttpClientContext
							.adapt(context);
					HttpRequest request = clientContext.getRequest();
					boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
					if (idempotent) {
						// Retry if the request is considered idempotent
						return true;
					}
					return false;
				}
			};

			// 请求参数设置,设置请求超时时间为20秒,连接超时为10秒,不允许循环重定向
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectionRequestTimeout(20000)
					.setConnectTimeout(20000)
					.setCircularRedirectsAllowed(false).build();

			return HttpClients
					.custom()
					.setSSLSocketFactory(sslsf)
					.setUserAgent(
							"Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36")
					.setMaxConnPerRoute(25).setMaxConnPerRoute(256)
					.setRetryHandler(retryHandler)
					.setRedirectStrategy(new SelfRedirectStrategy())
					.setDefaultRequestConfig(requestConfig)
					.setDefaultCookieStore(cookieStore).build();

		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return HttpClients.createDefault();
	}

	/**
	 * 提取数字
	 * 
	 * @param value
	 * @return
	 */
	public static String GetNumber(String value) {
		return value.replaceAll("\\D", "");
	}

	/**
	 * 在使用httpClient执行Https的URL中出现此错误unable to find valid certification path to requested target 的简单解决办法！！
	 * 解决方法，在httpClient执行的之前调用此方法
	 * Trust every server - dont check for any certificate
	 */
	public static void trustAllHosts() {
		// Create a trust manager that does not validate certificate chains
		TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return new java.security.cert.X509Certificate[] {};
			}

			public void checkClientTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) throws CertificateException {
			}
		} };

		try {
			SSLContext sc = SSLContext.getInstance("TLS");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection
					.setDefaultSSLSocketFactory(sc.getSocketFactory());
		} catch (Exception e) {
			e.printStackTrace();
		}
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
