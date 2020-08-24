package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * @author ares
 */
@Entity
@Table(name = "t_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "age")
    private Integer age;

    @Column(name = "no")
    private String no;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender")
    private byte gender;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE, targetEntity = Role.class)
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToMany(cascade = CascadeType.REFRESH, mappedBy = "students")
    private Set<Course> courses;

    @JsonIgnore
    @OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
    private Set<Course> courseList;

    @Column(name = "create_by")
    private Long createBy;

    @Column(name = "update_by")
    private Long updateBy;

    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Column(name = "update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @Column(name = "is_del")
    private byte isDel;
}
