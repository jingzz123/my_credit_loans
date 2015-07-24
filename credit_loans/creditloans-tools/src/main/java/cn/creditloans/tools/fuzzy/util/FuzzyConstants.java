package cn.creditloans.tools.fuzzy.util;

import org.apache.lucene.util.Version;

public class FuzzyConstants {
    public static final Version LUCENE_VER = Version.LUCENE_4_10_2;
    
    public static final int FUZZY_TYPE_ADDRESS = 1;//地址模糊匹配
    
    public static final int FUZZY_TYPE_CORP = 2;//公司模糊匹配
    
    public static final int INDEX_ADD = 1;//表示新增索引
    
    public static final int INDEX_DELETE = 2;//表示删除索引
    
    // 线程数，CPU个数+1
    public static final int THREAD_NUM = 3;
    
    public static final int LUCENE_SEARCH_NUM = 10;//lucene查询的最大数量
    
    public static final float MIN_SIMILARITY_SCORE = 90;//相似度最低分
    
    public static final int MAX_LENGTH = 350;//标准化的字符串的最大长度
    
    //公司的index_path，时间情况需要存放到配置文件中
    public static final String FUZZY_NAME_WORK_NAME = "work_name";
    
    public static final String FUZZY_NAME_WORK_ADDRESS = "work_address";
    
    public static final String FUZZY_NAME_HOME_ADDRESS = "home_address";
    
    public static final String FUZZY_NAME_URL_ADDRESS = "url_address";
    
    // FIXME：为是否执行lucene index暂时使用，如果执行，值设定为true
    public static final boolean RUN_LUCENE_INDEX = true;

}
