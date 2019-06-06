import Entity.*;
import Service.FoodService;
import Service.FoodServiceImpl;
import Service.UserService;
import Service.UserServiceImpl;
import Utils.*;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.*;

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

    @Autowired
    UserService userService = new UserServiceImpl();

    ResultUtils resultUtils = new ResultUtils();

    /**
     * 查询最新美食
     */
    @org.junit.Test
    public void queryLatestFood() {
        List<Food> foods = foodService.queryLatestFood(0, 20);
        for (Food food : foods) {
            Print.msg(food.getId());
            Print.msg(food.getImageUrls());
            //Print.msg(foodService.queryFoodRecommendNumber(food.getId()));
        }

    }

    /**
     * 查询周排行
     */
    @org.junit.Test
    public void queryWeekRankFood() {
        List<Food> foods = foodService.queryRankWeekFood(0, 4);
        for (Food food : foods) {
            Print.msg(TimeUtil.dateToString(food.getTime()));
        }
    }

    /**
     * 查询周排行
     */
    @org.junit.Test
    public void queryRankMonthFood() {
        List<Food> foods = foodService.queryRankMonthFood(0, 4);
        for (Food food : foods) {
            Print.msg(TimeUtil.dateToString(food.getTime()));
        }
    }

    @org.junit.Test
    public void insertTag() {
        String foodId = IDGenerate.getGuid();
        String tag = "超辣";
        int insertResult = foodService.insertTag(tag);
        Print.msg("insertResult" + insertResult);
    }

    @org.junit.Test
    public void insertImages() {
        String foodId = "20191554260951620101";
        List<String> urls = new ArrayList<String>();
        urls.add("aa");
        urls.add("bb");
        int insertResult = foodService.insertImages(foodId, urls);
        Print.msg("insertResult" + insertResult);
    }

    @org.junit.Test
    public void stringCut() {
        String a = " xianss ";
        Print.msg(a.length());
        int b = a.indexOf(" ");
        String[] arr = a.split(" ");
        for (String cc : arr) {
            Print.msg("cc" + cc);
        }
        Print.msg(b);
    }

    @org.junit.Test
    public void insertTags() {
        List<String> tags = new ArrayList<String>();
        tags.add("咸");
        tags.add("上火");
        tags.add("便宜");

        int insertResult = foodService.insertTags(tags);
        Print.msg("insertResult" + insertResult);

        String foodId = IDGenerate.getGuid();

    }

    @org.junit.Test
    public void queryTags() {
    }

    @org.junit.Test
    public void queryTagRelation() {
        String foodId = "20191554260955070206";
        List<Integer> tags = foodService.queryTagRelationShip(foodId);
        List<String> tagsName = foodService.queryTags(tags);
        for (String a : tagsName) {
            Print.msg(a);
        }
    }

    @org.junit.Test
    public void doubleTest() {
        DecimalFormat df = new DecimalFormat("0.00");
        double d = 0.200;
        int a = 10003;
        double c = a / 1000.0;
        String e = df.format(c);
        System.out.println(e);
    }

    @org.junit.Test
    public void insertData() {
        Food food = new Food();
        User user = new User();

        String[] images = {"https://c-lj.gnst.jp/public/article/detail/a/10/00/a1000112/img/basic/a1000112_main.jpg?20180801110104"
                , "https://c-lj.gnst.jp/public/article/detail/a/10/00/a1000112/img/basic/a1000112_main.jpg?20180801110104"};
        //food.setImageUrls(images);
        for (int i = 0; i < 10; i++) {
            food.setId(IDGenerate.getGuid());
            food.setAddress("长沙市001" + 1000 + i);
            food.setTime(TimeUtil.getSqlTime());
            Print.msg(food.getTime().toString());
            food.setLongitude(112.944882 + i * 0.00099);
            food.setLatitude(28.21485 + i * 0.00099);
            food.setTitle("测试数据标题" + i * 10);

            if ((i / 20) == 0 || i == 0) {
                user.setId(IDGenerate.getGuid());
                user.setCreateTime(TimeUtil.getSqlTime());
                user.setImgHead("http://img.wxcha.com/file/201607/29/a1e67e297d.jpg");
                user.setName("测试用户" + i);
                int result = userService.insertUser(user);
                Print.msg("UserResult = " + result);
            }
            food.setAuthorId(user.getId());
            int result = foodService.insertFood(food);
            Print.msg(result);
        }
    }

    /**
     * 关键字查询
     */
    @org.junit.Test
    public void queryKey() {
        int startPage = 0;
        int pageSize = 20;

        String key = "题 好";

        String[] keys = key.split(" ");
        //start
        List<String> keyList = Arrays.asList(keys);
        /**
         * 总查询结果包含美食表，标签表
         */
        List<QueryData<List<KeySearchResult>>> totalQueryData = new ArrayList<QueryData<List<KeySearchResult>>>();


        QueryData<List<KeySearchResult>> foodQueryData = new QueryData<List<KeySearchResult>>();
        QueryData<List<KeySearchResult>> tagQueryData = new QueryData<List<KeySearchResult>>();

        foodQueryData.setMsg("table_food");
        tagQueryData.setMsg("table_tag");

        List<KeySearchResult> totalFoodQueryList = new ArrayList<KeySearchResult>();
        List<KeySearchResult> totalTagQueryList = new ArrayList<KeySearchResult>();

        List<KeySearchResult> tagQueryList = new ArrayList<KeySearchResult>();
        List<KeySearchResult> foodQueryList = new ArrayList<KeySearchResult>();
        //直接查询
        //直接查询-美食
        List<KeySearchResult> foodQueryDList = foodService.queryByKey(startPage, pageSize, key);
        totalFoodQueryList.addAll(foodQueryDList);
        //直接查询-标签
        tagQueryList = userService.queryKey(startPage, pageSize, key);
        totalTagQueryList.addAll(tagQueryList);
        //分割查询
        //去重
        if (!keyList.get(0).equals(key) || keyList.get(0).length() != key.length()) {
            //查询美食表标题
            foodQueryList = foodService.queryByKeys(startPage, pageSize, keyList);
            totalFoodQueryList.addAll(foodQueryList);

            //查询标签
            tagQueryList = userService.queryKeys(startPage, pageSize, keyList);
            totalTagQueryList.addAll(tagQueryList);
        }

        //合并
        foodQueryData.setData(totalFoodQueryList);
        totalQueryData.add(foodQueryData);

        tagQueryData.setData(totalTagQueryList);
        totalQueryData.add(tagQueryData);

        String json = ResultUtils.generateKeysResult(totalQueryData);
        Gson gson = new Gson();
        //String json = gson.toJson(totalQueryData);
        Print.msg(json);
        //Print.msg(gson.toJson(totalQueryData));
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(json);
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        if (Message.RESULT_OK == jsonObject.get("status_code").getAsInt()) {
            JsonArray data = jsonObject.get("data").getAsJsonArray();
            List<QueryData<List<KeySearchResult>>> re = gson.fromJson(data, new TypeToken<List<QueryData<List<KeySearchResult>>>>() {
            }.getType());
            for (int i = 0; i < re.size(); i++) {
                Print.msg(re.get(i).getMsg());
                for (int j = 0; j < re.get(i).getData().size(); j++) {
                    Print.msg(re.get(i).getData().get(j).getDescribe());
                }
            }
        }
    }

    @org.junit.Test
    public void queryFoodById() {
        Food food = foodService.queryOneById("20191554260951620101");
        ResultUtils resultUtils = new ResultUtils();
        Print.msg(resultUtils.generateFoodResult(food));
    }

    @org.junit.Test
    public void Key() {
        QueryByKeys queryByKeys = new QueryByKeys(0, 20, "题");
        List<QueryData<List<KeySearchResult>>> a = queryByKeys.queryKeys();
    }

    @org.junit.Test
    public void getNearFood() {
        int cityCode = 101;
        int startPage = 0;
        int pageSize = 10;
        List<Food> foods = foodService.queryNearFood(cityCode,startPage,pageSize);
        Print.msg(resultUtils.generateFoodResult(foods));
    }

    @org.junit.Test
    public void getCommunityFood() {
        int startPage = 0;
        int pageSize = 10;
        List<Food> foods = foodService.queryCommunityFood(startPage,pageSize);
        Print.msg(resultUtils.generateFoodResult(foods));
    }

    @org.junit.Test
    public void queryUserCollectionFood() {
        String userId = "20191554260534017102";
        int startPage = 0;
        int pageSize = 20;
        List<Food> foods = foodService.queryUserCollectionFood(userId,startPage,pageSize);
        Print.msg(resultUtils.generateFoodResult(foods));
    }
    @org.junit.Test
    public void queryFoodByKey() {
        String key = "美味";
        int startPage = 0;
        int pageSize = Message.PAGE_SIZE;
        String[] keys = key.split(" ");
        List<String> keyList = Arrays.asList(keys);

        List<KeySearchResult> totalFoodQueryList = new ArrayList<KeySearchResult>();
        List<KeySearchResult> foodQueryList = new ArrayList<KeySearchResult>();
        List<KeySearchResult> foodQueryDList = foodService.queryByKey(startPage, pageSize, key);
        totalFoodQueryList.addAll(foodQueryDList);

        if (!keyList.get(0).equals(key) || keyList.get(0).length() != key.length()) {
            //查询美食表标题
            foodQueryList = foodService.queryByKeys(startPage, pageSize, keyList);
            totalFoodQueryList.addAll(foodQueryList);
        }
        Print.msg(resultUtils.generateFoodResult(totalFoodQueryList));
    }
}
