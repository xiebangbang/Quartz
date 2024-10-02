package cn.itheima.quartz.Controller;

import cn.itheima.quartz.bean.User;
import cn.itheima.quartz.job.HelloJob;
import cn.itheima.quartz.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class HelloController {

    private static final Logger logger = LoggerFactory.getLogger(HelloJob.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisTemplate redisTemplate;

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        User user = userMapper.getUserByName("李四");
        redisTemplate.opsForValue().set("name",user.getName());
        redisTemplate.opsForHash().put("user","name",user.getName());
        redisTemplate.opsForHash().put("user","age",user.getAge());
        logger.info("name:"+redisTemplate.opsForValue().get("name"));
        logger.info("user.name:"+redisTemplate.opsForHash().get("user","name"));
        logger.info("user.age:"+redisTemplate.opsForHash().get("user","age"));
        return "hello "+ user.toString();
    }
}
