package cn.creditloans.core.dto.enterprise;

import java.io.Serializable;
import java.util.Date;

import cn.creditloans.tools.context.AppContext;
import cn.creditloans.tools.parameters.ParameterConfig;
import cn.creditloans.tools.util.DateUtils;

public class BlackInfoResultDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 姓名 */
	private String name;
	/** 证件号码 */
	private String idNumber;
	/** 手机 */
	private String mobile;
	/** 是否已婚 1：已婚，2：未婚 */
	private String married;
	/** 家庭住址 */
	private String homeAddress;
	/** 家庭电话 */
	private String homeTell;
	/** 单位名称 */
	private String workName;
	/** 单位地址 */
	private String workAddress;
	/** 单位电话 */
	private String workTell;
	/** 贷款类型 */
	private String loanType;
	/** 确认状态 */
	private String confirmedType;
	/** */
	private String confirmedDetails;
	/** 贷款申请日期 */
	private String appliedOn;
	/** 确认日期 */
	private String confirmedDate;
	/** 备注 */
	private String comments;
	
	private String contactName1;
	
	private String contactWork1;
	
	private String contactTell1;
	
	private String contactRelationship1;
	
	private String contactName2;
	
	private String contactWork2;
	
	private String contactTell2;
	
	private String contactRelationship2;
	
	private String contactName3;
	
	private String contactWork3;
	
	private String contactTell3;
	
	private String contactRelationship3;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMarried() {
		return married;
	}

	public void setMarried(int married) {
		if(married == 1) {
			this.married = "已婚";
		} else {
			this.married = "未婚";
		}
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomeTell() {
		return homeTell;
	}

	public void setHomeTell(String homeTell) {
		this.homeTell = homeTell;
	}

	public String getWorkName() {
		return workName;
	}

	public void setWorkName(String workName) {
		this.workName = workName;
	}

	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}

	public String getWorkTell() {
		return workTell;
	}

	public void setWorkTell(String workTell) {
		this.workTell = workTell;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(int loanType) {
		this.loanType = AppContext.getInstance().getPrCongif().
				getParameterInfo(ParameterConfig.LOAN_TYPE).getValue(String.valueOf(loanType));
	}

	public String getConfirmedType() {
		return confirmedType;
	}

	public void setConfirmedType(String confirmedType) {
		this.confirmedType = AppContext.getInstance().getPrCongif().
		getParameterInfo(ParameterConfig.CONFIRMED_TYPE).getValue(confirmedType);
	}

	public String getConfirmedDetails() {
		return confirmedDetails;
	}

	public void setConfirmedDetails(int confirmedDetails) {
		this.confirmedType = AppContext.getInstance().getPrCongif().
				getParameterInfo(ParameterConfig.CONFIRMED_DETAILS).getValue(String.valueOf(confirmedDetails));
	}

	public String getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(Date appliedOn) {
		this.appliedOn = DateUtils.DATE_FORMAT.format(appliedOn);
	}

	public String getConfirmedDate() {
		return confirmedDate;
	}

	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = DateUtils.DATE_FORMAT.format(confirmedDate);
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getContactName1() {
		return contactName1;
	}

	public void setContactName1(String contactName1) {
		this.contactName1 = contactName1;
	}

	public String getContactWork1() {
		return contactWork1;
	}

	public void setContactWork1(String contactWork1) {
		this.contactWork1 = contactWork1;
	}

	public String getContactTell1() {
		return contactTell1;
	}

	public void setContactTell1(String contactTell1) {
		this.contactTell1 = contactTell1;
	}

	public String getContactRelationship1() {
		return contactRelationship1;
	}

	public void setContactRelationship1(int contactRelationship1) {
		this.contactRelationship1 = AppContext.getInstance().getPrCongif().
				getParameterInfo(ParameterConfig.CONTACTRELATIONSHIP).getValue(String.valueOf(contactRelationship1));
	}

	public String getContactName2() {
		return contactName2;
	}

	public void setContactName2(String contactName2) {
		this.contactName2 = contactName2;
	}

	public String getContactWork2() {
		return contactWork2;
	}

	public void setContactWork2(String contactWork2) {
		this.contactWork2 = contactWork2;
	}

	public String getContactTell2() {
		return contactTell2;
	}

	public void setContactTell2(String contactTell2) {
		this.contactTell2 = contactTell2;
	}

	public String getContactRelationship2() {
		return contactRelationship2;
	}

	public void setContactRelationship2(int contactRelationship2) {
		this.contactRelationship2 = AppContext.getInstance().getPrCongif().
				getParameterInfo(ParameterConfig.CONTACTRELATIONSHIP).getValue(String.valueOf(contactRelationship2));
	}

	public String getContactName3() {
		return contactName3;
	}

	public void setContactName3(String contactName3) {
		this.contactName3 = contactName3;
	}

	public String getContactWork3() {
		return contactWork3;
	}

	public void setContactWork3(String contactWork3) {
		this.contactWork3 = contactWork3;
	}

	public String getContactTell3() {
		return contactTell3;
	}

	public void setContactTell3(String contactTell3) {
		this.contactTell3 = contactTell3;
	}

	public String getContactRelationship3() {
		return contactRelationship3;
	}

	public void setContactRelationship3(int contactRelationship3) {
		this.contactRelationship3 = AppContext.getInstance().getPrCongif().
				getParameterInfo(ParameterConfig.CONTACTRELATIONSHIP).getValue(String.valueOf(contactRelationship3));
	}
	
}
