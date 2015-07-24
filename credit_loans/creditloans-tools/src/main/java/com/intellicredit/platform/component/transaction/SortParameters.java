package com.intellicredit.platform.component.transaction;

import java.util.Arrays;

/**
 * 排序的字段，以及每一个字段排序的类型
 * 
 * @author Hero.wu
 * 
 */
public class SortParameters {
	private String[] m_names = null;// 排序的数据项
	private int[] m_sortTypes = null;// 每一个数据项升序还是降序

	public SortParameters(String[] names, int[] sortTypes) {
		if ((names != null) && (names.length > 0)) {
			this.m_names = names;
			int len1 = names.length;
			this.m_sortTypes = new int[len1];
			if ((sortTypes == null) || (sortTypes.length == 0)) {
				Arrays.fill(this.m_sortTypes, TranConstants.ASCEND);
			} else {
				int len2 = sortTypes.length;
				int i;
				if (len1 > len2) {
					for (i = 0; i < len2; i++) {
						if (sortTypes[i] == TranConstants.DESCEND)
							this.m_sortTypes[i] = sortTypes[i];
						else {
							this.m_sortTypes[i] = TranConstants.ASCEND;
						}
					}
					for (i = len2; i < len1; i++)
						this.m_sortTypes[i] = TranConstants.ASCEND;
				} else {
					for (i = 0; i < len1; i++)
						if (sortTypes[i] == TranConstants.DESCEND)
							this.m_sortTypes[i] = sortTypes[i];
						else
							this.m_sortTypes[i] = TranConstants.ASCEND;
				}
			}
		}
	}

	public SortParameters(String[] names) {
		if ((names != null) && (names.length > 0)) {
			this.m_names = names;
			this.m_sortTypes = new int[names.length];
			Arrays.fill(this.m_sortTypes, TranConstants.ASCEND);
		}
	}

	public String[] getNames() {
		return this.m_names;
	}

	public int[] getSortTypes() {
		return this.m_sortTypes;
	}

}
