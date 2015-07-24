package cn.creditloans.core.cache.redis;

import cn.creditloans.tools.cache.redis.RedisCache;

public class PlatformUserTimeOutCache extends RedisCache<Long>{
	
	private static final String KEY = "platformuser_timeout:";

	private static Object lock = new Object();
	
	private static PlatformUserTimeOutCache instance = new PlatformUserTimeOutCache();
	
	private PlatformUserTimeOutCache(){
		
	}
	
	public static PlatformUserTimeOutCache getInstance(){
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new PlatformUserTimeOutCache();
				}
			}
		}

		return instance;
	}
	
	@Override
	protected String generateKeyPrefix() {
		return KEY;
	}
}
