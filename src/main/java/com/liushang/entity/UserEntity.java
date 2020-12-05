package com.liushang.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

//@NoArgsConstructor
@Data
public class UserEntity {
    private String userName;
    private Integer userId;


    public UserEntity(String userName, Integer userId) {
        this.userName = userName;
        this.userId = userId;
    }

    //    // 重写equals方法比较 对象中参数内容值是否相等
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        UserEntity user = (UserEntity) obj;
//        return user.userName.equals(userName) && user.userId.equals(userId);
//    }
//
//
//    @Override
//    public int hashCode() {
//        return userName.hashCode() + userId.hashCode();
//    }

    public static void main(String[] args) {
        UserEntity entity = new UserEntity("慕然",1);
        Map<UserEntity,Integer> map = new HashMap<>();
        map.put(entity,1);
        System.out.println("hashcode1:"+entity.hashCode());
        UserEntity entity1 = new UserEntity("慕然",1);
        System.out.println("hashcode2:"+entity1.hashCode());
        System.out.println(map.get(entity1));
    }
}
