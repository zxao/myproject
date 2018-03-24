package com.itheima.jedis;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

/**
 * jedis的测试
 * @author ZXAO
 *
 */
public class JedisTest {
	


	//连接单机版
	@Test
	public void testJedis() {
		//创建jedis对象，需要制定ip地址以及端口号
		Jedis jedis = new Jedis("192.168.113.130",6379);
		//使用jedis对象操作redis数据库
		jedis.set("test1", "test1");
		String value = jedis.get("test1");
		System.out.println(value);
		//关闭reids
		jedis.close();
		
	}
	
	//使用连接池连接单机版
	@Test
	public void testJedisPool() {
		//创建一个jedisPool对象
		JedisPool jedisPool = new JedisPool("192.168.113.130",6379);
		//从jedisPoll中获得jedis对象
		Jedis jedis = jedisPool.getResource();
		//使用jedis对象操作redis数据库
		String value = jedis.get("test1");
		System.out.println(value);
		//释放资源
		jedis.close();
		jedisPool.close();
	}
	
	
	//连接集群版redis
	@Test
	public void testJedisCluster() {
		
		//创建JedisCluster对象
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.113.130", 7001));
		nodes.add(new HostAndPort("192.168.113.130", 7002));
		nodes.add(new HostAndPort("192.168.113.130", 7003));
		nodes.add(new HostAndPort("192.168.113.130", 7004));
		nodes.add(new HostAndPort("192.168.113.130", 7005));
		nodes.add(new HostAndPort("192.168.113.130", 7006));
		
		JedisCluster jedisCluster = new JedisCluster(nodes);
		
		//直接使用jedisCluster对象操作redis数据库
		jedisCluster.set("hello", "world");
		String value = jedisCluster.get("hello");
		System.out.println(value);
		//关闭系统前关闭jedisCluster
		jedisCluster.close();
	}
	
}
