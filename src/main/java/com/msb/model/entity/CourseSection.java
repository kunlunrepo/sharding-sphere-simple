package com.msb.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-05-18 16:45
 */
@TableName("t_course_section")
@Data
@ToString
public class CourseSection {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long cid;

    private Long corderNo;

    private Long userId;

    private String sectionName;

    private int status;

}
