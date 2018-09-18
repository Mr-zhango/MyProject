package cn.myfreecloud.store.utils;

import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	private static JedisPoolConfig poolConfig = new JedisPoolConfig();
	private static JedisPool pool ;
	static{
		//解析配置文件
		try {
			Properties properties = new Properties();
			//使用类加载器 加载配置文件
			properties.load(RedisUtil.class.getClassLoader().getResourceAsStream("redis.properties"));
			int maxIdle =Integer.parseInt( properties.getProperty("maxIdle"));
			int maxTotal =Integer.parseInt( properties.getProperty("maxTotal"));
			int minIdle =Integer.parseInt( properties.getProperty("minIdle"));
			String host =properties.getProperty("host");
			int port =Integer.parseInt( properties.getProperty("port"));
			poolConfig.setMaxIdle(maxIdle);
			poolConfig.setMaxTotal(maxTotal);
			poolConfig.setMinIdle(minIdle);
			pool= new JedisPool(poolConfig,host, port);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static Jedis getConnection(){
		return pool.getResource();
	}
	public static void close(){
		pool.close();
	}
}
