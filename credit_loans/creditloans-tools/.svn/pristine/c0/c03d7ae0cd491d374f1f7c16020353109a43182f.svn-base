package cn.creditloans.tools.fuzzy.corp;

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

import com.solan.corp.compare.CorpCompare;

/**
 * 公司模糊匹配的工具
 * 
 * @author Ash
 * 
 */
public class CorpFuzzyTool {

	/**
	 * 提供对公司名称预先处理
	 * 
	 * @param name
	 * @return
	 */
	public static String prepareCorpName(String name) {
		if (name == null) {
			return null;
		}
		if (name.trim().equals("")) {
			return "";
		}
		CorpCompare corpCompare = CORP_COMPARE.get();
		// 对传入的字符串进行一些处理，这个处理可能会放到插入时统一处理，不会放在这里
		//corpCompare.prepareAnd2objstr(str)：多了一步骤，不需要
		/**
		 * 处理包括：全角转半角、删除文本中半角和全角空格、开头就是英文的情况进行处理、对汉字特定字符串的转换
		 * 
		 */
		String srcCorp = corpCompare.prepareStr(name);
		if (srcCorp.length() > FuzzyConstants.MAX_LENGTH) {
			srcCorp = name;
		}
		return srcCorp;
	}

	/**
	 * 查询单个
	 * 
	 * @param values
	 *            ：传入的一个公司名称
	 * @return
	 */

	public static List<String> search(String value, IndexWriter writer)
			throws Exception {
		Map<String, List<String>> map = search(new String[] { value }, writer);
		if (map == null) {
			return null;
		}
		return map.get(value);
	}

	/**
	 * 从索引中查询模糊查询多个
	 * 
	 * @param values
	 *            ：传入的待检查的多个公司名称列表
	 * @param writer
	 *            : FuzzyUtil.getWriter方法获取的，type=Constants.FUZZY_TYPE_CORP
	 * @return
	 * @throws Exception
	 */
	public static Map<String, List<String>> search(String[] values,
			IndexWriter writer) throws Exception {
		ExecutorService threadPool = FuzzyUtil
				.createService(FuzzyConstants.THREAD_NUM);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		IndexSearcher searcher = new IndexSearcher(DirectoryReader.open(writer,
				false), threadPool);
		int len = values.length;
		List<String> list = null;
		for (int i = 0; i < len; i++) {
			if(values[i]==null||values[i].trim().equals("")){
				continue;
			}
			list = searchSingle(searcher, values[i]);
			if (list != null && list.size() > 0) {
				map.put(values[i], list);
			}
		}
		FuzzyUtil.shutdownService(threadPool);
		return map;
	}

	public static List<String> searchSingle(IndexSearcher searcher, String value) {
		try {
			String queryTextTrim = StringUtils.replaceChars(value, "()/?*\"[]",
					null);
			if (queryTextTrim.isEmpty())
				return null;
			Query query = QUERY_PARSER.get().parse(
					QueryParser.escape(queryTextTrim));
			// 利用lucene找到相似度最高的最多前10个数据
			// Sort.RELEVANCE:该方法指对检索的结果按照 查询的相关度进行排序，默认采用，是new sort()的简写，其功能一样
			TopDocs topDocs = searcher.search(query,
					FuzzyConstants.LUCENE_SEARCH_NUM);
			topDocs.getMaxScore();
			int length = topDocs.scoreDocs.length;
			if (length == 0) {
				return null;
			}
			CorpCompare corpCompare = CORP_COMPARE.get();
			// 对传入的字符串进行一些处理，这个处理可能会放到插入时统一处理，不会放在这里
			String srcCorp = corpCompare.prepareAnd2objstr(value);
			/**
			 * 对于找出来的前面10个，利用公司的算法进行重新算法处理 从这些中找到相似度最高的一个
			 */
			List<String> list = new ArrayList<String>();
			for (int i = 0; i < length; i++) {
				Document doc = searcher.doc(topDocs.scoreDocs[i].doc);
				String targetCorp = doc.get("content");
				float score = corpCompare.compareViaObjstr(srcCorp,
						corpCompare.prepareAnd2objstr(targetCorp), true);
				float corp_min_similarity_score = Float.parseFloat(String.valueOf(AppContext.getInstance().getService_constants().get(Constants.FUZZY_CORP_MIN_SIMILARITY_SCORE)));
				if (score >= corp_min_similarity_score) {
					list.add(targetCorp);
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private static ThreadLocal<CorpCompare> CORP_COMPARE = new ThreadLocal<CorpCompare>() {
		protected CorpCompare initialValue() {
			return new CorpCompare();
		};
	};

	private static ThreadLocal<QueryParser> QUERY_PARSER = new ThreadLocal<QueryParser>() {
		protected QueryParser initialValue() {
			return new QueryParser("content", FuzzyUtil.getCorpAnalyzer());
		};
	};

}
