package cn.creditloans.tools.cache.redis;

import java.util.List;

import cn.creditloans.tools.cache.redis.RedisCache;


public class WorkNameCache extends RedisCache<String> {

	private static final String KEY = "workname:";
	
	private static Object lock = new Object();
	
	private static WorkNameCache instance = new WorkNameCache();
	
	private WorkNameCache() {}
	
	public static WorkNameCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new WorkNameCache();
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
		List<String> resultList = WorkNameCache.getInstance().hvals("1_古平蝶");
		for (String result : resultList) {
			System.out.println(result);
		}
		
		// WorkNameCache.getInstance().hdel("1_古平蝶", "1010120141127000006");
	}
}
