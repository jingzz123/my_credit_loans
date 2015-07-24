package cn.creditloans.tools.fuzzy.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 分区信息
 * @author Ash
 *
 */
public class PartitionInfo {
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 是否分区
	 */
	private boolean  isPartition;
	
	/**
	 * lucene所在的父目录
	 */
	private String parentDirectory;
	
	/**
	 * 分区对应的子目录(lucene)
	 * key是分区名字，例如北京市
	 * value是最后一层目录的名字，完整的目录需要前面加入parentDirectory
	 */
	private Map<String,String> partitionDirectoryMap = null;
	
	private List<String> keyList = null;
	
	/**
	 * 无法分区所存放的目录
	 */
	private String otherDirectory = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPartition() {
		return isPartition;
	}

	public void setPartition(boolean isPartition) {
		this.isPartition = isPartition;
	}

	public String getParentDirectory() {
		return parentDirectory;
	}

	public void setParentDirectory(String parentDirectory) {
		this.parentDirectory = parentDirectory;
	}

	public Map<String, String> getPartitionDirectoryMap() {
		return partitionDirectoryMap;
	}

	public void setPartitionDirectoryMap(Map<String, String> partitionDirectoryMap) {
		this.partitionDirectoryMap = partitionDirectoryMap;
		keyList = new ArrayList<String>();
		Iterator<String> keyIter = this.partitionDirectoryMap.keySet().iterator();
		while(keyIter.hasNext()){
			keyList.add(keyIter.next());
		}
	}

	public List<String> getKeyList() {
		return keyList;
	}

	public void setKeyList(List<String> keyList) {
		this.keyList = keyList;
	}

	public String getOtherDirectory() {
		return otherDirectory;
	}

	public void setOtherDirectory(String otherDirectory) {
		this.otherDirectory = otherDirectory;
	}
	
	public String getPartitionDirectory(String name){
		return new File(this.parentDirectory,name).getAbsolutePath();
	}
	
}
