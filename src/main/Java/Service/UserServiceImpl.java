package Service;

import Dao.UserMapper;
import Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author Hekai
 * @Date 2019/2/27 15:04
 * @Description TODO
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public int insertUser(User user) {
        return userMapper.insertUser(user);
    }

    public int insertRecommend(String food_id, String user_id, Date date) {
        return userMapper.insertRecommend(food_id, user_id, date);
    }

    public int deleteRecommend(String food_id, String user_id) {
        return userMapper.deleteRecommend(food_id, user_id);
    }

    public int insertFollow(String user_id, String from_id, Date date) {
        return userMapper.insertFollow(user_id, from_id, date);
    }

    public int deleteFollow(String user_id, String from_id) {
        return userMapper.deleteFollow(user_id, from_id);
    }

    public List<KeySearchResult> queryKeys(int startPage, int pageSize, List<String> keyList) {
        return userMapper.queryKeys(startPage, pageSize, keyList);
    }

    public List<KeySearchResult> queryKey(int startPage, int pageSize, String key) {
        return userMapper.queryKey(startPage, pageSize, key);
    }

    public String queryUserNameById(String authorId) {
        return userMapper.queryUserNameById(authorId);
    }

    public int deleteCollection(String foodId, String userId) {
        return userMapper.deleteCollection(foodId,userId);
    }

    public int addCollection(String foodId, String userId) {
        return userMapper.addCollection(foodId,userId);
    }

    public int addUser(User user) {
        return userMapper.addUser(user);
    }

    public int updateUserName(String userId, String userName) {
        return userMapper.updateUserName(userId,userName);
    }

    public int updateHeadImage(String userId, String sqlPath) {
        return userMapper.updateUserHeadImage(userId,sqlPath);
    }

    public User queryUserById(String userId) {
        return userMapper.queryUserById(userId);
    }
}
