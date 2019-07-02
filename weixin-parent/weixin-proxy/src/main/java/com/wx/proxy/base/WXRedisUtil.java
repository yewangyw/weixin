package com.wx.proxy.base;

import redis.clients.jedis.Jedis;

public class WXRedisUtil {


    /**
     *
     * @param key
     * @return
     */
    public String get(String key){
        Jedis jedis = getJedis();
        String value = jedis.get(key);
        return value;
    }

    /**
     * 设置属性 以及过期时间
     * @param key
     * @param value
     * @param time
     * @return
     */
    public String set(String key,String value,int time){
        Jedis jedis = getJedis();
        String ok = jedis.set(key, value);
        jedis.expire(key,time);
        return ok;
    }



    public Jedis getJedis(){
        Jedis jedis = new Jedis("192.168.1.179", 6379);
        jedis.auth("123");
        return jedis;
    }


}
