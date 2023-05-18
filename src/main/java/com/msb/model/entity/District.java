package com.msb.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-05-18 18:56
 */
@TableName("t_district")
@Data
public class District {

    @TableId(type = IdType.ASSIGN_ID) // 指定
    private Long id;

    private String districtName;

    private int level;

}
