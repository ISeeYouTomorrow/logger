package com.jrx.logger.config;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AnytxnLog {

    /**
     * 当前日志操作针对的对象的类型
     * @return
     */
    Class className();

    /**
     * 原值
     * @return
     */
    String old ();

    /**
     * 新值
     * @return
     */
    String now ();

    /**
     * 日志描述
     * @return
     */
    String logInfo();

}
