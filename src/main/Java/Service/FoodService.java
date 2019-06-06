package Service;

import Entity.Food;
import Entity.FoodDetails;
import Entity.KeySearchResult;

import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/2/20 13:21
 * @Description TODO
 **/
public interface FoodService {

    List<Food> queryLatestFood(int startPage, int endPage);

    String[] queryFoodImageUrls(String id);

    List<Food> queryRankWeekFood(int startPage, int pageSize);

    int queryFoodRecommendNumber(String id);

    List<Food> queryRankMonthFood(int startPage, int pageNumber);

    int insertTag(String tag);

    int insertTags(List<String> tags);

    int insertTagRelationship(String foodId, List<String> tags);

    int insertFood(Food food);

    List<KeySearchResult> queryByKey(int startPage, int pageSize, String keys);

    List<KeySearchResult> queryByKeys(int startPage, int pageSize, List<String> keyList);

    List<KeySearchResult> queryInfoByKeys(List<String> options);

    int insertImages(String foodId, List<String> imagesPath);

    List<String> queryTags(List<Integer> tags);

    List<Integer> queryTagRelationShip(String foodId);

    Food queryOneById(String foodId);

    List<Food> queryNearFood(int cityCode, int startPage, int pageSize);

    List<Food> queryCommunityFood(int startPage, int pageSize);

    List<Food> queryUserCollectionFood(String userId, int startPage, int pageSize);

    List<Food> queryUserRecommendFood(String userId, int startPage, int pageSize);
}
