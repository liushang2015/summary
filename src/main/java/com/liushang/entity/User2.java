package com.liushang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("User2")
public class User2 {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    private String name;
}
