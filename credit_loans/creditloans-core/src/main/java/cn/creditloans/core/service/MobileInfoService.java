package cn.creditloans.core.service;

import java.io.IOException;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import cn.creditloans.core.dto.mobile.MobilePropDTO;

/**
 * 
 * @author Administrator
 *
 */
public interface MobileInfoService {
	
	/**
	 * 根据手机号码获取验证码信息
	 * @param mobilePropDTO
	 * @return
	 */
	public String getMobileLoginValidate(MobilePropDTO mobilePropDTO);
	
	/**
	 * 手机登陆成功后查询信息
	 * @param mobilePropDTO
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws Exception 
	 */
	public Map<String, Object> getMobileResult(MobilePropDTO mobilePropDTO) throws ClientProtocolException, IOException, Exception;

}
