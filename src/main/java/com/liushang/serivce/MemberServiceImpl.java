package com.liushang.serivce;

;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liushang.entity.UserEntity;
import com.liushang.mapper.UserMapper;
import com.liushang.transactional.LSTransactional;
import com.liushang.utils.TransactionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiPredicate;


/**
 * @ClassName MemberService
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/
@RestController
public class MemberServiceImpl {
    Map<String, String> map = new ConcurrentHashMap<>();
    @Autowired
    public UserMapper userMapper;

    @Autowired
    private TransactionUtils transactionUtils;

    /**
     * findByUser userId
     * http://localhost:8080/findByUserId?userId=1
     * @param userId
     * @return
     */
    @GetMapping("/findByUserId")
    public UserEntity findByUser(Integer userId) {
        UserEntity userEntity = userMapper.selectById(userId);

        return userEntity;
    }


    /**
     * 多条件查询
     *
     * @return
     */
    @GetMapping("/findByUserEntityList")
    public List<UserEntity> findByUserEntityList(UserEntity userEntity, Integer startAge, Integer endAge
            , String userIds) {
        // 拼接查询条件的sql语句
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>();
        queryWrapper.eq(StringUtils.isNoneEmpty(userEntity.getUserName()), "user_name", userEntity.getUserName());
        //condition  是否需要拼接条件参数
        queryWrapper.gt(userEntity.getUserAge() != null, "user_age", userEntity.getUserAge());
        queryWrapper.between(startAge != null & endAge != null, "user_age", startAge, endAge);
        queryWrapper.like(StringUtils.isNoneEmpty(userEntity.getUserAddres()), "user_addres", userEntity.getUserAddres());
        //in(18,20,20) 18,22,23

        queryWrapper.in(StringUtils.isNoneEmpty(userIds), "user_id",
                StringUtils.isNoneEmpty(userIds) ? userIds.split(",") :
                        null);


        // user_name ?  and user_Age>10
        queryWrapper.orderByDesc("user_age");
        List<UserEntity> userList = userMapper.selectList(queryWrapper);
        return userList;
    }


    /**
     *        HttpServletRequest request =
     *                     ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
     */
    /**
     * 增加对象 注意默认的情况下 主键是不会自动增长。
     * http://localhost:8080/insertUser?userName=liushang&userAge=1&userAddres=123456
     * @param userEntity
     * @return
     */
    @GetMapping("/insertUser")
    @Transactional
    @LSTransactional
    public String insertUser(UserEntity userEntity) {
//        try {
        int  insert= userMapper.insert(userEntity);
//            int j = userEntity.getUserAge()/0;
              //从当前线程中获取到request
            HttpServletRequest request =
                          ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            // 事务会失效
//        } catch (Exception e) {
//            e.printStackTrace();
////            // 手动回滚事务
////            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//        }
        return insert > 0 ? "success" : "fail";
    }

    /**
     * 逻辑删除 标记 0（存在）,1（隐藏）
     * <p>
     * SELECT  z在SQL语句后面加上 delete=0
     */

    @GetMapping("/deleteUser")
    public String deleteUser(Long userId) {
        // update set delete=1 where userId=userId

        return userMapper.deleteById(userId) > 0 ? "success" : "fail";
    }

    /**
     * 根据主键id实现修改
     *
     * @param userEntity
     * @return
     */
    @GetMapping("/updateUser")
    public synchronized String updateUser(UserEntity userEntity) {
        TransactionStatus begin = transactionUtils.begin();
        // 行锁的机制
        int updatesult = userMapper.updateById(userEntity);
        // 行锁机制如何
//        transactionUtils.commit(begin);
        return updatesult > 0 ? "success" : "fail";
    }

    /**
     * 根据条件修改
     *
     * @param userEntity
     * @return
     */
    @GetMapping("/updateUserWrapper")
    public synchronized String updateUserWrapper(UserEntity userEntity) {
        UpdateWrapper<UserEntity> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq(StringUtils.isNoneEmpty(userEntity.getUserName()), "user_name", userEntity.getUserName());
        return userMapper.update(userEntity, updateWrapper) > 0 ? "success" : "fail";
    }


    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping("/listUser")
    public List<UserEntity> listUser(Page<UserEntity> page) {
        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<>();
        Page<UserEntity> pagelist = userMapper.selectPage(page, userEntityQueryWrapper);
        return pagelist.getRecords();
    }


    /**
     * 乐观锁
     *
     * @return
     */
    @GetMapping("/optimisticLockUser")
    public String optimisticLock(UserEntity userEntity) {
        Long userId = userEntity.getUserId();
        // 标记该线程是否修改成功
        Integer resultCount = 0; //cas 灵活控制超时
        while (resultCount <= 0) {
            // 1.根据userid 查找到对应的VERION版本号码 获取当前数据的版本号码 VERION=1
            UserEntity dbUserEntity = userMapper.selectById(userId);
            if (dbUserEntity == null) {
                return "未查询到该用户";
            }
            // 2.做update操作的时候，放入该版本号码  乐观锁
            userEntity.setVersion(dbUserEntity.getVersion());
            resultCount = userMapper.updateById(userEntity);
        }
        return resultCount > 0 ? "success" : "fail";
    }

    // 客户端向服务器端发送请求 tomcat线程 乐观锁、修改mysql数据悲观

    @RequestMapping("/listUserAllEq")
    public List<UserEntity> listUserAllEq(UserEntity userEntity) {

        QueryWrapper<UserEntity> userEntityQueryWrapper = new QueryWrapper<UserEntity>();
        HashMap<String, String> paramsMap = new HashMap<>();
        BiPredicate<String, String> biPredicate = new BiPredicate<String, String>() {
            @Override
            public boolean test(String s, String o) {
                Boolean result = s.indexOf(o) > 0;

                return result;
            }
        };
        paramsMap.put("user_name", userEntity.getUserName());
        biPredicate.test(userEntity.getUserName(), "mayikt");
        userEntityQueryWrapper.allEq(biPredicate, paramsMap);
        List<UserEntity> userEntities = userMapper.selectList(userEntityQueryWrapper);
        return userEntities;
    }

}
