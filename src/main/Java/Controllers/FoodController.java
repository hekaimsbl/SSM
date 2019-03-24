package Controllers;

import Entity.Food;
import Service.FoodService;
import Service.FoodServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.omg.Dynamic.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

/**
 * The type Food controller.
 */
@Controller
@RequestMapping("/api/Food")
public class FoodController {

    @Autowired
    private FoodService foodService = new FoodServiceImpl();

    /**
     * Json test string.
     *
     * @return the string
     */
    @ResponseBody
    @RequestMapping(value = "/Test", method = RequestMethod.GET)
    public String JsonTest() {
        String a = "FoodTest";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", a);
        return jsonObject.toString();
    }

    /**
     * Json test 2 string.
     *
     * @param id the id
     * @return the string
     */
    @RequestMapping(value = "/Test2/{id}")
    @ResponseBody
    public String JsonTest2(@PathVariable("id") Integer id) {
        JSONObject json = new JSONObject();
        json.put("id", "id" + id);
        return json.toString();
    }

    /**
     * 最新发布的美食，时间降序
     */
    @RequestMapping(value = "latestFood")
    @ResponseBody
    public String getLatestFood() {
        ArrayList<Food> foods = new ArrayList<Food>();
        return "null";
    }

    @RequestMapping(value = "/addFood", method = RequestMethod.POST)
    @ResponseBody
    public String addFood(Parameter food) {

        return "null";
    }

    @RequestMapping(value = "/queryFoodByLatestTime", method = RequestMethod.GET)
    @ResponseBody
    public String queryFoodByLatestTime(){
        JsonObject jsonObject = new JsonObject();
        Gson gson = new Gson();

        ArrayList<Food> foods = foodService.queryFoodByLatestTime();
        if (null != foods){
            JsonArray jsonElements = gson.toJsonTree(foods).getAsJsonArray();
            jsonObject.addProperty("status","success");
            jsonObject.add("foods",jsonElements);
        }else {
            jsonObject.addProperty("status","failed");
        }
        return gson.toJson(jsonObject);
    }

    @RequestMapping(value = "/queryFoodDetailById/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String queryFoodDetailById(@PathVariable("id") String id){
        Food food = foodService.queryFoodDetailById(id);
        return "";
    }
}
