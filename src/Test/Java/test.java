
import Service.FoodService;
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
    }
    @org.junit.Test
    public void TimeTest(){
        Timestamp ts = TimeUtil.getSqlTime();
        int result = service.updateTIme(ts);
        System.out.println("result:"+result);
    }
}