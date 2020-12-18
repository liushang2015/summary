package com.liushang.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("mayikt_user")
public class MayiktUser {
    private Integer id;
    private String name;
    private Integer age;


    public MayiktUser(Integer id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
