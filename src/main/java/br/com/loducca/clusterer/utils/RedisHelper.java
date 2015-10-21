package br.com.loducca.clusterer.utils;

import redis.clients.jedis.Jedis;

/**
 * Created by: dambros
 * Date: 10/21/2015
 */
public class RedisHelper {

	private static Jedis jedis;

	static {
		String url = PropertiesHelper.getProperties("redis.path");
		int port = Integer.parseInt(PropertiesHelper.getProperties("redis.port"));
		jedis = new Jedis(url, port);
	}

	public static void setPair(String key, String value) {
		jedis.set(key, value);
	}

	public static String getValue(String key) {
		return jedis.get(key);
	}
}
