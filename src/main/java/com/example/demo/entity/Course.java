package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author ares
 */
@Entity
@Table(name = "t_course")
@Setter
@Getter
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11)
    private Long id;

    @Column(name = "course_name", length = 50)
    private String courseName;

    @Column(name = "room_no")
    private String roomNo;

    @Column(name = "desc")
    private String desc;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private Set<StudentCourse> studentCourse;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = User.class)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "create_time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "update_time")
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

    @Column(name = "is_del", length = 1)
    private Integer isDel;

//    public void addUser(User user) {
//        this.students.add(user);
//    }
//
//    public void removeUser(User user) {
//        this.students.remove(user);
//    }
}
