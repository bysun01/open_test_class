package com.example.demo;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.CourseService;
import com.example.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCourse {

    private CourseService courseService;
    private UserService userService;

    @Autowired
    public void injectBean(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @Test
    public void saveCourse() {
        User user = userService.getUser(66L);
        Course course = new Course();
        course.setCourseName("语文课");
        course.setTeacher(user);
        course.setCreateBy(66L);
        course.setUpdateBy(66L);
        course.setCreateTime(new Date());
        course.setUpdateTime(new Date());
        course.setIsDel((byte) 1);
        Course course1 = courseService.saveCourse(course);
        System.out.println(JSONObject.toJSONString(course1));
    }
}
