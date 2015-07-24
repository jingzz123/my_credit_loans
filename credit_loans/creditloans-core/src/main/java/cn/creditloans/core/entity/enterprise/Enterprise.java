package cn.creditloans.core.entity.enterprise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 企业信息实体
 * @author Austin
 *
 */
public class Enterprise {
	
	private int id;
	
	/**
	 * 企业类型id
	 */
	private int typeId;
	
	/**
	 * 上级企业id
	 */
	private int fid;
	
	private String name;
	
	private String tel;
	
	private String fax;
	
	private String postcode;
	
	private String email;
	
	private String address;
	
	private String orgCode;

	private String shortName;

	private String englishName;

	private String prepareApprovalCode;

	private String businessApprovalCode;

	private String businessDate;

	private String countryTaxationCode;

	private String landTaxationCode;

	private String registeraAddr;

	private String registerCode;

	private String registeredCapital;

	private String legalPerson;

	private String acreage;
	
	private String website;
	
	private String note;
	
	private String code;
	
	/**
	 * 是否有效：0有效,1无效
	 */
	private int isinvalid;
	
	private Date createTime;
	
	private Date updateTime;
	
	private int createType;
	

	//字符串类型ID
	private String strId;
	
	//上级企业名称
	private String parentName;
	
	private List<EnterpriseRoleMenu> enterpriseRoleMenuList = new ArrayList<EnterpriseRoleMenu>();
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getPrepareApprovalCode() {
		return prepareApprovalCode;
	}

	public void setPrepareApprovalCode(String prepareApprovalCode) {
		this.prepareApprovalCode = prepareApprovalCode;
	}

	public String getBusinessApprovalCode() {
		return businessApprovalCode;
	}

	public void setBusinessApprovalCode(String businessApprovalCode) {
		this.businessApprovalCode = businessApprovalCode;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getCountryTaxationCode() {
		return countryTaxationCode;
	}

	public void setCountryTaxationCode(String countryTaxationCode) {
		this.countryTaxationCode = countryTaxationCode;
	}

	public String getLandTaxationCode() {
		return landTaxationCode;
	}

	public void setLandTaxationCode(String landTaxationCode) {
		this.landTaxationCode = landTaxationCode;
	}

	public String getRegisteraAddr() {
		return registeraAddr;
	}

	public void setRegisteraAddr(String registeraAddr) {
		this.registeraAddr = registeraAddr;
	}

	public String getRegisterCode() {
		return registerCode;
	}

	public void setRegisterCode(String registerCode) {
		this.registerCode = registerCode;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getAcreage() {
		return acreage;
	}

	public void setAcreage(String acreage) {
		this.acreage = acreage;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getIsinvalid() {
		return isinvalid;
	}

	public void setIsinvalid(int isinvalid) {
		this.isinvalid = isinvalid;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getCreateType() {
		return createType;
	}

	public void setCreateType(int createType) {
		this.createType = createType;
	}

	public String getStrId() {
		return strId;
	}

	public void setStrId(String strId) {
		if(strId != null && "" != strId){
			id = Integer.parseInt(strId);
		}
		this.strId = strId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public List<EnterpriseRoleMenu> getEnterpriseRoleMenuList() {
		return enterpriseRoleMenuList;
	}

	public void setEnterpriseRoleMenuList(
			List<EnterpriseRoleMenu> enterpriseRoleMenuList) {
		this.enterpriseRoleMenuList = enterpriseRoleMenuList;
	}
}
