package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ares
 */
@Entity
@Table(name = "t_user")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

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
    private Integer phone;

    @Column(name = "gender")
    private byte gender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    @Column(name = "create_by")
    private String createBy;

    @Column(name = "update_by")
    private String updateBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "is_del")
    private byte isDel;
}
