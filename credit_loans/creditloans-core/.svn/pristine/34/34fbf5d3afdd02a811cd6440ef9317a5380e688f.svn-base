package cn.creditloans.core.entity.enterprise;

import java.util.Date;

import cn.creditloans.tools.context.AppContext;
import cn.creditloans.tools.fuzzy.address.AddressFuzzyTool;
import cn.creditloans.tools.fuzzy.corp.CorpFuzzyTool;
import cn.creditloans.tools.parameters.ParameterConfig;
import cn.creditloans.tools.util.DateUtils;
import cn.creditloans.tools.util.UtilMethod;
import cn.creditloans.tools.validator.transaction.Transaction;

/**
 * 黑名单信息
 * @author Administrator
 * 
 */
public class BlackInfo {

	private int id;

	private int userId;

	private int depId;
	/** 姓名 */
	private String name;
	/** 证件号码 */
	private String idNumber;
	/** 手机 */
	private String mobile;
	/** 是否已婚 1：已婚，2：未婚 */
	private int married;
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
	private int loanType;
	/** 确认状态 */
	private String confirmedType;
	/** */
	private int confirmedDetails;
	/** 贷款申请日期 */
	private Date appliedOn;
	/** 确认日期 */
	private Date confirmedDate;
	/** 转白状态 */
	private int turnState;
	/** */
	private Date createTime;
	private Date updateTime;
	/** 备注 */
	private String comments;
	/** 批量id */
	private Integer batchId;
	
	/** 联系人姓名 */
	private String contactName1;
	private String contactName2;
	private String contactName3;
	/** 联系人单位名称 */
	private String contactWork1;
	private String contactWork2;
	private String contactWork3;
	/** 联系人电话 */
	private String contactTell1;
	private String contactTell2;
	private String contactTell3;
	/** 联系人关系 */
	private int contactRelationship1;
	private int contactRelationship2;
	private int contactRelationship3;
	
	/** 标准化操作 */
	private String standardWorkName;
	private String standardWorkAddress;
	private String standardHomeAddress;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

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

	public int getMarried() {
		return married;
	}

