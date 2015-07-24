package cn.creditloans.ui.controllers;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.creditloans.core.dto.mobile.MobilePropDTO;
import cn.creditloans.core.service.MobileInfoService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/mobileInformation")
public class MobileInformationController {
	
	@Autowired
	private MobileInfoService mobileInfoService;
	
	@RequestMapping(value="/showMobileInfo", method = {RequestMethod.GET, RequestMethod.POST})
	public String showMobileInfo(Model model) {
		return "/information/mobileinfo/mobile_info1";
	}
	
	/**
	 * 根据手机号码获取运营商访,归属地等信息
	 * @param mobilePhoneNo
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/getMobileType", method = {RequestMethod.GET, RequestMethod.POST})
	public String getMobileType(@RequestParam("mobilePhoneNo") String mobilePhoneNo, Model model) throws Exception {
		// 通过拍拍网查询手机归属地，运营商类型信息返回JSON
		String path = "http://virtual.paipai.com/extinfo/GetMobileProductInfo?mobile=" + 
				mobilePhoneNo + "&amount=10000&callname=getPhoneNumInfoExtCallback"; 
		String province = "";
		String cityName = "";
		String isp = "";
        //建立一个URL对象  
        URL url = new URL(path);  
        //得到打开的链接对象  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        //设置请求超时与请求方式  
        conn.setReadTimeout(5*1000);  
        conn.setRequestMethod("GET");  
        //从链接中获取一个输入流对象  
        InputStream inStream = conn.getInputStream();  
        //调用数据流处理方法  
        byte[] data = readInputStream(inStream);  
        String json = new String(data, "GBK");
        if (json != null && json.length() > 0) {
        	int startIndex = json.indexOf("{");
        	int endIndex = json.indexOf("}");
        	json = json.substring(startIndex, endIndex + 1);
        	JSONObject object = JSON.parseObject(json);
        	// 获取省份信息
        	province = (String) object.get("province");
        	// 获城市信息
        	cityName = (String) object.get("cityname");
        	// 获取运营商
        	isp = (String) object.get("isp");
        }
        model.addAttribute("province", province);
        model.addAttribute("cityName", cityName);
        model.addAttribute("isp", isp);
        model.addAttribute("mobilePhoneNo", mobilePhoneNo);
		return "/information/mobileinfo/mobile_info2";
	}
	
	/**
	 * 显示验证码图片
	 * @param mobilePropDTO
	 * @return
	 */
	@RequestMapping(value="/showMobileLoginValidate", method = {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Map<String, String> showMobileLoginValidate(@RequestBody MobilePropDTO mobilePropDTO) {
		Map<String, String> map = new HashMap<String, String>();
		String url = mobileInfoService.getMobileLoginValidate(mobilePropDTO);
		if (url != null) {
			map.put("url", url);
		}
		return map;
	}
	
	/**
	 * 根据手机号码查询信息
	 * @param mobilePropDTO
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/showMobileResult", method = {RequestMethod.GET, RequestMethod.POST})
	public String showMobileResult(@ModelAttribute MobilePropDTO mobilePropDTO, Model model) throws Exception {
		Map<String, Object> map = mobileInfoService.getMobileResult(mobilePropDTO);
		Set<String>set = map.keySet();
		for (String string : set) {
			if ("loginInfo".equals(string) && !"".equals(map.get(string))) {
				model.addAttribute(string, map.get(string));
				model.addAttribute("province", mobilePropDTO.getProvince());
		        model.addAttribute("cityName", mobilePropDTO.getCityName());
		        model.addAttribute("isp", mobilePropDTO.getIsp());
		        model.addAttribute("mobilePhoneNo", mobilePropDTO.getMobile());
				return "/information/mobileinfo/mobile_info2";
			} else {
				model.addAttribute(string, map.get(string));
			}
		}
		return "/information/mobileinfo/mobile_info3";
	} 
	
	// 从响应留中获取JSON串
	public static byte[] readInputStream(InputStream inStream) throws Exception{  
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] buffer = new byte[1024];  
        int len = 0;  
        while( (len=inStream.read(buffer)) != -1 ){  
            outStream.write(buffer, 0, len);  
        }  
        inStream.close();  
        return outStream.toByteArray();  
    }  

}
