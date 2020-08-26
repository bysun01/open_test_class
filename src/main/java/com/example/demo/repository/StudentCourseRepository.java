package com.example.demo.repository;

import com.example.demo.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author ares
 */
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long>, JpaSpecificationExecutor<StudentCourse> {

}
