package com.example.demo.service.impl;

import com.example.demo.entity.Course;
import com.example.demo.entity.StudentCourse;
import com.example.demo.entity.User;
import com.example.demo.repository.CourseRepository;
import com.example.demo.repository.CourseVoRepository;
import com.example.demo.repository.StudentCourseRepository;
import com.example.demo.service.CacheService;
import com.example.demo.service.CourseService;
import com.example.demo.utils.CopyUtil;
import com.example.demo.vo.CourseVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * @author ares
 */
@Service
public class CourseServiceImpl implements CourseService {

    private CourseRepository courseRepository;
    private CacheService cacheService;
    private CourseVoRepository courseVoRepository;
    private StudentCourseRepository studentCourseRepository;

    @Autowired
    public void injectBean(CourseRepository courseRepository, CacheService cacheService,
                           CourseVoRepository courseVoRepository, StudentCourseRepository studentCourseRepository) {
        this.courseRepository = courseRepository;
        this.cacheService = cacheService;
        this.courseVoRepository = courseVoRepository;
        this.studentCourseRepository = studentCourseRepository;
    }

    @Override
    public Course saveCourse(Course course) {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        User user = (User) cacheService.getCommonCache(token);
        if (course.getId() != null) {
            Course course1 = courseRepository.findById(course.getId()).get();
            CopyUtil.copyProperties(course, course1);
            course = course1;
        } else {
            course.setCreateBy(user.getId());
            course.setCreateTime(new Date());
        }
        course.setTeacher(user);
        course.setIsDel(1);
        course.setUpdateTime(new Date());
        course.setUpdateBy(user.getId());
        return courseRepository.save(course);
    }

    @Override
    public Course delCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).get();
        course.setIsDel(0);
        return courseRepository.save(course);
    }

    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.findById(courseId).get();
    }

    @Override
    public Page<CourseVO> getCourses(String courseName, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return courseVoRepository.findCourseForSub(pageable);
    }

    @Override
    public void subCourses(Long courseId) {
        String token = String.valueOf(SecurityUtils.getSubject().getPrincipal());
        User user = (User) cacheService.getCommonCache(token);
        Course course = courseRepository.findById(courseId).get();
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourse(course);
        studentCourse.setUser(user);
        studentCourseRepository.save(studentCourse);
    }

    @Override
    public void abbrSubCourses(Long courseId) {
        String token = String.valueOf(SecurityUtils.getSubject().getPrincipal());
        User user = (User) cacheService.getCommonCache(token);
        Course course = courseRepository.findById(courseId).get();
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setCourse(course);
        studentCourse.setUser(user);
        Example<StudentCourse> of = Example.of(studentCourse);
        StudentCourse course1 = studentCourseRepository.findOne(of).get();
        studentCourseRepository.deleteById(course1.getId());
    }


}
