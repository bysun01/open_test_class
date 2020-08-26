package com.example.demo.service;

import com.example.demo.entity.Course;
import com.example.demo.vo.CourseVO;
import org.springframework.data.domain.Page;

/**
 * @author ares
 */
public interface CourseService {

    /**
     * 保存course
     *
     * @param course
     * @return
     */
    Course saveCourse(Course course);

    /**
     * 删除course
     *
     * @param courseId
     * @return
     */
    Course delCourse(Long courseId);

    /**
     * 根据id获取课程
     *
     * @param courseId
     * @return
     */
    Course getCourse(Long courseId);

    /**
     * 获取课程列表
     *
     * @return
     */
    Page<CourseVO> getCourses(String courseName, Integer pageNo, Integer pageSize);


    /**
     * 学生订阅课程
     *
     * @param courseId
     */
    void subCourses(Long courseId);

    /**
     * 学生取消订阅课程
     *
     * @param courseId
     */
    void abbrSubCourses(Long courseId);
}
