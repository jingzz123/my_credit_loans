package cn.creditloans.core.cache.redis;

import cn.creditloans.core.dto.enterprise.EnterpriseUserDTO;
import cn.creditloans.tools.cache.redis.RedisCache;

public class EnterpriseUserCache extends RedisCache<EnterpriseUserDTO> {
	
	private static final String KEY = "enterpriseuser:";

	private static Object lock = new Object();
	
	private static EnterpriseUserCache instance = new EnterpriseUserCache();
	
	private EnterpriseUserCache(){
		
	}
	
	public static EnterpriseUserCache getInstance(){
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new EnterpriseUserCache();
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
