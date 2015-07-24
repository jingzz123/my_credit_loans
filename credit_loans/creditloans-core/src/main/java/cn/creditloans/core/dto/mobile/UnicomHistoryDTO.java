package cn.creditloans.core.dto.mobile;

import java.io.Serializable;
import java.util.List;

/**
 * 联通历史信息
 * 
 * @author Administrator
 * 
 */
public class UnicomHistoryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String monthKey;
	/** 客户姓名 */
	private String customName;
	/** 本月消费 */
	private String discountFee;
	/** 手机号码 */
	private String userNumber;
	/** 计费周期 */
	private String month;
	/** 实际应缴合计 */
	private String payTotal;
	/** 抵扣合计 */
	private String sumFee;
	/** 本月新增积分 */
	private String preMonthInterral;
	/** 可用积分余额 */
	private String useIntegral;
	/** 已兑换积分 */
	private String usedIntegral;
	/** 消费信息 */
	private List<UnicomHistoryColumnList> list;

	public String getMonthKey() {
		return monthKey;
	}

	public void setMonthKey(String monthKey) {
		this.monthKey = monthKey;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public String getDiscountFee() {
		return discountFee;
	}

	public void setDiscountFee(String discountFee) {
		this.discountFee = discountFee;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getPayTotal() {
		return payTotal;
	}

	public void setPayTotal(String payTotal) {
		this.payTotal = payTotal;
	}

	public String getSumFee() {
		return sumFee;
	}

	public void setSumFee(String sumFee) {
		this.sumFee = sumFee;
	}

	public String getPreMonthInterral() {
		return preMonthInterral;
	}

	public void setPreMonthInterral(String preMonthInterral) {
		this.preMonthInterral = preMonthInterral;
	}

	public String getUseIntegral() {
		return useIntegral;
	}

	public void setUseIntegral(String useIntegral) {
		this.useIntegral = useIntegral;
	}

	public String getUsedIntegral() {
		return usedIntegral;
	}

	public void setUsedIntegral(String usedIntegral) {
		this.usedIntegral = usedIntegral;
	}

	public List<UnicomHistoryColumnList> getList() {
		return list;
	}

	public void setList(List<UnicomHistoryColumnList> list) {
		this.list = list;
	}

}
