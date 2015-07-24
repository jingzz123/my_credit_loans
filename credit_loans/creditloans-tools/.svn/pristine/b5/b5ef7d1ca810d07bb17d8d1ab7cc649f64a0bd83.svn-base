package cn.creditloans.tools.cache.redis;

import cn.creditloans.tools.cache.datas.MemoryDatas;

/**
 * 用户上传数据的缓存
 * @author Ash
 *
 */
public class UploadDatasCache extends RedisCache<MemoryDatas>{
	
	private static final String KEY = "user_upload_datas:";
	
	private static Object lock = new Object();
	
	private static UploadDatasCache instance = new UploadDatasCache();
	
	private UploadDatasCache() {}
	
	public static UploadDatasCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new UploadDatasCache();
				}
			}
		}
		
		return instance;
	}
	
	/**
	 * 判断是否存在数据
	 * @param key
	 * @return
	 */
	public boolean contain(String key){
		if(super.get(key)==null){
			return false;
		}
		return true;
	}
	
	@Override
	protected String generateKeyPrefix() {
		return KEY;
	}

}
