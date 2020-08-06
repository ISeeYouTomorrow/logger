package com.jrx.logger;

import com.jrx.logger.config.AnytxnLog;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LoggerApplication.class)
class LoggerApplicationTests {

    @AnytxnLog(className = LoggerApplicationTests.class,now = "now",old = "old", logInfo = "测试日志")
    @Test
    void contextLoads() {

        System.out.println("context");

    }

}
