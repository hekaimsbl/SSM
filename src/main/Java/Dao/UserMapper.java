package Dao;

import Entity.*;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/4/3 10:30
 * @Description TODO
 **/
public interface UserMapper {
    /**
     * 添加用户
     *
     * @param user 用户实体
     * @return
     */
    int insertUser(@Param("user") User user);

    /**
     * 添加推荐关系
     *
     * @param food_id 美食id
     * @param user_id 推荐用户id
     * @param date    日期
     * @return
     */
    @Insert("INSERT INTO t_recommend (food_id,from_userId,create_time) VALUES (#{foodId},#{userId},#{date})")
    int insertRecommend(@Param("foodId") String food_id, @Param("userId") String user_id, @Param("date") Date date);

    /**
     * 删除推荐
     *
     * @param food_id 美食id
     * @param user_id 用户id
     * @return
     */
    @Delete("DELETE FROM t_recommend WHERE food_id = #{foodId} AND from_userId = #{userId}")
    int deleteRecommend(@Param("foodId") String food_id, @Param("userId") String user_id);

    /**
     * 添加关注
     *
     * @param user_id 被关注者id
     * @param from_id 关注者id
     * @return int 结果 >= 0 成功
     */
    @Insert("INSERT INTO t_follow (user_id,from_userId,create_time) VALUES (#{userId},#{fromUserId},#{createTime})")
    int insertFollow(@Param("userId") String user_id, @Param("fromUserId") String from_id, @Param("createTime") Date date);

    /**
     * 取消关注
     *
     * @param user_id 被取消者id
     * @param from_id 关注者id
     * @return
     */
    String Sql_deleteFollow = "DELETE FROM t_follow" +
            " WHERE " +
            "user_id = #{userId}" +
            " AND " +
            "from_userId = #{fromId}";

    @Delete(Sql_deleteFollow)
    int deleteFollow(@Param("userId") String user_id, @Param("fromId") String from_id);


    List<KeySearchResult> queryKeys(@Param("startPage") int startPage, @Param("pageSize") int pageSize, @Param("keyList") List<String> keyList);

    List<KeySearchResult> queryKey(@Param("startPage") int startPage, @Param("pageSize") int pageSize, @Param("key") String key);

    String Sql_selectUserNameById = "SELECT name FROM t_user WHERE id = #{id}";

    @Select(Sql_selectUserNameById)
    String queryUserNameById(@Param("id") String authorId);

    String Sql_deleteCollection = "DELETE from t_collection " +
            "WHERE " +
            "from_userId = #{userId}" +
            "AND " +
            "food_id = #{foodId}";
    @Delete(Sql_deleteCollection)
    int deleteCollection(@Param("foodId") String foodId,@Param("userId") String userId);

    String Sql_addCollection = "INSERT INTO t_collection " +
            "(food_id,from_userId)" +
            " VALUES " +
            "(#{foodId},#{userId})";
    @Insert(Sql_addCollection)
    int addCollection(@Param("foodId") String foodId,@Param("userId") String userId);

    int addUser(@Param("user") User user);

    String Sql_updateUserName = "UPDATE t_user SET " +
            "name = #{userName}" +
            " WHERE " +
            "id = #{userId}";

    @Update(Sql_updateUserName)
    int updateUserName(@Param("userId") String userId,@Param("userName") String userName);

    String Sql_updateUserHeadImage = "UPDATE t_user SET " +
            "img_head = #{sqlPath}" +
            " WHERE " +
            "id = #{userId}";
    @Update(Sql_updateUserHeadImage)
    int updateUserHeadImage(@Param("userId") String userId,@Param("sqlPath") String sqlPath);

    User queryUserById(@Param("userId") String userId);
}
