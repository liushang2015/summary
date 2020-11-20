package com.liushang.entity;

public class UserEntity {
    private String userName;
    private Integer userId;

    public UserEntity(String userName, Integer userId) {
        this.userName = userName;
        this.userId = userId;
    }

    //    // 重写equals方法比较 对象中参数内容值是否相等
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        UserEntity user = (UserEntity) obj;
        return user.userName.equals(userName) && user.userId.equals(userId);
    }

    //
    @Override
    public int hashCode() {
        return userName.hashCode() + userId.hashCode();
    }
}
