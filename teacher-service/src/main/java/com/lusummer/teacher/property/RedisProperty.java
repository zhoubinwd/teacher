package com.lusummer.teacher.property;

import lombok.Data;

/**
 * Created by zhoubin on 2018/1/20.
 */
@Data
public class RedisProperty {
    private Integer maxIdle;
    private Integer maxTotalIdle;
    private String host;
    private Integer port;
    private String password;
    private Long maxWaitTimeMills;
    private Integer timeOut;
    private Integer database;
}
