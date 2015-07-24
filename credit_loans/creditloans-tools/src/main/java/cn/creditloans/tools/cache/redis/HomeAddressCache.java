package cn.creditloans.tools.cache.redis;

import cn.creditloans.tools.cache.redis.RedisCache;


public class HomeAddressCache extends RedisCache<String> {

	private static final String KEY = "homeaddr:";
	
	private static Object lock = new Object();
	
	private static HomeAddressCache instance = new HomeAddressCache();
	
	private HomeAddressCache() {}
	
	public static HomeAddressCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new HomeAddressCache();
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
