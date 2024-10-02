package cn.itheima.quartz.Controller;

import cn.itheima.quartz.bean.User;
import cn.itheima.quartz.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class HelloController {

    @Autowired
    UserMapper userMapper;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){

        User user = userMapper.getUserByName("李四");
        return "hello "+ user.toString();
    }
}
