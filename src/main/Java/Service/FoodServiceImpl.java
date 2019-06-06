package Service;

import Dao.FoodMapper;
import Entity.Food;
import Entity.KeySearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/2/20 13:21
 * @Description TODO
 **/
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    FoodMapper foodMapper;
    private Food food;

    public List<Food> queryLatestFood(int startPage, int endPage) {
        return foodMapper.queryLatestFood(startPage, endPage);
    }

    public String[] queryFoodImageUrls(String id) {
        return foodMapper.queryFoodImageUrls(id);
    }

    public List<Food> queryRankWeekFood(int startPage, int pageSize) {
        return foodMapper.queryRankWeekFood(startPage, pageSize);
    }

    public int queryFoodRecommendNumber(String id) {
        return foodMapper.queryFoodRecommendNumber(id);
    }

    public List<Food> queryRankMonthFood(int startPage, int pageNumber) {
        return foodMapper.queryRankMonthFood(startPage, pageNumber);
    }

    public int insertTag(String tag) {
        return foodMapper.insertTag(tag);
    }

    public int insertTags(List<String> tags) {
        return foodMapper.insertTags(tags);
    }

    public int insertTagRelationship(String foodId, List<String> tags) {
        return foodMapper.insertTagRelationship(foodId, tags);
    }

    public int insertFood(Food food) {
        return foodMapper.insertFood(food);
    }

    public List<KeySearchResult> queryByKey(int startPage, int pageSize, String keys) {
        return foodMapper.queryByKey(startPage, pageSize, keys);
    }

    public List<KeySearchResult> queryByKeys(int startPage, int pageSize, List<String> keyList) {
        return foodMapper.queryByKeys(startPage, pageSize, keyList);
    }

    public List<KeySearchResult> queryInfoByKeys(List<String> options) {
        return foodMapper.queryInfoByKeys(options);
    }

    public int insertImages(String foodId, List<String> imagesPath) {
        return foodMapper.insertImages(foodId, imagesPath);
    }

    public List<String> queryTags(List<Integer> tags) {
        return foodMapper.queryTags(tags);
    }

    public List<Integer> queryTagRelationShip(String foodId) {
        return foodMapper.queryTagRelationShip(foodId);
    }

    public Food queryOneById(String foodId) {
        return foodMapper.queryOneById(foodId);
    }

    public List<Food> queryNearFood(int cityCode, int startPage, int pageSize) {
        return foodMapper.queryNearFood(cityCode,startPage,pageSize);
    }

    public List<Food> queryCommunityFood(int startPage, int pageSize) {
        return foodMapper.queryCommunityFood(startPage,pageSize);
    }

    public List<Food> queryUserCollectionFood(String userId, int startPage, int pageSize) {
        return foodMapper.queryUserCollectionFood(userId,startPage,pageSize);
    }

    public List<Food> queryUserRecommendFood(String userId, int startPage, int pageSize) {
        return foodMapper.queryUserRecommendFood(userId,startPage,pageSize);
    }
}
