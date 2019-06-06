
import Service.FoodService;
import Utils.Print;
import Utils.TimeUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;

//指定bean注入的配置文件
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class)
public class test {

    @Autowired
    private FoodService service;

    @org.junit.Test
    public void test() {
        Print.msg("hello");
    }

    @org.junit.Test
    public void TimeTest() {
    }
}