package Controllers;

import Entity.*;
import Service.FoodService;
import Service.FoodServiceImpl;
import Service.UserService;
import Service.UserServiceImpl;
import Utils.*;
import com.google.gson.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * The type Food controller.
 */
@Controller
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService = new FoodServiceImpl();
    @Autowired
    private UserService userService = new UserServiceImpl();

    private ResultUtils resultUtils = new ResultUtils();


    /**
     * 获取附近的美食列表
     *
     * @param cityCode  the city code
     * @param startPage the start page
     * @param pageSize  the page size
     * @return the near food
     */
    @RequestMapping(value = "/nearFood", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getNearFood(@RequestParam("cityCode") int cityCode,@RequestParam("startPage") int startPage, @RequestParam("pageSize") int pageSize) {
        List<Food> foods = foodService.queryNearFood(cityCode,startPage,pageSize);
        return resultUtils.generateFoodResult(foods);
    }


    /**
     * 获取社群的美食列表
     *
     * @param startPage the start page
     * @param pageSize  the page size
     * @return the community food
     */
    @RequestMapping(value = "/communityFood", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getCommunityFood(@RequestParam("startPage") int startPage,
                                   @RequestParam("pageSize") int pageSize) {
        List<Food> foods = foodService.queryCommunityFood(startPage,pageSize);
        return resultUtils.generateFoodResult(foods);
    }

    /**
     * 最新发布的美食，时间降序
     *
     * @param startPage 查询起点
     * @param pageSize  查询记录条数
     * @return json latest food
     */
    @RequestMapping(value = "/latestFood", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getLatestFood(@RequestParam("startPage") int startPage,
                                @RequestParam("pageSize") int pageSize) {
        List<Food> foods = foodService.queryLatestFood(startPage, pageSize);
        for (int i = 0; i < foods.size(); i++) {
            List<Integer> tagsId = foodService.queryTagRelationShip(foods.get(i).getId());
            if (!tagsId.isEmpty()) {
                List<String> tagsName = foodService.queryTags(tagsId);
                foods.get(i).setTags(tagsName);
            }
            String userName = userService.queryUserNameById(foods.get(i).getAuthorId());
            foods.get(i).setUserName(userName);
        }
        return resultUtils.generateFoodResult(foods);
        //return ResultUtils.generateFoodResult(foods);
    }


    /**
     * 美食周排行Api接口
     *
     * @param startPage the start page
     * @param pageSize  the page size
     * @return json data
     */
    @RequestMapping(value = "/foodRankOfWeek", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryFoodOfWeekRank(@RequestParam("startPage") int startPage,
                                      @RequestParam("pageSize") int pageSize) {
        List<Food> foods = foodService.queryRankWeekFood(startPage, pageSize);
        return resultUtils.generateFoodResult(foods);
    }


    /**
     * 美食月排行榜Api
     *
     * @param startPage  the start page
     * @param pageNumber the page number
     * @return json data
     */
    @RequestMapping(value = "/foodRankOfMonth", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryFoodOfMonthRank(@RequestParam("startPage") int startPage,
                                       @RequestParam("pageNumber") int pageNumber) {
        List<Food> foods = foodService.queryRankMonthFood(startPage, pageNumber);
        return resultUtils.generateFoodResult(foods);
    }


    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam(value = "data", required = false) String
                                 data, @RequestParam(value = "pictures", required = false) CommonsMultipartFile[] pictures) {
        Gson gson = new GsonBuilder().serializeNulls().create();

        Food food = gson.fromJson(data, Food.class);

        food.setId(IDGenerate.getGuid());
        food.setTime(TimeUtil.getSqlTime());
        int resultOfInsertFood = foodService.insertFood(food);

        List<String> tags = food.getTags();
        int resultOfInsertTags = foodService.insertTags(tags);
        int resultOfInsertTagsRelationship = foodService.insertTagRelationship(food.getId(), tags);

        String sqlPath = null;
        String localPath = "D:\\Android\\XunwImages\\";
        List<String> imagesPath = new ArrayList<String>();
        for (CommonsMultipartFile file : pictures) {
            String contentType = file.getContentType();
            String suffixName = contentType.substring(contentType.indexOf("/") + 1);
            String uuid = UUID.randomUUID().toString().replace("-", "");
            String filename = uuid + "." + suffixName;
            try {
                file.transferTo(new File(localPath + filename));
            } catch (IllegalStateException el) {
                el.printStackTrace();
            } catch (IOException el) {
                el.printStackTrace();
            }

            sqlPath = "/images/" + filename;

            imagesPath.add(sqlPath);

            Print.msg("sqlPath:" + sqlPath);
            //访问路径：host + sqlPath
        }

        int resultOfInsertImages = foodService.insertImages(food.getId(), imagesPath);

        ApiResult<String> apiResult = new ApiResult<String>();

        if (resultOfInsertFood > 0 &&
                resultOfInsertImages > 0 &&
                resultOfInsertTags >= 0 &&
                resultOfInsertTagsRelationship > 0) {
            apiResult.setStatus(Message.RESULT_OK);
        } else {
            apiResult.setStatus(Message.RESULT_SERVER_ERR);
        }
        return gson.toJson(apiResult);
    }

    @RequestMapping(value = "/queryOne", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryOne(@RequestParam("foodId") String foodId) {
        Food food = foodService.queryOneById(foodId);
        return resultUtils.generateFoodResult(food);
    }

    @RequestMapping(value = "/queryFoodByKey", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryFoodByKey(@RequestParam("key") String key,
                           @RequestParam("startPage") int startPage) {
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
        return resultUtils.generateFoodResult(totalFoodQueryList);
    }

    @RequestMapping(value = "/queryKey", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryKey(@RequestParam("key") String key,
                           @RequestParam("startPage") int startPage) {
        int pageSize = Message.PAGE_SIZE;
        String[] keys = key.split(" ");
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

        //合并-start
        foodQueryData.setData(totalFoodQueryList);
        totalQueryData.add(foodQueryData);

        tagQueryData.setData(totalTagQueryList);
        totalQueryData.add(tagQueryData);
        //合并-end

        return resultUtils.generateKeysResult(totalQueryData);
    }

    @RequestMapping(value = "/queryUserCollectionFood", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryUserCollectionFood(@RequestParam("userId") String userId,
                           @RequestParam("startPage")int startPage,
                           @RequestParam("pageSize")int pageSize) {
        List<Food> foods = foodService.queryUserCollectionFood(userId,startPage,pageSize);
        return resultUtils.generateFoodResult(foods);
    }

    @RequestMapping(value = "/queryUserRecommendFood", method = RequestMethod.GET, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryUserRecommendFood(@RequestParam("userId") String userId,
                           @RequestParam("startPage")int startPage,
                           @RequestParam("pageSize")int pageSize) {
        List<Food> foods = foodService.queryUserRecommendFood(userId,startPage,pageSize);
        return resultUtils.generateFoodResult(foods);
    }
}
