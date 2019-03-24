package Controllers;

import Service.UserService;
import Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping(value = "/alipay/getUserInfo")
    @ResponseBody
    public String getUserInfoAlipay(){

        return "hello world";
    }
}
