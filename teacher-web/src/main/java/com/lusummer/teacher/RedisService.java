package com.lusummer.teacher;

import com.lusummer.teacher.redis.RedisClient;

import javax.annotation.Resource;

/**
 * Created by zhoubin on 2018/1/20.
 */
public class RedisService {


    public static void main(String[] args) {
        boolean s =RedisClient.set("b","2",500);
        System.out.printf(s+"");
    }
}
