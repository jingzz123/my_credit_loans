package cn.creditloans.core.dto.mobile;

import java.io.Serializable;
import java.util.List;

/**
 * 消费信息
 * @author Administrator
 *
 */
public class UnicomHistoryColumnList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 费用名称 */
	private String name;
	/** 费用名称 */
	private String fee;
	/** 费用明细 */
	private List<UnicomHistoryItem> itemList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public List<UnicomHistoryItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<UnicomHistoryItem> itemList) {
		this.itemList = itemList;
	}

}
