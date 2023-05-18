package com.msb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.msb.model.entity.Course;
import com.msb.model.vo.CourseVo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-05-18 14:43
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    // 自定义语句 写逻辑表名即可
    @Insert("INSERT INTO t_course (user_id, cname, brief, price, status )  VALUES  (#{course.userId}, #{course.cname}, #{course.brief}, #{course.price}, #{course.status})")
    int insertCus(@Param("course")Course course);

    @Select("select c.corder_no, c.cname, count(cs.id) num from t_course c inner join t_course_section cs on c.corder_no = cs.corder_no group by c.corder_no, c.cname")
    List<CourseVo> getCourseNameAndSectionName();

}
