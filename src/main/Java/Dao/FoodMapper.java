package Dao;

import Entity.Food;
import Entity.KeySearchResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author Hekai
 * @Date 2019/2/20 13:19
 * @Description TODO
 **/
public interface FoodMapper {
    List<Food> queryLatestFood(@Param("startPage") int startPage, @Param("pageSize") int pageSize);

    String[] queryFoodImageUrls(@Param("id") String id);

    List<Food> queryRankWeekFood(@Param("startPage") int startPage, @Param("pageSize") int pageSize);

    int queryFoodRecommendNumber(@Param("foodId") String id);

    List<Food> queryRankMonthFood(@Param("startPage") int startPage, @Param("pageNumber") int pageNumber);

    @Insert("INSERT IGNORE INTO t_tag (tag_name) VALUES (#{tag})")
    int insertTag(@Param("tag") String tag);

    int insertTags(List<String> tags);

    int insertTagRelationship(@Param("foodId") String foodId, @Param("tags") List<String> tags);

    int insertFood(@Param("food") Food food);

    List<KeySearchResult> queryByKey(@Param("startPage") int startPage, @Param("pageSize") int pageSize, @Param("keys") String keys);

    List<KeySearchResult> queryByKeys(@Param("startPage") int startPage, @Param("pageSize") int pageSize, @Param("keyList") List<String> keyList);

    List<KeySearchResult> queryInfoByKeys(@Param("options") List<String> options);

    int insertImages(@Param("foodId") String foodId, @Param("images") List<String> imagesPath);

    List<String> queryTags(@Param("tagsId") List<Integer> tags);

    @Select("SELECT tag_id FROM t_relationship_food_tag WHERE food_id = #{foodId}")
    List<Integer> queryTagRelationShip(@Param("foodId") String foodId);

    Food queryOneById(@Param("foodId") String foodId);

    List<Food> queryNearFood(@Param("cityCode") int cityCode,@Param("startPage") int startPage,@Param("pageSize") int pageSize);

    List<Food> queryCommunityFood(@Param("startPage") int startPage,@Param("pageSize") int pageSize);

    List<Food> queryUserCollectionFood(@Param("userId") String userId,@Param("startPage") int startPage,@Param("pageSize") int pageSize);

    List<Food> queryUserRecommendFood(String userId, int startPage, int pageSize);
}
