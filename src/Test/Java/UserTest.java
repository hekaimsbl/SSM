import Entity.CommentLike;
import Entity.Reply;
import Entity.User;
import Service.UserService;
import Service.UserServiceImpl;
import Utils.IDGenerate;
import Utils.Print;
import Utils.ResultUtils;
import Utils.TimeUtil;
import com.google.gson.Gson;
import com.google.gson.annotations.Since;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.Date;

/**
 * @Author Hekai
 * @Date 2019/4/3 11:34
 * @Description TODO
 **/
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
//使用标准的JUnit @RunWith注释来告诉JUnit使用Spring TestRunner
@RunWith(SpringJUnit4ClassRunner.class)
public class UserTest {
    @Autowired
    private UserService userService = new UserServiceImpl();

    ResultUtils resultUtils = new ResultUtils();

    /**
     * 推荐插入功能
     */
    @org.junit.Test
    public void insertRecommendTest() {
        String food_id = "20191554260951620101";
        String user_id = "20191554260534017102";
        Date date = TimeUtil.getSqlTime();
        int result = userService.insertRecommend(food_id, user_id, date);
        Print.msg(result);
    }

    /**
     * 取消推荐
     */
    @org.junit.Test
    public void deleteRecommendTest() {
        String food_id = "20191554260951620101";
        String user_id = "20191554260534017102";
        int result = userService.deleteRecommend(food_id, user_id);
        Print.msg(result);
    }

    /**
     * 添加关注
     */
    @org.junit.Test
    public void insertFollow() {
        /*被订阅id*/
        String user_id = "20191554260534017102";
        /*订阅者id*/
        String from_id = "20191554260795089102";
        Date date = TimeUtil.getSqlTime();
        int result = userService.insertFollow(user_id, from_id, date);
        Print.msg(result);
    }

    /**
     * 取消关注
     */
    @org.junit.Test
    public void deleteFollow() {
        /*被订阅id*/
        String user_id = "20191554260534017102";
        /*订阅者id*/
        String from_id = "20191554260795089102";
        int result = userService.deleteFollow(user_id, from_id);
        Print.msg(result);
    }


    @org.junit.Test
    public void addUser() {
        User user = new User();
        user.setImgHead("hello");
        user.setCreateTime(TimeUtil.getSqlTime());
        user.setName("hellow wored");
        user.setId(IDGenerate.getGuid());
        user.setEmail("this is email");

        int result = userService.addUser(user);
        Print.msg("result" + result);

        /*Gson gson = new Gson();
        String str = gson.toJson(user);
        Print.msg(str);

        User user2 = new User();

        user2 = gson.fromJson(str,User.class);
        Print.msg("user2:" + user2.getEmail());*/
    }

    @org.junit.Test
    public void queryUserNameById() {
        String userId = "20191554260534017102";
        int commentId = 1;
        String result = userService.queryUserNameById(userId);
        Print.msg(result);
    }

    @org.junit.Test
    public void deleteCollection() {
        String userId = "20191554260534017102";
        String foodId = "20191554260951620101";
        int result = userService.deleteCollection(foodId,userId);
        Print.msg(resultUtils.generateOpretorResult(result));
    }

    @org.junit.Test
    public void addCollection() {
        String userId = "20191554260534017102";
        String foodId = "20191554260951620101";
        int result = userService.addCollection(foodId,userId);
        Print.msg(resultUtils.generateOpretorResult(result));
    }

    @org.junit.Test
    public void updateUserName() {
        String userId = "20191554260795089102";
        String userName = "hello user";
        int result = userService.updateUserName(userId,userName);
        Print.msg(resultUtils.generateOpretorResult(result));
    }
    @org.junit.Test
    public void queryUserById() {
        String userId = "20191559310987286101";
        User user = userService.queryUserById(userId);
        Print.msg(resultUtils.generateFoodResult(user));
    }
}
