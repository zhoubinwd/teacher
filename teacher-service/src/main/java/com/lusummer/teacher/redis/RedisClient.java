package com.lusummer.teacher.redis;

import com.lusummer.teacher.property.RedisProperty;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Created by zhoubin on 2018/1/20.
 */
@Component
public class RedisClient{


    public  static  Logger logger = LoggerFactory.getLogger(RedisClient.class);


    private static RedisProperty redisPropertysta;

    @Autowired
    private  RedisProperty redisPropertytemp;

    @PostConstruct
    public void init() {
        redisPropertysta = this.redisPropertytemp;
    }

    public static JedisPool getJedisPool(RedisProperty redisProperty){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(redisProperty.getMaxIdle());
        jedisPoolConfig.setMaxTotal(redisProperty.getMaxTotalIdle());
        jedisPoolConfig.setMaxWaitMillis(redisProperty.getMaxWaitTimeMills());
        return new JedisPool(jedisPoolConfig,redisProperty.getHost(),redisProperty.getPort(),redisProperty.getTimeOut());
    }

    public static  Jedis getJedis(){
        Jedis jedis;
        try{
             jedis = getJedisPool(redisPropertysta).getResource();
             jedis.select(redisPropertysta.getDatabase());
             return jedis;
        }catch (JedisException e){
            logger.error("get redis error",e);
            return null;
        }
    }

    public static void close(Jedis jedis){
        try{
            jedis.close();
        }catch (JedisException e){

        }
    }


    public static  boolean set(String key,String value,int expireSeconds){
        Jedis jedis = null;
        try{

            jedis = getJedis();
            jedis.set(key,value);
            jedis.expire(key,expireSeconds);
        }catch (Exception e){
            logger.error("redis set error",e);
            return false;
        }finally {
            close(jedis);
        }
        return true;
    }

    public String getString(String key){
        Jedis jedis = null;
        try{
            jedis = getJedis();
            String value = jedis.get(key);
            return value;
        }catch (Exception e){
            logger.error("redis get error",e);
        }finally {
            close(jedis);
        }
        return "";
    }







}
