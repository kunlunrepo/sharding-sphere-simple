package com.msb;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.msb.mapper.*;
import com.msb.model.entity.*;
import com.msb.model.vo.CourseVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * description :
 *
 * @author kunlunrepo
 * date :  2023-05-18 14:07
 */
@SpringBootTest
public class ShardingJdbcApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PayOrderMapper payOrderMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseSectionMapper courseSectionMapper;

    @Autowired
    private DistrictMapper districtMapper;

    @Test
    public void testInsert() {

        User user = new User();
        user.setId(1002);
        user.setUsername("大远哥");
        user.setPhone("15066668888");
        user.setPassword("123456");
        userMapper.insert(user);

        PayOrder payOrder = new PayOrder();
        payOrder.setOrder_id(12345679);
        payOrder.setProduct_name("猕猴桃");
        payOrder.setUser_id(user.getId());
        payOrder.setCount(2);
        payOrderMapper.insert(payOrder);

    }


    @Test
    public void testSelect() {
        User user = userMapper.selectById(1002);
        System.out.println(user);
        PayOrder payOrder = payOrderMapper.selectById(12345679);
        System.out.println(payOrder);
    }

    @Test
    public void testInsertCourse() {
        for (int i = 0; i < 3; i++) {
            Course course = new Course();
            course.setCid(10086L+i);
            course.setUserId(1L+i);
            course.setCname("Java经典面试题讲解");
            course.setBrief("课程涵盖目前最容易被问题的10000道Java面试题");
            course.setPrice(100.0);
            course.setStatus(1);

            courseMapper.insert(course);
        }
    }

    // 水平分库
    @Test
    public void testInsertCourseDB() {
        for (int i = 0; i < 10; i++) {
            Course course = new Course();
            course.setUserId(1001L+i);
            course.setCname("Java经典面试题讲解");
            course.setBrief("课程涵盖目前最容易被问题的10000道Java面试题");
            course.setPrice(100.0);
            course.setStatus(1);

            courseMapper.insert(course);
        }
    }

    // 水平分库水平分表
    @Test
    public void testInsertCourseDBTable() {
        for (int i = 0; i < 130; i++) {
            Course course = new Course();
            course.setUserId(1001L+i);
            course.setCname("Java经典面试题讲解");
            course.setBrief("课程涵盖目前最容易被问题的10000道Java面试题");
            course.setPrice(100.0);
            course.setStatus(1);

            courseMapper.insertCus(course);
        }
    }

    // 验证分类是否正确
    @Test
    public void testHashMod() {
        Long cid = 865996729323880449L;
        int userId = 1002;
        int hash = cid.hashCode();
        System.out.println(hash);
        System.out.println("-------------"+ userId % 2);
        System.out.println("============="+Math.abs(hash % 2));
    }

    // 查询所有数据
    @Test
    public void testShardingSelectAll() {
        List<Course> courses = courseMapper.selectList(null);
        courses.forEach(System.out::println);
        System.out.println("插入总数：" + courses.size());
    }

    // 根据user_id进行查询
    @Test
    public void testSelectByUserId() {
        QueryWrapper<Course> courseQueryWrapper = new QueryWrapper<>();
        courseQueryWrapper.eq("user_id", 1075L);
        List<Course> courses = courseMapper.selectList(courseQueryWrapper);
        courses.forEach(System.out::println);
    }

    // 测试关联表插入
    @Test
    public void testInsertCourseAndCourseSection() {
        for (int i = 0; i < 3; i++) {
            Course course = new Course();
            course.setUserId(1L); // 决定库
            course.setCorderNo(1000L + i); // 决定表
            course.setPrice(100.0);
            course.setCname("ShardingSphere实战");
            course.setBrief("ShardingSphere实战-项目");
            course.setStatus(1);
            courseMapper.insert(course);

            Long cid = course.getCid(); //
            for (int j = 0; j < 3; j++) {
                CourseSection section = new CourseSection();
                section.setUserId(1L); // 决定库
                section.setCorderNo(1000L + i); // 决定表
                section.setCid(cid);
                section.setSectionName("ShardingSphere实战_" + i);
                section.setStatus(1);
                courseSectionMapper.insert(section);
            }
        }

        for (int i = 3; i < 5; i++) {
            Course course = new Course();
            course.setUserId(2L); // 决定库
            course.setCorderNo(1000L + i); // 决定表
            course.setPrice(100.0);
            course.setCname("ShardingSphere实战");
            course.setBrief("ShardingSphere实战-项目");
            course.setStatus(1);
            courseMapper.insert(course);

            Long cid = course.getCid(); //
            for (int j = 0; j < 3; j++) {
                CourseSection section = new CourseSection();
                section.setUserId(2L); // 决定库
                section.setCorderNo(1000L + i); // 决定表
                section.setCid(cid);
                section.setSectionName("ShardingSphere实战_" + i);
                section.setStatus(1);
                courseSectionMapper.insert(section);
            }
        }
    }

    // 测试关联查询
    @Test
    public void testSelectCourseNameAndSectionName() {
        List<CourseVo> list = courseMapper.getCourseNameAndSectionName();
        list.forEach(System.out::println);
        System.out.println("总数："+list.size());
    }

    // 广播表
    @Test
    public void testBroadcast() {
        District district = new District();
        district.setDistrictName("昌平区");
        district.setLevel(1);

        districtMapper.insert(district);
    }


    @Test
    public void testSelectBroadcast() {
        List<District> districts = districtMapper.selectList(null);
        districts.forEach(System.out::println);
    }

















}
