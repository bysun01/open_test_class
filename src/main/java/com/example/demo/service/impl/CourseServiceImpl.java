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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.Date;
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
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        User user = (User) cacheService.getCommonCache(token);
        if (course.getId() != null) {
            Course course1 = courseRepository.findById(course.getId()).get();
            BeanUtils.copyProperties(course, course1);
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
    public Page<Course> getCourses(String courseName, Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        Specification<Course> courseSpecification = new Specification<Course>(){

            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> cr, CriteriaBuilder cb) {
                Path<String> cn = root.get("courseName");
                Path<Object> isDel = root.get("isDel");
                Predicate equal = cb.equal(isDel, 1);
                if (courseName != null) {
                    Predicate like = cb.like(cn, "%" + courseName + "%");
                    cb.and(like, equal);
                }
                return equal;
            }
        };
        return courseRepository.findAll(courseSpecification, pageable);
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
