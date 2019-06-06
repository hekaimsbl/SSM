package Service;

import Entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/2/27 15:03
 * @Description TODO
 **/
public interface UserService {

    int insertUser(User user);

    int insertRecommend(String food_id, String user_id, Date date);

    int deleteRecommend(String food_id, String user_id);

    int insertFollow(String user_id, String from_id, Date date);

    int deleteFollow(String user_id, String from_id);

    List<KeySearchResult> queryKeys(int startPage, int pageSize, List<String> keyList);

    List<KeySearchResult> queryKey(int startPage, int pageSize, String key);

    String queryUserNameById(String authorId);

    int deleteCollection(String foodId, String userId);

    int addCollection(String foodId, String userId);

    int addUser(@Param("user") User user);

    int updateUserName(String userId, String userName);

    int updateHeadImage(String userId, String sqlPath);

    User queryUserById(String userId);
}
