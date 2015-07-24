package cn.creditloans.tools.fuzzy.address;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import cn.creditloans.tools.fuzzy.util.FuzzyAddressDataCache;
import cn.creditloans.tools.fuzzy.util.FuzzyConstants;
import cn.creditloans.tools.fuzzy.util.FuzzyMemoryAddressData;
import cn.creditloans.tools.fuzzy.util.FuzzyUtil;
import cn.creditloans.tools.fuzzy.util.IndexValue;
import cn.creditloans.tools.fuzzy.util.PartitionInfo;

/**
 * 地址模糊匹配的应用类 确保新增索引、修改索引、删除的 
 * TODO：初始化索引单独利用
 * 需要测试一下删除功能
 * @author Ash
 * 
 */
public abstract class AddressFuzzyAbstractApp {
	
	private final static Log logger = LogFactory.getLog(AddressFuzzyAbstractApp.class);
	
	private String cacheKey;

	protected AddressFuzzyAbstractApp(){
		this.setPartitionInfo();
	}
	
	/**
	 * 从中可以得到配置文件的信息
	 */
	protected PartitionInfo partitionInfo;

	/**
	 * 这里存放所有无法分区的地址信息
	 * key1：表示地址名称
	 * value：所有的【业务代码 +'|'+id】的列表
	 */
	private Map<String,List<String>> otherValueMap = new HashMap<String,List<String>>();
	
	/**
	 * key1是索引目录
	 * key2是地址
	 * value：所有的【业务代码 +'|'+id】的列表
	 */
	private Map<String,Map<String,List<String>>> partitionValueMap = new HashMap<String,Map<String,List<String>>>();
	
	public abstract void setPartitionInfo();

	/**
	 * 
	 * @param type
	 * @param code
	 * @param address：没有分区域
	 * @param cacheKey：缓存的key
	 */
	public synchronized void index(int type, String code, List<IndexValue> addresses, String cacheKey){
		if(addresses==null||addresses.size()==0){
			return;
		}
		this.cacheKey = cacheKey;
		FuzzyMemoryAddressData fuzzyMemoryAddressData = FuzzyAddressDataCache.getInstance().get(cacheKey);
		if(fuzzyMemoryAddressData != null) {
			otherValueMap = fuzzyMemoryAddressData.getOtherValueMap();
			partitionValueMap = fuzzyMemoryAddressData.getPartitionValueMap();
		}
		if(partitionInfo.isPartition()){//如果地址分区，主要针对家庭地址和单位地址
			List<String> keyList = partitionInfo.getKeyList();
			Map<String,String> partitionDirectoryMap = partitionInfo.getPartitionDirectoryMap();
			int size = keyList.size();
			//key是目录，value是地址信息
			Map<String,List<IndexValue>> classifyMap = new HashMap<String,List<IndexValue>>();
			List<IndexValue> others = new LinkedList<IndexValue>();//存放所有不能分区的
			for(IndexValue iv: addresses){
				String address = iv.getValue();
				//是否在分区中找到，如果没有会放到others中
				boolean finded = false;
				for(int i=0; i<size; i++){
					finded = false;
					String key = keyList.get(i);
					if(address.startsWith(key)){
						//分区只是存放的最后一层的目录名称
						String directory = this.partitionInfo.getPartitionDirectory(partitionDirectoryMap.get(key));
						if(classifyMap.containsKey(directory)){
							classifyMap.get(directory).add(iv);
						}else{
							List<IndexValue> vList = new LinkedList<IndexValue>();
							vList.add(iv);
							classifyMap.put(directory, vList);
						}
						finded = true;
						break;
					}
				}
				if(!finded){//如果没有从分区中找到
					others.add(iv);
					//logger.debug("other partition is "+iv.getValue());
				}
			}
			addresses = null;
			//调用分别进行索引处理
			Iterator<Entry<String,List<IndexValue>>> keyValues = classifyMap.entrySet().iterator();
			while(keyValues.hasNext()){
				Entry<String,List<IndexValue>> kv = keyValues.next();
				String directory = kv.getKey();
				List<IndexValue> list = kv.getValue();
				index(type, code, list, directory, true);
			}
			if(others.size()>0){
				index(type, code, others, partitionInfo.getOtherDirectory(), false);
			}
		}else{//主要正对商户地址，它不做分区
			index(type, code, addresses, partitionInfo.getParentDirectory(), false);
		}
	}

