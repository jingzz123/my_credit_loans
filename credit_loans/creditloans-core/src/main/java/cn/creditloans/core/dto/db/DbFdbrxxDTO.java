package cn.creditloans.core.dto.db;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DbFdbrxxDTO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 4276563299162246813L;

	/**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.id
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private int id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.fdbrlx
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private String fdbrlx;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.fdbrmc
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private String fdbrmc;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.fdbrzjlx
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private String fdbrzjlx;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.fdbrzjhm
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private String fdbrzjhm;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.fdbzrje
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private BigDecimal fdbzrje;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.ztw
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private String ztw;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.dbhtxx_id
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private Integer dbhtxxId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.status
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private Integer status;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.user_id
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private Integer userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.org_code
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private String orgCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.create_time
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column db_fdbrxx.update_time
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    private Date updateTime;

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	/**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column db_fdbrxx.fdbrlx
     *
     * @return the value of db_fdbrxx.fdbrlx
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public String getFdbrlx() {
        return fdbrlx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column db_fdbrxx.fdbrlx
     *
     * @param fdbrlx the value for db_fdbrxx.fdbrlx
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public void setFdbrlx(String fdbrlx) {
        this.fdbrlx = fdbrlx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column db_fdbrxx.fdbrmc
     *
     * @return the value of db_fdbrxx.fdbrmc
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public String getFdbrmc() {
        return fdbrmc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column db_fdbrxx.fdbrmc
     *
     * @param fdbrmc the value for db_fdbrxx.fdbrmc
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public void setFdbrmc(String fdbrmc) {
        this.fdbrmc = fdbrmc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column db_fdbrxx.fdbrzjlx
     *
     * @return the value of db_fdbrxx.fdbrzjlx
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public String getFdbrzjlx() {
        return fdbrzjlx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column db_fdbrxx.fdbrzjlx
     *
     * @param fdbrzjlx the value for db_fdbrxx.fdbrzjlx
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public void setFdbrzjlx(String fdbrzjlx) {
        this.fdbrzjlx = fdbrzjlx;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column db_fdbrxx.fdbrzjhm
     *
     * @return the value of db_fdbrxx.fdbrzjhm
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public String getFdbrzjhm() {
        return fdbrzjhm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column db_fdbrxx.fdbrzjhm
     *
     * @param fdbrzjhm the value for db_fdbrxx.fdbrzjhm
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public void setFdbrzjhm(String fdbrzjhm) {
        this.fdbrzjhm = fdbrzjhm;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column db_fdbrxx.fdbzrje
     *
     * @return the value of db_fdbrxx.fdbzrje
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public BigDecimal getFdbzrje() {
        return fdbzrje;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column db_fdbrxx.fdbzrje
     *
     * @param fdbzrje the value for db_fdbrxx.fdbzrje
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public void setFdbzrje(BigDecimal fdbzrje) {
        this.fdbzrje = fdbzrje;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column db_fdbrxx.ztw
     *
     * @return the value of db_fdbrxx.ztw
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public String getZtw() {
        return ztw;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column db_fdbrxx.ztw
     *
     * @param ztw the value for db_fdbrxx.ztw
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public void setZtw(String ztw) {
        this.ztw = ztw;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column db_fdbrxx.dbhtxx_id
     *
     * @return the value of db_fdbrxx.dbhtxx_id
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public Integer getDbhtxxId() {
        return dbhtxxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column db_fdbrxx.dbhtxx_id
     *
     * @param dbhtxxId the value for db_fdbrxx.dbhtxx_id
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public void setDbhtxxId(Integer dbhtxxId) {
        this.dbhtxxId = dbhtxxId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column db_fdbrxx.status
     *
     * @return the value of db_fdbrxx.status
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column db_fdbrxx.status
     *
     * @param status the value for db_fdbrxx.status
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column db_fdbrxx.user_id
     *
     * @return the value of db_fdbrxx.user_id
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column db_fdbrxx.user_id
     *
     * @param userId the value for db_fdbrxx.user_id
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column db_fdbrxx.org_code
     *
     * @return the value of db_fdbrxx.org_code
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public String getOrgCode() {
        return orgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column db_fdbrxx.org_code
     *
     * @param orgCode the value for db_fdbrxx.org_code
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column db_fdbrxx.create_time
     *
     * @return the value of db_fdbrxx.create_time
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column db_fdbrxx.create_time
     *
     * @param createTime the value for db_fdbrxx.create_time
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column db_fdbrxx.update_time
     *
     * @return the value of db_fdbrxx.update_time
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column db_fdbrxx.update_time
     *
     * @param updateTime the value for db_fdbrxx.update_time
     *
     * @mbggenerated Wed May 27 10:21:44 CST 2015
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}