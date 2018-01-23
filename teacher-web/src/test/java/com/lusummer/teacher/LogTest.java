package com.lusummer.teacher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhoubin on 2018/1/20.
 */
public class LogTest {

    public  static Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        logger.error("Asdasd");
        System.out.printf("控制台打断点");
    }
}