	/**
	 * 删除索引目录下的所有索引文件，此功能必须在索引初始化之前处理
	 * 使用者需要根据实际情况来进行调用
	 */
	public synchronized void cleanIndexFiles(){
		try{
			otherValueMap = new HashMap<String,List<String>>();
			partitionValueMap = new HashMap<String,Map<String,List<String>>>();
			//clean只是删除目录中内容，不会删除目录本身
			boolean isPartition = partitionInfo.isPartition();
			if(isPartition){
				String[] partitions = partitionInfo.getPartitionDirectoryMap().values().toArray(new String[0]);
				int len = partitions.length;
				for(int i=0; i<len; i++){
					FileUtils.cleanDirectory(new File(partitionInfo.getParentDirectory(),partitions[i]));
				}
				FileUtils.cleanDirectory(new File(partitionInfo.getOtherDirectory()));
			}else{//没有分区，所有的就放到了父目录中
				FileUtils.cleanDirectory(new File(partitionInfo.getParentDirectory()));
			}
		}catch(Exception e){
			logger.error(e.getStackTrace());
		}
	}
	
	private void deleteAll(String indexPath){
		IndexWriter writer = null;
		try {
			writer = FuzzyUtil.getWriter(indexPath,FuzzyConstants.THREAD_NUM, FuzzyConstants.FUZZY_TYPE_ADDRESS);
			writer.deleteAll();
			writer.close();
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
	}
	/**
	 * 修改索引
	 * 
	 * @param type
	 *            type = 1：表示新增 type = 2：表示删除
	 * @param code：业务类型代码
	 * @param addresses
	 *            ：表示传入的地址信息，这里要求传入的是同一个分区的信息
	 */
	public synchronized void index(int type, String code, List<IndexValue> addresses, String indexPath, boolean isPartition) {
		if(addresses==null||addresses.size()==0){
			return;
		}
		if (type == FuzzyConstants.INDEX_ADD) {// 索引新增
			add(code, addresses, indexPath, isPartition);
		} else if (type == FuzzyConstants.INDEX_DELETE) {// 索引删除
			delete(code, addresses, indexPath, isPartition);
		}
	}

	/**
	 * 新增索引
	 * 在名称没有加入到缓存之前jin'xin
	 * @param needDelete
	 */
	private void add(String code, List<IndexValue> values, String indexPath, boolean isPartition) {
		IndexWriter writer = null;
		try {
			// 存放加入到index中的信息
			writer = FuzzyUtil.getWriter(indexPath,
					FuzzyConstants.THREAD_NUM, FuzzyConstants.FUZZY_TYPE_ADDRESS);
			FieldType fdType = new FieldType(TextField.TYPE_STORED);
			FieldType IdfdType = new FieldType(StringField.TYPE_STORED);
			fdType.setOmitNorms(true);
			fdType.setIndexOptions(IndexOptions.DOCS_ONLY);
			for (IndexValue single : values) {
				String address = single.getValue();
				if(address==null||address.trim().equals("")){
					continue;
				}
				int id = single.getId();
				String value = code+"|"+id;
				//内存处理
				if(!isPartition){//如果地址没有分区，针对Url Address
					if(otherValueMap.containsKey(address)){//如果缓存中存在，表示lucene中也存在
						//将数据加入到内存中
						if(!otherValueMap.get(address).contains(value)){
							otherValueMap.get(address).add(value);
						}
					}else{
						//将数据加入到内存中
						List<String> vList = new ArrayList<String>();
						//为数据创建唯一ID
						String uuid = FuzzyUtil.getUUID();
						vList.add(uuid);
						vList.add(value);
						otherValueMap.put(address, vList);
						// 需要增加index
						Document doc = new Document();
						Field idField = new Field("id", uuid,IdfdType);
						Field field = new Field("content", address, fdType);
						doc.add(idField);
						doc.add(field);
						// 对所有的进行索引
						writer.addDocument(doc);
						//logger.debug("  add address："+address);
					}
				}else{//如果地址可以分区
					if(!partitionValueMap.containsKey(indexPath)){//不存在就新增一个
						partitionValueMap.put(indexPath, new HashMap<String,List<String>>());
					}
					if(partitionValueMap.get(indexPath).containsKey(address)){//如果缓存中存在，表示lucene中也存在
						//将数据加入到内存中
						if(!partitionValueMap.get(indexPath).get(address).contains(value)){
							partitionValueMap.get(indexPath).get(address).add(value);
						}
					}else{
						//将数据加入到内存中
						List<String> vList = new ArrayList<String>();
						String uuid = FuzzyUtil.getUUID();
						vList.add(uuid);
						vList.add(value);
						partitionValueMap.get(indexPath).put(address, vList);
						// 需要增加index
						Document doc = new Document();
						Field idField = new Field("id", uuid,IdfdType);
						Field field = new Field("content", address, fdType);
						doc.add(idField);
						doc.add(field);
						// 对所有的进行索引
						writer.addDocument(doc);
						//logger.debug("  add address："+address);
					}
				}
			}
			updateDataCache(cacheKey, partitionValueMap, otherValueMap);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(writer!=null){
				try{
					writer.commit();
					writer.close();
				}catch(Exception e){
					
				}
			}
		}
	}

	/**
	 * 删除索引 只有内存中完全没有此的时候才需要从index中进行删除
	 * 
	 * @param code
	 * @param values
	 */
	private void delete(String code, List<IndexValue> values, String indexPath, boolean isPartition) {
		IndexWriter writer = null;
		try {
			writer = FuzzyUtil.getWriter(indexPath,
					FuzzyConstants.THREAD_NUM, FuzzyConstants.FUZZY_TYPE_ADDRESS);
			for (IndexValue single : values) {
				String address = single.getValue();
				if(address==null||address.trim().equals("")){
					continue;
				}
				int id = single.getId();
				String value = code+"|"+id;
				if(!isPartition){
					//首先从内存中删除
					String uuid = null;
					if(otherValueMap.containsKey(address)){
						if(otherValueMap.get(address).contains(value)){
							otherValueMap.get(address).remove(value);
							if(otherValueMap.get(address).size()==1){
								uuid = otherValueMap.get(address).get(0);
								otherValueMap.remove(address);
							}
						}
					}
					if ((!otherValueMap.containsKey(address)) && uuid!=null) {// 如果需要删除
						//通过uuid删除
						writer.deleteDocuments(new Term("id", uuid));
					}
				}else{
					String uuid = null;
					if(partitionValueMap.containsKey(indexPath)){
						if(partitionValueMap.get(indexPath).containsKey(address)){
							if(partitionValueMap.get(indexPath).get(address).contains(value)){
								partitionValueMap.get(indexPath).get(address).remove(value);
								if(partitionValueMap.get(indexPath).get(address).size()==1){
									uuid = partitionValueMap.get(indexPath).get(address).get(0);
									partitionValueMap.get(indexPath).remove(address);
								}
							}
						}
						if ((!partitionValueMap.get(indexPath).containsKey(address)) && uuid!=null) {// 如果需要删除
							
							writer.deleteDocuments(new Term("id", uuid));
						}
					}
				}
			}
			updateDataCache(cacheKey, partitionValueMap, otherValueMap);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(writer!=null){
				try{
					writer.commit();
					writer.close();
				}catch(Exception e){
					
				}
			}
		}
	}
	
	/**
	 * 更新缓存
	 * @param key
	 */
	private void updateDataCache(String key, Map<String,Map<String,List<String>>> partitionValueMap, Map<String,List<String>> otherValueMap) {
		FuzzyAddressDataCache.getInstance().delete(key);
		FuzzyMemoryAddressData fuzzyMemoryAddressData = new FuzzyMemoryAddressData();
		fuzzyMemoryAddressData.setOtherValueMap(otherValueMap);
		fuzzyMemoryAddressData.setPartitionValueMap(partitionValueMap);
		FuzzyAddressDataCache.getInstance().set(key, fuzzyMemoryAddressData);
	}

	/**
	 * 查询单个
	 * @param value
	 * @return
	 */
	public List<String> search(String address) {
		if(address==null||address.trim().equals("")){
			return null;
		}
		IndexWriter writer = null;
		try {
			String directory = null;
			if(partitionInfo.isPartition()){
				List<String> keyList = partitionInfo.getKeyList();
				Map<String,String> partitionDirectoryMap = partitionInfo.getPartitionDirectoryMap();
				int size = keyList.size();
				boolean finded = false;
				for(int i=0; i<size; i++){
					finded = false;
					String key = keyList.get(i);
					if(address.startsWith(key)){
						//分区只是存放的最后一层的目录名称
						directory = this.partitionInfo.getPartitionDirectory(partitionDirectoryMap.get(key));
						finded = true;
						break;
					}
				}
				if(!finded){
					directory = partitionInfo.getOtherDirectory();
				}
			}else{
				directory = partitionInfo.getParentDirectory();
			}
			writer = FuzzyUtil.getWriter(directory,
					FuzzyConstants.THREAD_NUM, FuzzyConstants.FUZZY_TYPE_ADDRESS);
			List<String> vs = AddressFuzzyTool.search(address, writer);
			return vs;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			if(writer!=null){
				try{
					writer.close();
				}catch(Exception e){
					
				}
			}
		}
	}

	/**
	 * 从索引中查询模糊查询多个
	 * 
	 * @param values
	 *            ：传入的待检查的多个公司名称列表
	 * @return
	 * @throws Exception
	 */
	public Map<String, List<String>> search(String[] addresses) {
		try {
			Map<String, List<String>> map = new HashMap<String,List<String>>();
			if(partitionInfo.isPartition()){
				List<String> keyList = partitionInfo.getKeyList();
				Map<String,String> partitionDirectoryMap = partitionInfo.getPartitionDirectoryMap();
				int size = keyList.size();
				//key是目录，value是地址信息
				Map<String,List<String>> classifyMap = new HashMap<String,List<String>>();
				List<String> others = new LinkedList<String>();//存放所有不能分区的
				for(String address: addresses){
					if(address==null||address.trim().equals("")){
						continue;
					}
					//是否在分区中找到，如果没有会放到others中
					boolean finded = false;
					for(int i=0; i<size; i++){
						finded = false;
						String key = keyList.get(i);
						if(address.startsWith(key)){
							String directory = this.partitionInfo.getPartitionDirectory(partitionDirectoryMap.get(key));
							if(classifyMap.containsKey(directory)){
								classifyMap.get(directory).add(address);
							}else{
								List<String> vList = new LinkedList<String>();
								vList.add(address);
								classifyMap.put(directory, vList);
							}
							finded = true;
							break;
						}
						if(!finded){//如果没有从分区中找到
							others.add(address);
						}
					}
				}
				addresses = null;
				//调用分别进行索引处理
				Iterator<Entry<String,List<String>>> keyValues = classifyMap.entrySet().iterator();
				while(keyValues.hasNext()){
					Entry<String,List<String>> kv = keyValues.next();
					String directory = kv.getKey();
					List<String> list = kv.getValue();
					IndexWriter writer = FuzzyUtil.getWriter(directory,
							FuzzyConstants.THREAD_NUM, FuzzyConstants.FUZZY_TYPE_ADDRESS);
					map.putAll(AddressFuzzyTool
							.search(list.toArray(new String[0]), writer));
					writer.close();
				}
				if(others.size()>0){
					IndexWriter writer = FuzzyUtil.getWriter(partitionInfo.getOtherDirectory(),
							FuzzyConstants.THREAD_NUM, FuzzyConstants.FUZZY_TYPE_ADDRESS);
					map.putAll(AddressFuzzyTool
							.search(others.toArray(new String[0]), writer));
					writer.close();
				}
			}else{
				IndexWriter writer = FuzzyUtil.getWriter(partitionInfo.getParentDirectory(),
						FuzzyConstants.THREAD_NUM, FuzzyConstants.FUZZY_TYPE_ADDRESS);
				map.putAll(AddressFuzzyTool
						.search(addresses, writer));
				writer.close();
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
