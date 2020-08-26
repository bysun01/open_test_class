package com.example.demo.repository;

import com.example.demo.vo.CourseVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author ares
 */
public interface CourseVoRepository extends JpaRepository<CourseVO, Long>, JpaSpecificationExecutor<CourseVO> {

    /**
     * 查询订阅信息里欸包
     *
     * @param pageable
     * @return Page<CourseVO>
     */
    @Query(nativeQuery = true, value = "select\n" +
            "a.id as id, \n" +
            "a.course_name as courseName,\n" +
            "a.room_no as roomNo,\n" +
            "a.desc as desc,\n" +
            "b.course_id as courseId,\n" +
            "count(b.user_id) as sub_num,\n" +
            "group_concat(b.user_id) as user_ids\n" +
            "from \n" +
            "t_course a left join t_student_course b on a.id = b.course_id \n" +
            "where \n" +
            "a.is_del=1\n" +
            "group by a.id")
    Page<CourseVO> findCourseForSub(Pageable pageable);
}
