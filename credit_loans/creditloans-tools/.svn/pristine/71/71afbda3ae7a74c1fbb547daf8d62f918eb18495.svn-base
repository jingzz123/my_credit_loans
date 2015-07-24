package cn.creditloans.tools.cache.redis;

import cn.creditloans.tools.cache.redis.RedisCache;


public class MobileCache extends RedisCache<String> {

	private static final String KEY = "mobile:";
	
	private static Object lock = new Object();
	
	private static MobileCache instance = new MobileCache();
	
	private MobileCache() {}
	
	public static MobileCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new MobileCache();
				}
			}
		}
		
		return instance;
	}
	
	@Override
	protected String generateKeyPrefix() {
		return KEY;
	}
	
	public static void main(String args[]) {
		// IdNumberCache.getInstance().save(key, obj);
	}
}
