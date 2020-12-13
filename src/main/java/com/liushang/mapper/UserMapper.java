package com.liushang.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liushang.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;


/**
 * @ClassName UserMapper
 * @Author
 * @Version V1.0
 **/
public interface UserMapper extends BaseMapper<UserEntity> {

    @Insert("INSERT INTO `mayikt_user` VALUES (null,#{userName}, #{userInfo}, #{phone});")
    void insertUser(@Param("userName") String userName,
                    @Param("userInfo") String userInfo,
                    @Param("phone") String phone);
}
