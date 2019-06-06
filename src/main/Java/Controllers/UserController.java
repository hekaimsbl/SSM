package Controllers;

import Entity.Food;
import Entity.User;
import Service.UserService;
import Service.UserServiceImpl;
import Utils.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @Author Hekai
 * @Date 2019/2/27 15:02
 * @Description TODO
 **/
@Controller
@RequestMapping(value = "/api/user")
public class UserController {


    @Autowired
    private UserService userService = new UserServiceImpl();

    private ResultUtils resultUtils = new ResultUtils();

    @RequestMapping(value = "/alipay/getUserInfo")
    @ResponseBody
    public String getUserInfoAlipay() {
        //初始化
        /*AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.SANDBOX_URL,
                AlipayConfig.APPID,
                AlipayConfig.APP_PRIVATE_KEY,
                AlipayConfig.FORMAT,
                AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.SIGN_TYPE);*/
        return "hello world";
    }

    @RequestMapping(value = "/insertRecommend", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String insertRecommend(@RequestParam("userId") String userId,
                                  @RequestParam("foodId") String foodId) {
        Date date = TimeUtil.getSqlTime();
        int result = userService.insertRecommend(foodId, userId, date);
        return resultUtils.generateOpretorResult(result);
    }

    @RequestMapping(value = "/deleteRecommend", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String deleteRecommend(@RequestParam("userId") String userId,
                                  @RequestParam("foodId") String foodId) {
        int result = userService.deleteRecommend(foodId, userId);
        return resultUtils.generateOpretorResult(result);
    }

    @DeleteMapping(value = "/collection",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String deleteCollection(@RequestParam("userId") String userId,
                                   @RequestParam("foodId") String foodId) {
        int result = userService.deleteCollection(foodId, userId);
        return resultUtils.generateOpretorResult(result);
    }

    @PostMapping(value = "/addCollection", produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String addCollection(@RequestParam("userId") String userId,
                                @RequestParam("foodId") String foodId) {
        int result = userService.addCollection(foodId, userId);
        return resultUtils.generateOpretorResult(result);
    }

    @GetMapping(value = "/addUser",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String addUser(@RequestParam("userInfo") String userInfo){
        Gson gson = new Gson();
        User user = gson.fromJson(userInfo,User.class);
        user.setId(IDGenerate.getGuid());
        user.setCreateTime(TimeUtil.getSqlTime());
        int result = userService.addUser(user);
        if (result > 0){
            return resultUtils.generateFoodResult(user);
        }
        return resultUtils.generateOpretorResult(result);
    }

    @PutMapping(value = "/updateUserName/{userId}",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String updateUserName(@PathVariable String userId,
                                 @RequestParam("userName") String userName){
       int result  = userService.updateUserName(userId,userName);
       return resultUtils.generateOpretorResult(result);
    }


    @PutMapping(value = "/updateUserHeadImage/{userId}",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String updateUserHeadImage(@RequestParam(value = "userId", required = true) String userId,
                                      @RequestParam(value = "headImage") CommonsMultipartFile pictures) {
        String sqlPath = null;
        String localPath = "D:\\Android\\XunwImages\\";

        String contentType = pictures.getContentType();
        String suffixName = contentType.substring(contentType.indexOf("/") + 1);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String filename = uuid + "." + suffixName;

        try {
            pictures.transferTo(new File(localPath + filename));
        } catch (IllegalStateException el) {
            el.printStackTrace();
        } catch (IOException el) {
            el.printStackTrace();
        }
        sqlPath = "/images/" + filename;


        int  result = userService.updateHeadImage(userId, sqlPath);

        return resultUtils.generateOpretorResult(result);
    }

    @GetMapping(value = "/queryUserById",produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String queryUserById(@RequestParam("userId") String userId){
        User user = userService.queryUserById(userId);
        return resultUtils.generateFoodResult(user);
    }
}
