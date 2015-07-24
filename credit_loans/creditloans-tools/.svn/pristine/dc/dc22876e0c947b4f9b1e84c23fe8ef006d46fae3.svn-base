package cn.creditloans.tools.fuzzy.util;

import cn.creditloans.tools.cache.redis.RedisCache;

/**
 * 模糊查询缓存Redis
 * @author Administrator
 *
 */
public class FuzzyCorpDataCache extends RedisCache<FuzzyMemoryCorpData> {
	
	private static final String KEY = "fuzzy_datas:";
	
	private static Object lock = new Object();
	
	private static FuzzyCorpDataCache instance = new FuzzyCorpDataCache();
	
	private FuzzyCorpDataCache() {}
	
	public static FuzzyCorpDataCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new FuzzyCorpDataCache();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 判断是否存在数据
	 * @param key
	 * @return
	 */
	public boolean contain(String key){
		if(super.get(key)==null){
			return false;
		}
		return true;
	}

	@Override
	protected String generateKeyPrefix() {
		return KEY;
	}

}
