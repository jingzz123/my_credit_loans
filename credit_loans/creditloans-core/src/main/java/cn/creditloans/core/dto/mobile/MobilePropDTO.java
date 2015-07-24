package cn.creditloans.core.dto.mobile;

import java.io.Serializable;

/**
 * 手机号的基本信息
 * 
 * @author Administrator
 * 
 */
public class MobilePropDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 手机号码 */
	private String mobile;
	/** 手机运营商 */
	private String isp;
	/** 手机省份 */
	private String province;
	/** 手机城市 */
	private String cityName;
	/** 服务密码 */
	private String password;
	/** 验证码 */
	private String imagCaptcha;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIsp() {
		return isp;
	}

	public void setIsp(String isp) {
		this.isp = isp;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagCaptcha() {
		return imagCaptcha;
	}

	public void setImagCaptcha(String imagCaptcha) {
		this.imagCaptcha = imagCaptcha;
	}

}