	public void setMarried(int married) {
		this.married = married;
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

	public int getLoanType() {
		return loanType;
	}

	public void setLoanType(int loanType) {
		this.loanType = loanType;
	}

	public String getConfirmedType() {
		return confirmedType;
	}

	public void setConfirmedType(String confirmedType) {
		this.confirmedType = confirmedType;
	}

	public int getConfirmedDetails() {
		return confirmedDetails;
	}

	public void setConfirmedDetails(int confirmedDetails) {
		this.confirmedDetails = confirmedDetails;
	}

	public Date getAppliedOn() {
		return appliedOn;
	}

	public void setAppliedOn(Date appliedOn) {
		this.appliedOn = appliedOn;
	}

	public int getTurnState() {
		return turnState;
	}

	public void setTurnState(int turnState) {
		this.turnState = turnState;
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

	public Date getConfirmedDate() {
		return confirmedDate;
	}

	public void setConfirmedDate(Date confirmedDate) {
		this.confirmedDate = confirmedDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Integer getBatchId() {
		return batchId;
	}

	public void setBatchId(Integer batchId) {
		this.batchId = batchId;
	}

	public String getStandardWorkName() {
		return standardWorkName;
	}

	public void setStandardWorkName(String standardWorkName) {
		this.standardWorkName = standardWorkName;
	}

	public String getStandardWorkAddress() {
		return standardWorkAddress;
	}

	public void setStandardWorkAddress(String standardWorkAddress) {
		this.standardWorkAddress = standardWorkAddress;
	}

	public String getStandardHomeAddress() {
		return standardHomeAddress;
	}

	public void setStandardHomeAddress(String standardHomeAddress) {
		this.standardHomeAddress = standardHomeAddress;
	}

	public String getContactName1() {
		return contactName1;
	}

	public void setContactName1(String contactName1) {
		this.contactName1 = contactName1;
	}

	public String getContactName2() {
		return contactName2;
	}

	public void setContactName2(String contactName2) {
		this.contactName2 = contactName2;
	}

	public String getContactName3() {
		return contactName3;
	}

	public void setContactName3(String contactName3) {
		this.contactName3 = contactName3;
	}

	public String getContactWork1() {
		return contactWork1;
	}

	public void setContactWork1(String contactWork1) {
		this.contactWork1 = contactWork1;
	}

	public String getContactWork2() {
		return contactWork2;
	}

	public void setContactWork2(String contactWork2) {
		this.contactWork2 = contactWork2;
	}

	public String getContactWork3() {
		return contactWork3;
	}

	public void setContactWork3(String contactWork3) {
		this.contactWork3 = contactWork3;
	}

	public String getContactTell1() {
		return contactTell1;
	}

	public void setContactTell1(String contactTell1) {
		this.contactTell1 = contactTell1;
	}

	public String getContactTell2() {
		return contactTell2;
	}

	public void setContactTell2(String contactTell2) {
		this.contactTell2 = contactTell2;
	}

	public String getContactTell3() {
		return contactTell3;
	}

	public void setContactTell3(String contactTell3) {
		this.contactTell3 = contactTell3;
	}

	public int getContactRelationship1() {
		return contactRelationship1;
	}

	public void setContactRelationship1(int contactRelationship1) {
		this.contactRelationship1 = contactRelationship1;
	}

	public int getContactRelationship2() {
		return contactRelationship2;
	}

	public void setContactRelationship2(int contactRelationship2) {
		this.contactRelationship2 = contactRelationship2;
	}

	public int getContactRelationship3() {
		return contactRelationship3;
	}

	public void setContactRelationship3(int contactRelationship3) {
		this.contactRelationship3 = contactRelationship3;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlackInfo other = (BlackInfo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public void setData(Transaction tran) {
		ParameterConfig parameterConfig = AppContext.getInstance().getPrCongif();
		this.setName(tran.getString("name"));
		this.setIdNumber(tran.getString("idNumber"));
		this.setMobile(tran.getString("mobile"));
		String strMarried = tran.getString("married");
		if ("已婚".equals(strMarried)) {
			this.setMarried(1);
		} else if ("未婚".equals(strMarried)) {
			this.setMarried(2);
		}
		this.setHomeAddress(tran.getString("homeAddress"));
		this.setHomeTell(tran.getString("homeTell"));
		this.setWorkName(tran.getString("workName"));
		this.setWorkAddress(tran.getString("workAddress"));
		this.setWorkTell(tran.getString("workTell"));
		String strLoanType = tran.getString("loanType");
		strLoanType = parameterConfig.getParameterInfo(ParameterConfig.LOAN_TYPE).getKey(strLoanType);
		if (strLoanType != null && !"".equals(strLoanType)) {
			this.setLoanType(Integer.parseInt(strLoanType));
		}
		String strConfirmedType = tran.getString("confirmedType");
		strConfirmedType = parameterConfig.getParameterInfo(ParameterConfig.CONFIRMED_TYPE).getKey(strConfirmedType);
		if (strConfirmedType != null && !"".equals(strConfirmedType)) {
			this.setConfirmedType(strConfirmedType);
			String strConfirmedDetails = tran.getString("confirmedDetails");
			strConfirmedDetails = parameterConfig.getParameterInfo(ParameterConfig.CONFIRMED_DETAILS).getValue(strConfirmedDetails);
			if (strConfirmedDetails != null && !"".equals(strConfirmedDetails)) {
				this.setConfirmedDetails(Integer.parseInt(strConfirmedDetails));
			}
		}
		String strAppliedOn = tran.getString("appliedOn");
		this.setAppliedOn(UtilMethod.getDefaultProducedOn(DateUtils.getDateFromIDate(strAppliedOn)));
		String strConfirmedDate = tran.getString("confirmedDate");
		this.setConfirmedDate(UtilMethod.getDefaultProducedOn(DateUtils.getDateFromIDate(strConfirmedDate)));
		this.setComments(tran.getString("comments"));
		this.setContactName1(tran.getString("contactName1"));
		this.setContactName2(tran.getString("contactName2"));
		this.setContactName3(tran.getString("contactName3"));
		this.setContactWork1(tran.getString("contactWork1"));
		this.setContactWork2(tran.getString("contactWork2"));
		this.setContactWork3(tran.getString("contactWork3"));
		this.setContactTell1(tran.getString("contactTell1"));
		this.setContactTell2(tran.getString("contactTell2"));
		this.setContactTell3(tran.getString("contactTell3"));
		String relationship = tran.getString("contactRelationship1");
		relationship = parameterConfig.getParameterInfo(ParameterConfig.CONTACTRELATIONSHIP).getKey(relationship);
		if (relationship != null && !"".equals(relationship)) {
			this.setContactRelationship1(Integer.parseInt(relationship));
		}
		relationship = tran.getString("contactRelationship2");
		relationship = parameterConfig.getParameterInfo(ParameterConfig.CONTACTRELATIONSHIP).getKey(relationship);
		if (relationship != null && !"".equals(relationship)) {
			this.setContactRelationship2(Integer.parseInt(relationship));
		}
		relationship = tran.getString("contactRelationship3");
		relationship = parameterConfig.getParameterInfo(ParameterConfig.CONTACTRELATIONSHIP).getKey(relationship);
		if (relationship != null && !"".equals(relationship)) {
			this.setContactRelationship3(Integer.parseInt(relationship));
		}
		this.standard();
	}
	
	public void standard(){
		//标准化机构名称
		this.setStandardWorkName(CorpFuzzyTool.prepareCorpName(this.getWorkName()));
		//标准化机构地址
		this.setStandardWorkAddress(AddressFuzzyTool.formatAddr(this.getWorkAddress()));
		//标准化家庭地址
		this.setStandardHomeAddress(AddressFuzzyTool.formatAddr(this.getHomeAddress()));
	}

}
