package cn.creditloans.core.entity.af;

import java.util.Date;

/**
 * af_ruleset_precondition
 * 
 * @author Ash
 * 
 */
public class RulesetPreCondition {
	private int id;
	private int rulesetId;
	private String name; // 名称
	private String description; // 描述
	private String conditions; // 所有的条件
	private int sequence; // 次序
	private int createUserId; // 创建用户
	private Date createTime; // 创建时间
	private int upadteUserId; // 最后修改用户
	private Date updateTime; // 最后修改时间

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
