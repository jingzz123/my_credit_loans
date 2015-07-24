package cn.creditloans.tools.fuzzy.corp;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;

import cn.creditloans.tools.context.AppContext;
import cn.creditloans.tools.fuzzy.util.FuzzyConstants;
import cn.creditloans.tools.fuzzy.util.FuzzyCorpDataCache;
import cn.creditloans.tools.fuzzy.util.FuzzyMemoryCorpData;
import cn.creditloans.tools.fuzzy.util.FuzzyUtil;
import cn.creditloans.tools.fuzzy.util.IndexValue;

/**
 * 公司模糊匹配的应用类 确保新增索引、修改索引、删除的 
 * TODO：初始化索引单独利用 需要测试一下删除功能
 * 
 * @author Ash
 * 
 */
public class CorpFuzzyApp {
	
	private final static Log logger = LogFactory.getLog(CorpFuzzyApp.class);

	private static CorpFuzzyApp instance = new CorpFuzzyApp();

	/**
	 * 公司只是存放一个索引目录
	 */
	private String corpIndexPath = null;

	/**
	 * key1：表示公司名称 value：所有的【业务代码 +'|'+id】的列表
	 */
	private Map<String, List<String>> valueMap = new HashMap<String, List<String>>();

	private CorpFuzzyApp() {
		corpIndexPath = AppContext.getInstance().getFuzzyConfig()
				.getPartitionInfo(FuzzyConstants.FUZZY_NAME_WORK_NAME)
				.getParentDirectory();
	}

	public static CorpFuzzyApp getInstance() {
		return instance;
	}

	/**
	 * 修改索引
	 * 
	 * @param type
	 *            type = 1：表示新增 type = 2：表示删除
	 * @param code
	 *            ：业务类型代码
	 * @param names
	 *            ：表示传入的公司名称数据
	 */
	public synchronized void index(int type, String code, List<IndexValue> names, String key) {
		if(names==null||names.size()==0){
			return;
		}
		FuzzyMemoryCorpData fuzzyMemoryCorpData = FuzzyCorpDataCache.getInstance().get(key);
		if(fuzzyMemoryCorpData != null) {
			valueMap = fuzzyMemoryCorpData.getValueMap();
		}
		if (type == FuzzyConstants.INDEX_ADD) {// 索引新增
			add(code, names, key);
		} else if (type == FuzzyConstants.INDEX_DELETE) {// 索引删除
			delete(code, names, key);
		}
	}
	
	/**
	 * 删除索引目录下的所有索引文件，此功能必须在索引初始化之前处理
	 * 使用者需要根据实际情况来进行调用
	 */
	public synchronized void cleanIndexFiles(){
		try{
			valueMap = new HashMap<String, List<String>>();
			//clean只是删除目录中内容，不会删除目录本身
			FileUtils.cleanDirectory(new File(corpIndexPath));
		}catch(Exception e){
			logger.error(e.getStackTrace());
		}
	}
	
	private void deleteAll(String indexPath){
		IndexWriter writer = null;
		try {
			writer = FuzzyUtil.getWriter(indexPath,FuzzyConstants.THREAD_NUM, FuzzyConstants.FUZZY_TYPE_CORP);
			
			writer.deleteAll();
			writer.forceMergeDeletes();
			writer.close();
		} catch (Exception e) {
			logger.error(e.getStackTrace());
		}
	}
	
	/**
	 * 新增索引 在名称没有加入到缓存之前jin'xin
	 * 
	 * @param needDelete
	 */
	private void add(String code, List<IndexValue> values, String key) {
		IndexWriter writer = null;
		try {
			// 存放加入到index中的信息
			writer = FuzzyUtil.getWriter(corpIndexPath,
					FuzzyConstants.THREAD_NUM, FuzzyConstants.FUZZY_TYPE_CORP);
			FieldType fdType = new FieldType(TextField.TYPE_STORED);
			fdType.setOmitNorms(true);
			fdType.setIndexOptions(IndexOptions.DOCS_ONLY);
			for (IndexValue single : values) {
				String name = single.getValue();
				if(name==null||name.trim().equals("")){
					continue;
				}
				int id = single.getId();
				String value = code + "|" + id;
				if (valueMap.containsKey(name)) {// 如果缓存中存在，表示lucene中也存在
					// 将数据加入到内存中
					if (!valueMap.get(name).contains(value)) {
						valueMap.get(name).add(value);
					}
				} else {
					// 将数据加入到内存中
					List<String> vList = new ArrayList<String>();
					vList.add(value);
					valueMap.put(name, vList);
					// 需要增加index
					Document doc = new Document();
					Field field = new Field("content", name, fdType);
					doc.add(field);
					// 对所有的进行索引
					writer.addDocument(doc);
					//logger.debug("  add name："+name);
				}
			}
			// 删除后更新索引
			updateDataCache(key, valueMap);
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
	private void delete(String code, List<IndexValue> values, String key) {
		IndexWriter writer = null;
		try {
			writer = FuzzyUtil.getWriter(corpIndexPath,
					FuzzyConstants.THREAD_NUM, FuzzyConstants.FUZZY_TYPE_CORP);

			for (IndexValue single : values) {
				String name = single.getValue();
				if(name==null||name.trim().equals("")){
					continue;
				}
				int id = single.getId();
				String value = code + "|" + id;
				// 首先从内存中删除
				if (valueMap.containsKey(name)) {
					if (valueMap.get(name).contains(value)) {
						valueMap.get(name).remove(value);
						if (valueMap.get(name).size() == 0) {
							valueMap.remove(name);
						}
					}
				}
				if (!valueMap.containsKey(name)) {// 如果需要删除
					writer.deleteDocuments(new Term("content", name));
				}
			}
			// 删除后更新索引
			updateDataCache(key, valueMap);
			writer.commit();
			writer.close();
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
	private void updateDataCache(String key, Map<String, List<String>> valueMap) {
		// 先将原有的删除
		FuzzyCorpDataCache.getInstance().delete(key);
		FuzzyMemoryCorpData fuzzyMemoryCorpData = new FuzzyMemoryCorpData();
		fuzzyMemoryCorpData.setValueMap(valueMap);
		// 再保存更新的内容
		FuzzyCorpDataCache.getInstance().set(key, fuzzyMemoryCorpData);
	}

	/**
	 * 查询单个
	 * 
	 * @param name
	 * @return
	 */
	public List<String> search(String name) {
		if(name==null||name.trim().equals("")){
			return null;
		}
		IndexWriter writer = null;
		try {
			writer = FuzzyUtil.getWriter(corpIndexPath,
					FuzzyConstants.THREAD_NUM, FuzzyConstants.FUZZY_TYPE_CORP);
			List<String> vs = CorpFuzzyTool.search(name, writer);
			writer.close();
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
	public Map<String, List<String>> search(String[] values) {
		IndexWriter writer = null;
		try {
			writer = FuzzyUtil.getWriter(corpIndexPath,
					FuzzyConstants.THREAD_NUM, FuzzyConstants.FUZZY_TYPE_CORP);
			Map<String, List<String>> map = CorpFuzzyTool
					.search(values, writer);
			writer.close();
			return map;
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
}
