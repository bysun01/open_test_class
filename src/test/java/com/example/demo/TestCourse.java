package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.CourseVoRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.CourseService;
import com.example.demo.service.UserService;
import com.example.demo.vo.CourseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCourse {

    private CourseService courseService;
    private UserService userService;
    private CourseRepository courseRepository;
    private CourseVoRepository courseVoRepository;

    @Autowired
    public void injectBean(CourseService courseService, UserService userService, CourseRepository courseRepository, CourseVoRepository courseVoRepository) {
        this.courseService = courseService;
        this.userService = userService;
        this.courseRepository = courseRepository;
        this.courseVoRepository = courseVoRepository;
    }

    @Test
    public void saveCourse() {
        User user = userService.getUser(2L);
        Course course = new Course();
        course.setCourseName("数学课");
        course.setTeacher(user);
        course.setCreateBy(2L);
        course.setUpdateBy(2L);
        course.setCreateTime(new Date());
        course.setUpdateTime(new Date());
        course.setIsDel(1);
        course.setId(2L);
        Course course1 = courseService.saveCourse(course);
        System.out.println(JSONObject.toJSONString(course1));
    }

    @Test
    public void testListCourse() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<CourseVO> courseForSub = courseVoRepository.findCourseForSub(pageable);
        System.out.println(JSONObject.toJSONString(courseForSub));
    }
}
