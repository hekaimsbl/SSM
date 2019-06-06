package Utils;

import Entity.Food;
import Entity.KeySearchResult;
import Entity.QueryData;
import Service.FoodService;
import Service.FoodServiceImpl;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/3/26 17:37
 * @Description TODO
 **/
public class ResultUtils<T> {
    public ResultUtils() {

    }

    /*public static String generateFoodResult(List<Food> foods){
        Gson gson = new GsonBuilder().serializeNulls().create();
        FoodService foodService = new FoodServiceImpl();
        ApiResult<List<Food>> result = new ApiResult<List<Food>>();
        if (foods != null) {
            result.setStatus(Message.RESULT_OK);
            result.setData(foods);
            return gson.toJson(result);
        }else {
            result.setStatus(Message.RESULT_NOT_FOUND);
            return gson.toJson(result);
        }
    }*/

    public String generateFoodResult(T data) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        ApiResult<T> result = new ApiResult<T>();
        if (data != null) {
            result.setStatus(Message.RESULT_OK);
            result.setData(data);
            return gson.toJson(result);
        } else {
            result.setStatus(Message.RESULT_NOT_FOUND);
            return gson.toJson(result);
        }
    }

    public String generateOpretorResult(int result) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        ApiResult<String> resultToReturn = new ApiResult<String>();
        if (result > 0) {
            resultToReturn.setStatus(Message.RESULT_OK);
            resultToReturn.setData("");
            return gson.toJson(result);
        } else {
            resultToReturn.setStatus(Message.RESULT_NOT_FOUND);
            return gson.toJson(result);
        }
    }

    public static String generateFoodResult(Food food) {
        Gson gson = new Gson();
        FoodService foodService = new FoodServiceImpl();
        ApiResult<Food> result = new ApiResult<Food>();
        if (food != null) {
            result.setStatus(Message.RESULT_OK);
            result.setData(food);
            return gson.toJson(result);
        } else {
            result.setStatus(Message.RESULT_NOT_FOUND);
            return gson.toJson(result);
        }
    }

    public static String generateKeysResult(List<QueryData<List<KeySearchResult>>> queryResultList) {
        Gson gson = new Gson();
        ApiResult<List<QueryData<List<KeySearchResult>>>> result = new ApiResult<List<QueryData<List<KeySearchResult>>>>();
        if (queryResultList != null) {
            result.setStatus(Message.RESULT_OK);
            result.setData(queryResultList);
            return gson.toJson(result);
        } else {
            result.setStatus(Message.RESULT_NOT_FOUND);
            return gson.toJson(result);
        }
    }
}
