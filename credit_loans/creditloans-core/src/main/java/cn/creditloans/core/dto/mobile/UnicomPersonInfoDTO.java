package cn.creditloans.core.dto.mobile;

import java.io.Serializable;

/**
 * 联通手机号个人信息
 * @author Administrator
 *
 */
public class UnicomPersonInfoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 当前套餐 */
	private String packageName;
	/** 当前状态 */
	private String status;
	/** 入网日期 */
	private String opendate;
	/** 证件号码 */
	private String idNumber;
	/** 手机号码 */
	private String userumber;
	/** 付款类型 */
	private String paytype;
	/** 用户名 */
	private String custName;
	/** 所属品牌 */
	private String brandName;
	/** 性别 */
	private String custsex;
	/** 地址 */
	private String certaddr;
	/** 最后一次登录时间 */
	private String lastLoginTime;
	/**  */
	private String loginCustid;

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOpendate() {
		return opendate;
	}

	public void setOpendate(String opendate) {
		if (opendate != null && !"".equals(opendate)) {
			opendate = opendate.length() > 8 ? opendate.substring(0, 8) : opendate;
			String strArr[] = opendate.split("");
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < strArr.length; i++) {
				if (i == 4 || i == 6) {
					builder.append(strArr[i]);
					builder.append("-");
				} else {
					builder.append(strArr[i]);
				}
			}
			this.opendate = builder.toString();
		}
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		if (idNumber != null && !"".equals(idNumber)) {
			String[] strArr = idNumber.split("");
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < strArr.length; i++) {
				if (i > 3 && i < 13) {
					builder.append("*");
				} else {
					builder.append(strArr[i]);
				}
			}
			this.idNumber = builder.toString();
		}
	}

	public String getUserumber() {
		return userumber;
	}

	public void setUserumber(String userumber) {
		this.userumber = userumber;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		if ("2".equals(paytype)) {
			this.paytype = "后付费";
		} else {
			this.paytype = "预付费";
		}
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getCustsex() {
		return custsex;
	}

	public void setCustsex(String custsex) {
		if ("1".equals(custsex)) {
			this.custsex = "男";
		} else {
			this.custsex = "女";
		}
	}

	public String getCertaddr() {
		return certaddr;
	}

	public void setCertaddr(String certaddr) {
		this.certaddr = certaddr;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLoginCustid() {
		return loginCustid;
	}

	public void setLoginCustid(String loginCustid) {
		this.loginCustid = loginCustid;
	}
	

	@Override
	public String toString() {
		return "UnicomPersonInfoDTO [packageName=" + packageName + ", status="
				+ status + ", opendate=" + opendate + ", idNumber=" + idNumber
				+ ", userumber=" + userumber + ", paytype=" + paytype
				+ ", custName=" + custName + ", brandName=" + brandName
				+ ", custsex=" + custsex + ", certaddr=" + certaddr
				+ ", lastLoginTime=" + lastLoginTime + ", loginCustid="
				+ loginCustid + "]";
	}

//	public static void main(String[] args) {
//		UnicomPersonInfoDTO unicomPersonInfoDTO = new UnicomPersonInfoDTO();
//		unicomPersonInfoDTO.setCustsex("1");
//		unicomPersonInfoDTO.setOpendate("20120802125603");
//		unicomPersonInfoDTO.setPaytype("2");
//		unicomPersonInfoDTO.setIdNumber("32102819771107641X");
//		System.out.println(unicomPersonInfoDTO.toString());
//	}
}
