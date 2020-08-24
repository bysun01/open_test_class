package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CacheService;
import com.example.demo.service.CourseService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author ares
 */
@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private CacheService cacheService;

    @Autowired
    public void injectBean(CourseRepository courseRepository, CacheService cacheService) {
        this.courseRepository = courseRepository;
        this.cacheService = cacheService;
    }

    @Override
    public Course saveCourse(Course course) {
        if (course.getId() != null) {
            Course course1 = courseRepository.findById(course.getId()).get();
            BeanUtils.copyProperties(course, course1);
            course = course1;
        }
        return courseRepository.save(course);
    }

    @Override
    public Course delCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        course.setIsDel((byte) 1);
        return courseRepository.save(course);
    }

    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId).get();
    }

    @Override
    public Page<Course> getCourses(String courseName, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return courseRepository.findByCourseNameLike("%" + courseName + "%", pageable);
    }

    @Override
    public void subCourses(Long courseId) {
        String token = String.valueOf(SecurityUtils.getSubject().getPrincipal());
        User user = (User) cacheService.getCommonCache(token);
        Course course = courseRepository.findById(courseId).get();
        course.addUser(user);
        courseRepository.saveAndFlush(course);
    }

    @Override
    public void abbrSubCourses(Long courseId) {
        String token = String.valueOf(SecurityUtils.getSubject().getPrincipal());
        User user = (User) cacheService.getCommonCache(token);
        Course course = courseRepository.findById(courseId).get();
        course.removeUser(user);
        courseRepository.saveAndFlush(course);
    }


}
