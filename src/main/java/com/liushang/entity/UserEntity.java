package com.liushang.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.liushang.utils.Global;
import lombok.Data;

import java.util.Date;

//@NoArgsConstructor
@Data
@TableName("user")
public class UserEntity {
    @TableId(value = "user_id",type = IdType.AUTO)
    private Long userId;
    private String userName;
    private Integer userAge;
    private String userAddres;
    private Date createTime;

    @TableLogic
    private Integer deleted = 0;
    // 版本
    @Version
    private Integer version;

    public String getFormatCreateTime() {
        return this.createTime != null ? Global.GENERAL_DF_NOT_SS().format(this.createTime) : null;
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

//    public static void main(String[] args) {
//        UserEntity entity = new UserEntity("慕然",1);
//        Map<UserEntity,Integer> map = new HashMap<UserEntity,Integer>();
//        map.put(entity,1);
//        System.out.println("hashcode1:"+entity.hashCode());
//        UserEntity entity1 = new UserEntity("慕然",1);
//        System.out.println("hashcode2:"+entity1.hashCode());
//        System.out.println(map.get(entity1));
//    }
}
