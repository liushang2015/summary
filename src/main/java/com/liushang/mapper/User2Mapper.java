package com.liushang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liushang.entity.User2;
import org.apache.ibatis.annotations.Insert;

public interface User2Mapper extends BaseMapper<User2> {
    @Insert("INSERT INTO `user2` VALUES (null,#{name});")
    int insert(User2 record);
    User2 selectByPrimaryKey(Integer id);
}
