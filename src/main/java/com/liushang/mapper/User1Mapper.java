package com.liushang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liushang.entity.User1;
import org.apache.ibatis.annotations.Insert;

public interface User1Mapper extends BaseMapper<User1> {

    @Insert("INSERT INTO `user1` VALUES (null,#{name});")
    int insert(User1 record);
    User1 selectByPrimaryKey(Integer id);
}
