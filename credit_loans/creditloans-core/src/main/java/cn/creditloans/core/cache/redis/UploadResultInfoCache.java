package cn.creditloans.core.cache.redis;

import cn.creditloans.core.dto.enterprise.UploadResultInfoDTO;
import cn.creditloans.tools.cache.redis.RedisCache;

public class UploadResultInfoCache extends RedisCache<UploadResultInfoDTO> {

	private static final String KEY = "user_upload_result_datas:";

	private static Object lock = new Object();
	
	private static UploadResultInfoCache instance = new UploadResultInfoCache();
	
	private UploadResultInfoCache () {}
	
	public static UploadResultInfoCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new UploadResultInfoCache();
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
