package cn.creditloans.core.cache.redis;

import cn.creditloans.tools.cache.redis.RedisCache;

public class EnterpriseStateCache extends RedisCache<Integer>  {

	private static final String KEY = "enterprisestate:";

	private static Object lock = new Object();
	
	private static EnterpriseStateCache instance = new EnterpriseStateCache();
	
	private EnterpriseStateCache(){
		
	}
	
	public static EnterpriseStateCache getInstance(){
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new EnterpriseStateCache();
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
