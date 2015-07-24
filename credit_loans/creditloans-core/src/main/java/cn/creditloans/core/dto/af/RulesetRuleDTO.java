package cn.creditloans.core.dto.af;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * af_ruleset_rule
 * 
 * @author Ash
 * 
 */
public class RulesetRuleDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1997733019902447337L;
	private int id;
	private int rulesetId;
	private String name; // 名称
	private String description; // 描述
	private String preconditions; // 所有的前提条件
	private String conditions; // 所有的条件
	private String actions; // 所有的动作
	private int sequence; // 次序
	private String isUsed = "Y";//是否有用
	private int createUserId; // 创建用户
	private Date createTime; // 创建时间
	private int upadteUserId; // 最后修改用户
	private Date updateTime; // 最后修改时间
	private String oldName;
	private int oldSequence;
	private List<String> conditionList;
	private List<String> actionNameList;
	
	public List<String> getActionNameList() {
		return actionNameList;
	}

	public void setActionNameList(List<String> actionNameList) {
		this.actionNameList = actionNameList;
	}

	public List<String> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<String> conditionList) {
		this.conditionList = conditionList;
	}

	public String getOldName() {
		return oldName;
	}

	public void setOldName(String oldName) {
		this.oldName = oldName;
	}

	public int getOldSequence() {
		return oldSequence;
	}

	public void setOldSequence(int oldSequence) {
		this.oldSequence = oldSequence;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRulesetId() {
		return rulesetId;
	}

	public void setRulesetId(int rulesetId) {
		this.rulesetId = rulesetId;
	}

	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}

	public String getPreconditions() {
		return preconditions;
	}

	public void setPreconditions(String preconditions) {
		this.preconditions = preconditions;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getUpadteUserId() {
		return upadteUserId;
	}

	public void setUpadteUserId(int upadteUserId) {
		this.upadteUserId = upadteUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
