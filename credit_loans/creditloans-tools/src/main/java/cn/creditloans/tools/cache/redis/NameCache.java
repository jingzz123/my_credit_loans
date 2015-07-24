package cn.creditloans.tools.cache.redis;

import java.util.List;

import cn.creditloans.tools.cache.redis.RedisCache;


public class NameCache extends RedisCache<String> {

	// FIXME : 加一个query:name:这样吧
	private static final String KEY = "name:";
	
	private static Object lock = new Object();
	
	private static NameCache instance = new NameCache();
	
	private NameCache() {}
	
	public static NameCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new NameCache();
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
//		String key = "5_古平蝶";
//		
//		List<String> resultList = NameCache.getInstance().lrange(key, 0, -1);
//		
//		System.out.println(resultList.size());
		
		List<String> resultList = NameCache.getInstance().hvals("8_潘新翰1");
		System.out.println(resultList.size());
	}
}
