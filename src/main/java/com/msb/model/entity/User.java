package com.msb.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-05-18 13:49
 */
@TableName("users")
@Data
@ToString
public class User {

    @TableId
    private long id;

    private String username;

    private String phone;

    private String password;

}
