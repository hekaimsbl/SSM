import Entity.Food;
import Service.FoodService;
import Service.FoodServiceImpl;
import Utils.IDGenerate;
import Utils.TimeUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @Author Hekai
 * @Date 2019/2/19 15:51
 * @Description TODO
 **/

@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class)
public class FoodTest {
    @Autowired
    FoodService foodService = new FoodServiceImpl();

    @org.junit.Test
    public void insertFoodTest() {
        Food food = new Food();
        String Id = IDGenerate.getGuid();
        BigInteger id = new BigInteger(Id);
        food.setId(id);
        food.setTitle("this is test");
        food.setAuthorId(id);
        food.setLatitude(27.642097281);
        food.setLongitude(113.155145645);
        food.setAddress("this is test");
        /*获取sql时间*/
        Timestamp ts = TimeUtil.getSqlTime();
        food.setTime(ts);
        int result = foodService.insertFood(food);
        System.out.println("result:" + result);
    }

    @org.junit.Test
    public void queryFoodByLatestTimeTest() {
        ArrayList<Food> foods = new ArrayList<Food>();
        foods = foodService.queryFoodByLatestTime();
        if (null != foods) {
            Food food = foods.get(1);
            JsonObject jsonObject = new JsonObject();

            Gson gson = new Gson();
            JsonElement jsonElement = gson.toJsonTree(food).getAsJsonObject();
            JsonArray jsonElements = gson.toJsonTree(foods).getAsJsonArray();
            jsonObject.addProperty("status", "success");
            //jsonObject.add("food",jsonElement);
            jsonObject.add("foods", jsonElements);
            String a = gson.toJson(jsonObject);
            System.out.println(a);
            System.out.println("/n/n****");
            String b = gson.toJson(foods);
            System.out.println(b);
        }
    }

    @org.junit.Test
    public void queryFoodDetailById(){
        Food food = foodService.queryFoodDetailById("101");
        System.out.println(food.getId());
    }
}
