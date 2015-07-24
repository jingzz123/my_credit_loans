package cn.creditloans.core.cache.redis;

import cn.creditloans.core.dto.platform.PlatformUserDTO;
import cn.creditloans.tools.cache.redis.RedisCache;

public class PlatformUserCache extends RedisCache<PlatformUserDTO> {
	
	private static final String KEY = "platformuser:";

	private static Object lock = new Object();
	
	private static PlatformUserCache instance = new PlatformUserCache();
	
	private PlatformUserCache(){
		
	}
	
	public static PlatformUserCache getInstance(){
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new PlatformUserCache();
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
