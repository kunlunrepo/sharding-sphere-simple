package com.msb.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-05-18 13:45
 */
@TableName("pay_order")
@Data
@ToString
public class PayOrder {

    @TableId
    private long order_id;

    private long user_id;

    private String product_name;

    private int count;

}
