package com.msb.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-05-18 14:41
 */
@TableName("t_course")
@Data
@ToString
public class Course implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long cid;

    private Long userId;

    private Long corderNo;

    private String cname;

    private String brief; // 简短

    private double price;

    private int status;

}
