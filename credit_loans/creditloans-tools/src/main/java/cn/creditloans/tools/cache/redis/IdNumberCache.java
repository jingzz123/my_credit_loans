package cn.creditloans.tools.cache.redis;

import java.util.List;

import cn.creditloans.tools.cache.redis.RedisCache;


public class IdNumberCache extends RedisCache<String> {

	private static final String KEY = "idNumber:";
	
	private static Object lock = new Object();
	
	private static IdNumberCache instance = new IdNumberCache();
	
	private IdNumberCache() {}
	
	public static IdNumberCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new IdNumberCache();
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
//		String key = "6_186641970108267";
//		
//		List<String> resultList = IdNumberCache.getInstance().lrange(key, 0, -1);
//		
//		System.out.println(resultList.size());
//		
//		IdNumberCache.getInstance().delete(key);
		
		// IdNumberCache.getInstance().hset("37060", "1234567", "tom11,tom22,tom33");
		
//		long startTime = System.currentTimeMillis();
		List<String> resultList = IdNumberCache.getInstance().hvals("370601");
//		IdNumberCache.getInstance().get("tom");
//		long endTime = System.currentTimeMillis();
//		System.out.println("::" + (endTime - startTime));
		System.out.println(resultList.size());
//		System.out.println(resultList.get(0));
		
		
	}
}
