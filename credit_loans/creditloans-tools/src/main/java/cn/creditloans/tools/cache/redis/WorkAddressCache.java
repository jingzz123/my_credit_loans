package cn.creditloans.tools.cache.redis;

import cn.creditloans.tools.cache.redis.RedisCache;


public class WorkAddressCache extends RedisCache<String> {

	private static final String KEY = "workaddr:";
	
	private static Object lock = new Object();
	
	private static WorkAddressCache instance = new WorkAddressCache();
	
	private WorkAddressCache() {}
	
	public static WorkAddressCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new WorkAddressCache();
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
