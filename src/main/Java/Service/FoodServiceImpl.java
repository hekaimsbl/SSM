package Service;

import Dao.FoodMapper;
import Entity.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @Author Hekai
 * @Date 2019/2/20 13:21
 * @Description TODO
 **/
@Service
public class FoodServiceImpl implements FoodService{
    @Autowired
    FoodMapper foodMapper;

    public int insertFood(Food food) {
        return foodMapper.insertFood(food);
    }

    public int updateTIme(Timestamp ts) {
        return foodMapper.updateTime(ts);
    }

    public ArrayList<Food> queryFoodByLatestTime() {
        return foodMapper.queryFoodByLatestTime();
    }

    public Food queryFoodDetailById(String id) {
        return foodMapper.queryFoodDetailById(id);
    }
}
