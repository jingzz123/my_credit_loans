package cn.creditloans.core.dto.platform;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MatchItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 改字段表示
	 * 0: 10天内
	 * 1: 30天内
	 * 2: 90天内
	 * 3: 365天内
	 * 4: 所有
	 */
	private int type;
	
	/**
	 * 匹配数
	 */
	private int matchCount;
	
	/**
	 * 涉及银行数
	 */
	private int referBankCount;
	
	/**
	 * 涉及的记录的id集合
	 */
	private Set<Integer> itemIdList = new HashSet<Integer>();
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}

	public int getReferBankCount() {
		return referBankCount;
	}

	public void setReferBankCount(int referBankCount) {
		this.referBankCount = referBankCount;
	}
	
	public Set<Integer> getItemIdList() {
		return itemIdList;
	}

	public void setItemIdList(Set<Integer> itemIdList) {
		this.itemIdList = itemIdList;
	}

}
