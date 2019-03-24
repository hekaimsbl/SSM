package Service;

import Entity.Food;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @Author Hekai
 * @Date 2019/2/20 13:21
 * @Description TODO
 **/
public interface FoodService {
    int insertFood(Food food);

    int updateTIme(Timestamp ts);

    ArrayList<Food> queryFoodByLatestTime();

    Food queryFoodDetailById(String id);
}
