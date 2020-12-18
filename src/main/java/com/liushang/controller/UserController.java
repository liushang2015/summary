package com.liushang.controller;

import com.liushang.entity.MayiktUser;
import com.liushang.mapper.MayiktUserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private MayiktUserMapper mayiktUserMapper;

    @RequestMapping(value = "/insertUser" , method=RequestMethod.POST)
    public String insertUser() {
        for (int i = 1; i < 10; i++) {
            MayiktUser mayiktUser = new MayiktUser(i, "mayikt" + i, i);
            try {
                mayiktUserMapper.insert(mayiktUser);
            } catch (Exception e) {

            }

        }
        return "success";
    }

    /**
     * 查询所有
     *
     * @return
     */
    @RequestMapping(value = "/userList", method= RequestMethod.GET)
    public List<MayiktUser> userList() {
        return mayiktUserMapper.userList();
    }

    /**
     * 分页查询
     *
     * @return
     */
    @RequestMapping(value = "/userListPage",method= RequestMethod.GET)
    public List<MayiktUser> userListPage() {
        return mayiktUserMapper.userListPage();
    }

    /**
     * 排序
     *
     * @return
     */
    @RequestMapping(value = "/userOrderBy",method = RequestMethod.GET)
    public List<MayiktUser> userOrderBy() {
        return mayiktUserMapper.userOrderBy();
    }

    @RequestMapping(value="/getByUserId/{id}", method=RequestMethod.GET)
    public List<MayiktUser> getByUserId(@PathVariable("id")Integer id) {
        return mayiktUserMapper.getByUserId(id);
    }
}
