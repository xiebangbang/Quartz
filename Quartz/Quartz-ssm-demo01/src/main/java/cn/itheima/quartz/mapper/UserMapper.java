package cn.itheima.quartz.mapper;


import cn.itheima.quartz.bean.User;

public interface UserMapper {

    //查询一个实体通过姓名
    User getUserByName(String name);

}
