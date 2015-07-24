package cn.creditloans.tools.cache.redis;

import cn.creditloans.tools.cache.datas.MemoryCorrectDatas;

/**
 * 用户上传正确数据的缓存
 * @author Ash
 *
 */
public class UploadCorrectDatasCache extends RedisCache<MemoryCorrectDatas>{
	
	private static final String KEY = "user_upload_correct_datas:";
	
	private static Object lock = new Object();
	
	private static UploadCorrectDatasCache instance = new UploadCorrectDatasCache();
	
	private UploadCorrectDatasCache() {}
	
	public static UploadCorrectDatasCache getInstance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new UploadCorrectDatasCache();
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
