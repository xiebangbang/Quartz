package cn.finedo.mapper;

import cn.finedo.domain.User;

public interface UserMapper {

    //查询一个实体通过姓名
    User getUserByName(String name);

}
