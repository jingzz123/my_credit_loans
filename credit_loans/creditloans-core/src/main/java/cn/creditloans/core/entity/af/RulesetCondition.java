package cn.creditloans.core.entity.af;

import java.util.Date;

/**
 * af_ruleset_condition
 * 
 * @author Ash
 * 
 */
public class RulesetCondition {
	private int id;
	private int rulesetId;
	private String name; // 名称
	private String description; // 描述
	private String lhs; // 左侧表达式
	private int operator; // 操作符
	private String rhs; // 右侧表达式
	private int type; // 两边数据类型
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

	public String getLhs() {
		return lhs;
	}

	public void setLhs(String lhs) {
		this.lhs = lhs;
	}

	public int getOperator() {
		return operator;
	}

	public void setOperator(int operator) {
		this.operator = operator;
	}

	public String getRhs() {
		return rhs;
	}

	public void setRhs(String rhs) {
		this.rhs = rhs;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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
