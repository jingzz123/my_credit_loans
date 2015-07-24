package cn.creditloans.tools.fuzzy.util;

import cn.creditloans.tools.cache.redis.RedisCache;

/**
 * 模糊查询缓存Redis
 * @author Administrator
 *
 */
public class FuzzyAddressDataCache extends RedisCache<FuzzyMemoryAddressData> {
	
	private static final String KEY = "fuzzy_datas:";
	
	private static Object lock = new Object();
	
	private static FuzzyAddressDataCache instance = new FuzzyAddressDataCache();
	
	private FuzzyAddressDataCache() {}
	
	public static FuzzyAddressDataCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new FuzzyAddressDataCache();
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
