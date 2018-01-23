package com.lusummer.teacher.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by zhoubin on 2018/1/20.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ="classpath*:teacher-test.xml")
public class RedisTest {

    @Resource
    private RedisClient redisClient;

    @Test
    public void testRedis(){
        boolean  a = redisClient.set("a","11",100);
        System.out.printf(a+"");
    }
}
