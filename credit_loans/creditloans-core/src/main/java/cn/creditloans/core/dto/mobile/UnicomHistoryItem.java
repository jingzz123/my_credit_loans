package cn.creditloans.core.dto.mobile;

import java.io.Serializable;

/**
 * 费用明细
 * 
 * @author Administrator
 * 
 */
public class UnicomHistoryItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 费用名称 */
	private String name;
	/** 费用 */
	private String fee;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null && !"".equals(name)) {
			name = name.replaceAll("-", "");
		}
		this.name = name;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

}
