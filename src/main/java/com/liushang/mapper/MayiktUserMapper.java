package com.liushang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liushang.entity.MayiktUser;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MayiktUserMapper extends BaseMapper<MayiktUser> {
    /**
     * 查询所有
     *
     * @return
     */
    @Select("SELECT * FROM mayikt_user")
    List<MayiktUser> userList();

    /**
     * 分页查询
     *
     * @return
     */
    @Select("SELECT * FROM mayikt_user limit 0,2")
    List<MayiktUser> userListPage();

    /**
     * 排序
     *
     * @return
     */
    @Select("SELECT * FROM mayikt_user order by id desc ")
    List<MayiktUser> userOrderBy();

    /**
     * get by id
     *
     * @return
     */
    @Select("SELECT * FROM mayikt_user where id =#{id} ")
    List<MayiktUser> getByUserId(Integer id);
}
