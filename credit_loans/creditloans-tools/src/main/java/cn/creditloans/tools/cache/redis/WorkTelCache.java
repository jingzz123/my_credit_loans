package cn.creditloans.tools.cache.redis;

import java.util.List;

import cn.creditloans.tools.cache.redis.RedisCache;


public class WorkTelCache extends RedisCache<String> {

	private static final String KEY = "worktel:";
	
	private static Object lock = new Object();
	
	private static WorkTelCache instance = new WorkTelCache();
	
	private WorkTelCache() {}
	
	public static WorkTelCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new WorkTelCache();
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
		List<String> resultList = WorkTelCache.getInstance().hvals("1_03200574542");
		
		for (String result : resultList) {
			System.out.println(result);
		}
		
		WorkTelCache.getInstance().hdel("1_03200574542", "1010120141127000006");
	}
}
