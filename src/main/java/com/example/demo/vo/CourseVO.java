package com.example.demo.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;

/**
 * @author ares
 */
@Entity
@Data
public class CourseVO {
    @Id
    private Long id;
    private String courseName;
    private String roomNo;
    private String desc;
    private Integer subNum;
    private String userIds;
}
