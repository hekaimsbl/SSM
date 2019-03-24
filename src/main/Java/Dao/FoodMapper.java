package Dao;

import Entity.Food;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @Author Hekai
 * @Date 2019/2/20 13:19
 * @Description TODO
 **/
public interface FoodMapper {
    int updateTime(Timestamp ts);

    int insertFood(Food food);

    ArrayList<Food> queryFoodByLatestTime();

    Food queryFoodDetailById(String id);
}
