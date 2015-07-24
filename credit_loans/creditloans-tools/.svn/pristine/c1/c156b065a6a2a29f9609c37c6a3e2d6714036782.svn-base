package cn.creditloans.tools.cache.redis;

import cn.creditloans.tools.cache.datas.MemoryErrorDatas;

/**
 * 用户上传错误数据的缓存
 * @author Ash
 *
 */
public class UploadErrorDatasCache extends RedisCache<MemoryErrorDatas>{
	
	private static final String KEY = "user_upload_error_datas:";
	
	private static Object lock = new Object();
	
	private static UploadErrorDatasCache instance = new UploadErrorDatasCache();
	
	private UploadErrorDatasCache() {}
	
	public static UploadErrorDatasCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new UploadErrorDatasCache();
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
