package cn.creditloans.core.dto.enterprise;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.creditloans.core.dto.platform.MatchItem;

/**
 * 查询比对结果 DTO
 * @author Administrator
 *
 */
public class QueryCompareResultItem implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 黑名单的类型名称
	 */
	private String blackTypeName;
	
	/**
	 * 某个黑名单类型表中的字段
	 */
	private String field;
	
	private List<MatchItem> matchItemList = new ArrayList<MatchItem>();

	public String getBlackTypeName() {
		return blackTypeName;
	}

	public void setBlackTypeName(String blackTypeName) {
		this.blackTypeName = blackTypeName;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public List<MatchItem> getMatchItemList() {
		return matchItemList;
	}

	public void setMatchItemList(List<MatchItem> matchItemList) {
		this.matchItemList = matchItemList;
	}

}
