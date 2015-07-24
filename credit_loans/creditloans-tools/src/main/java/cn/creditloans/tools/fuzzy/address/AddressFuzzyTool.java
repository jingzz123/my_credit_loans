package cn.creditloans.tools.fuzzy.address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;

import cn.creditloans.tools.constants.Constants;
import cn.creditloans.tools.context.AppContext;
import cn.creditloans.tools.fuzzy.util.FuzzyConstants;
import cn.creditloans.tools.fuzzy.util.FuzzyUtil;

import com.solan.addr.UniteRegionAndAddr;
import com.solan.compare.Compare;

/**
 * 地址模糊匹配的一些工具
 * @author Ash
 *
 */
public class AddressFuzzyTool {
	
	private static UniteRegionAndAddr standardAddr = new UniteRegionAndAddr();
	/**
	 * 标准化地址
	 * @param address：地址信息
	 * @return
	 */
	public static String formatAddr(String address){
		if(address==null){
			return null;
		}
		if(address.trim().equals("")){
			return "";
		}
		String standard = standardAddr.formatAddr(address, "");
		if(standard.length()>FuzzyConstants.MAX_LENGTH){
			standard = address;
		}
		return standard;
	}
	
	/**
	 * 查询单个
	 * @param values：传入的一个地址名称
	 * @return
	 */
	
	public static List<String> search(String value,IndexWriter writer) throws Exception{
		Map<String,List<String>> map = search(new String[]{value},writer);
		if(map==null){
			return null;
		}
		return map.get(value);
	}
	/**
	 * 从索引中查询模糊查询多个
	 * @param values：传入的待检查的多个公司名称列表
	 * @param writer: FuzzyUtil.getWriter方法获取的，type=Constants.FUZZY_TYPE_CORP
	 * @return
	 * @throws Exception
	 */
	public static Map<String,List<String>> search(String[] values,IndexWriter writer) throws Exception{
		ExecutorService threadPool = FuzzyUtil.createService(FuzzyConstants.THREAD_NUM);
		Map<String,List<String>> map = new HashMap<String,List<String>>();
		IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(writer, false) ,threadPool);
		int len = values.length;
		List<String> list = null;
		for(int i=0; i<len; i++){
			if(values[i]==null||values[i].trim().equals("")){
				continue;
			}
			list = searchSingle(searcher,values[i]);
			if(list!=null&&list.size()>0){
				map.put(values[i], list);
			}
		}
		FuzzyUtil.shutdownService(threadPool);
		return map;
	}
	
	public static List<String> searchSingle(IndexSearcher searcher,String srcAddress){
        try {
      String queryTextTrim = StringUtils.replaceChars(srcAddress, "()/?*\"[]", null);
        if (queryTextTrim.isEmpty())
            return null;
        Query query = QUERY_PARSER.get().parse(QueryParser.escape(queryTextTrim));
        //利用lucene找到相似度最高的最多前10个数据
        //Sort.RELEVANCE:该方法指对检索的结果按照 查询的相关度进行排序，默认采用，是new sort()的简写，其功能一样
        TopDocs topDocs = searcher.search(query, FuzzyConstants.LUCENE_SEARCH_NUM);
        topDocs.getMaxScore();
        int length = topDocs.scoreDocs.length;
        if (length == 0) {
        	return null;
        }
        Compare corpCompare = ADDRESS_COMPARE.get();
        /**
         * 对于找出来的前面10个，利用公司的算法进行重新算法处理
         * 从这些中找到相似度最高的一个
         */
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < length; i++) {
            Document doc = searcher.doc(topDocs.scoreDocs[i].doc);
            String targetAddress = doc.get("content");
            //比较两个地址的相似度
            float score = corpCompare.acuityPowerCompareFloat(srcAddress, targetAddress);
            float address_min_similarity_score = Float.parseFloat(String.valueOf(AppContext.getInstance().getService_constants().get(Constants.FUZZY_ADDRESS_MIN_SIMILARITY_SCORE)));
            if(score>=address_min_similarity_score){
            	list.add(targetAddress);
            }
        }
        return list;
    } catch (Exception e) {
    	e.printStackTrace();
        return null;
    }
        
	}
	
    private static ThreadLocal<Compare> ADDRESS_COMPARE = new ThreadLocal<Compare>() {
        protected Compare initialValue() {
            return new Compare();
        };
    };
    
    private static ThreadLocal<QueryParser> QUERY_PARSER = new ThreadLocal<QueryParser>() {
        protected QueryParser initialValue() {
            return new QueryParser("content", FuzzyUtil.getAddressAnalyzer());
        };
    };
    
	
}
