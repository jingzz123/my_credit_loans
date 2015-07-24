package cn.creditloans.core.cache.redis;

import cn.creditloans.tools.cache.redis.RedisCache;

public class EnterpriseUserTimeOutCache extends RedisCache<Long>{
	
	private static final String KEY = "enterpriseuser_timeout:";

	private static Object lock = new Object();
	
	private static EnterpriseUserTimeOutCache instance = new EnterpriseUserTimeOutCache();
	
	private EnterpriseUserTimeOutCache(){
		
	}
	
	public static EnterpriseUserTimeOutCache getInstance(){
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new EnterpriseUserTimeOutCache();
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
