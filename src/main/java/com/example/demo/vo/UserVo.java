package com.example.demo.vo;

import com.example.demo.entity.User;
import lombok.Getter;
import lombok.Setter;

/**
 * @author ares
 */
@Setter
@Getter
public class UserVo extends User {

    private String token;

    private Long roleId;
}
