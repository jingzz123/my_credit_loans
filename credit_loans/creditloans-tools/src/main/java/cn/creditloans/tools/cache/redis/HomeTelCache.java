package cn.creditloans.tools.cache.redis;

import cn.creditloans.tools.cache.redis.RedisCache;


public class HomeTelCache extends RedisCache<String> {

	private static final String KEY = "hometel:";
	
	private static Object lock = new Object();
	
	private static HomeTelCache instance = new HomeTelCache();
	
	private HomeTelCache() {}
	
	public static HomeTelCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new HomeTelCache();
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
