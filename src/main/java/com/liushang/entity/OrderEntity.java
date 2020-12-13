package com.liushang.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @ClassName OrderEntity
 * @Author 蚂蚁课堂余胜军 QQ644064779 www.mayikt.com
 * @Version V1.0
 **/

@TableName("order")
@Data
public class OrderEntity {
    @TableId(value = "order_id")
    private Long orderId;
    private String orderName;
}
