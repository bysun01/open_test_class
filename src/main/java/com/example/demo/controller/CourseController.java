package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.entity.Course;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ares
 */
@RestController
@RequestMapping("/v1/course/")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public void injectBean(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("saveCourse")
    public ApiResponse saveCourse(Course course) {
        return ApiResponse.ofSuccess(courseService.saveCourse(course));
    }

    @PostMapping("delCourse")
    public ApiResponse delCourse(Long courseId) {
        return ApiResponse.ofSuccess(courseService.delCourse(courseId));
    }

    @GetMapping("getCourse")
    public ApiResponse getCourse(Long courseId) {
        return ApiResponse.ofSuccess(courseService.getCourse(courseId));
    }

    @GetMapping("getCourses")
    public ApiResponse getCourses(@RequestParam(defaultValue = "1") Integer pageNo,
                                  @RequestParam(defaultValue = "10") Integer pageSize,
                                  @RequestParam String courseName) {
        return ApiResponse.ofSuccess(courseService.getCourses(courseName, pageNo, pageSize));
    }

    @GetMapping("subCourses")
    public ApiResponse subCourses(@RequestParam Long courseId) {
        courseService.subCourses(courseId);
        return ApiResponse.ofSuccess();
    }

    @GetMapping("abbrSubCourses")
    public ApiResponse abbrSubCourses(@RequestParam Long courseId) {
        courseService.abbrSubCourses(courseId);
        return ApiResponse.ofSuccess();
    }
}
