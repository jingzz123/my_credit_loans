package cn.creditloans.tools.cache.redis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author 51ibm
 * TODO : 如果redis不可用，报警吧
 * http://www.cnblogs.com/wangzaizhen/p/3465815.html
 * @param <T>
 * TODO : 是否考虑一个线程用一个redis实例
 */
public abstract class RedisCache<T extends Serializable> {
	
	private static final Log logger = LogFactory.getLog(RedisCache.class);
	
	private static final String HOST;
	
	private static final String PORT;

	private static final JedisPool jedisPool;
	
	static {
		// 获取redis配置参数
		Properties p = new Properties();
		InputStream in = RedisCache.class.getResourceAsStream("/redis.properties");
		try {
			p.load(in);
		} catch (IOException e) {
			logger.error("Error occured while load redis property file", e);
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				logger.error("Close redis property file inputstream error", e);
			}
		}
		HOST = p.getProperty("redis.host", "");
		PORT = p.getProperty("redis.port", "6379");
		int port = Integer.parseInt(PORT);
		
		// 初始化 redis pool
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(100);
        // config.setMaxActive(100);
        config.setMaxIdle(50);
        // config.setMaxWait(10000L);
        config.setMaxWaitMillis(10000L);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);    
        jedisPool = new JedisPool(config, HOST, port, 1000*30);
	}
	
	public void set(String key, T obj) {
		this.set(key, obj, 0);
	}
	
	/**
	 * @param key
	 * @param obj
	 * @param expired 过期时间(单位：秒)  0表示不过期
	 */
	public void set(String key, T obj, int expired) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String _key = generateKeyPrefix() + key;
			
			if (expired == 0) {
				jedis.set(_key.getBytes("UTF-8"), serialize(obj));
			} else {
				jedis.setex(_key.getBytes("UTF-8"), expired, serialize(obj));
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}

	public void lpush(String key, T... obj) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String _key = generateKeyPrefix() + key;
			
			for (T _t : obj) {
				jedis.lpush(_key.getBytes("UTF-8"), serialize(_t));
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			
			// FIXME : 在数据初始化的时候抛出去异常(作用，在有异常的时候停止程序运行)
			// throw new RuntimeException(e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	public void hset(String key, String field, T obj) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String _key = generateKeyPrefix() + key;
			
			jedis.hset(_key.getBytes("UTF-8"), field.getBytes("UTF-8"), serialize(obj));
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
			
			// FIXME : 在数据初始化的时候抛出去异常(作用，在有异常的时候停止程序运行)
			// throw new RuntimeException(e);
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	public T get(String key) {
		T obj = null;
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String _key = generateKeyPrefix() + key;
			byte[] _byte = jedis.get(_key.getBytes("UTF-8"));
			if (_byte != null && _byte.length > 0) {
				obj = unserialize(_byte);
			} 
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		
		return obj;
	}
	
	public List<T> lrange(String key, int start, int end) {
		Jedis jedis = null;
		List<T> resultList = new ArrayList<T>();
		
		try {
			jedis = jedisPool.getResource();
			String _key = generateKeyPrefix() + key;
			
			// Long startTime = System.currentTimeMillis();
			List<byte[]> objList = jedis.lrange(_key.getBytes("UTF-8"), start, end);
			// Long endTime = System.currentTimeMillis();
			// logger.debug("查找记录消耗时间 ： " + (endTime - startTime) + "ms");
			
			if (objList!= null && objList.size() > 0) {
				for (byte[] bs : objList) {
					T _t = unserialize(bs);
					resultList.add(_t);
				}
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		
		return resultList;
	}
	
	public List<T> hvals(String key) {
		Jedis jedis = null;
		List<T> resultList = new ArrayList<T>();
		
		try {
			// long startTime = System.currentTimeMillis();
			jedis = jedisPool.getResource(); // FIXME : 这一步好像很费时,30ms左右, 好像只有第一次很耗时
			// long endTime = System.currentTimeMillis();
			// logger.debug("hvals 获取 jedis 消耗：" + (endTime - startTime) + " ms");
			
			String _key = generateKeyPrefix() + key;
			
			List<byte[]> objList = jedis.hvals(_key.getBytes("UTF-8"));
			if (objList!= null && objList.size() > 0) {
				for (byte[] bs : objList) {
					T _t = unserialize(bs);
					resultList.add(_t);
				}
			}
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		
		return resultList;
	}
	
	public T hget(String key, String field) {
		T obj = null;
		Jedis jedis = null;
		
		try {
			jedis = jedisPool.getResource();
			String _key = generateKeyPrefix() + key;
			byte[] _byte = jedis.hget(_key.getBytes("UTF-8"), field.getBytes("UTF-8"));
			
			if (_byte != null && _byte.length > 0) {
				obj = unserialize(_byte);
			} 
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		
		return obj;
	}
	
	public void hdel(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String _key = generateKeyPrefix() + key;
			jedis.hdel(_key.getBytes("UTF-8"), field.getBytes("UTF-8"));
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	public void delete(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			String _key = generateKeyPrefix() + key;
			jedis.del(_key.getBytes("UTF-8"));
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error(e.getMessage(), e);
			}
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
	}
	
	private byte[] serialize(T object) throws Exception {
		ObjectOutputStream oos = null;
		ByteArrayOutputStream baos = null;
		try {
			// 序列化
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(object);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			throw e;
		}
	}

	private T unserialize(byte[] bytes) throws Exception {
		ByteArrayInputStream bais = null;
		try {
			// 反序列化
			bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			return (T) ois.readObject();
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void test() {
		Jedis jedis = jedisPool.getResource();
		// jedis.lp
		// jedis.sc
	}
	
	
	protected abstract String generateKeyPrefix();
	
}
