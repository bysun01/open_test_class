package com.example.demo.query;

import lombok.Data;

/**
 * @author ares
 */
@Data
public class UsersQuery {
    private Integer pageNo;
    private Integer pageSize;
    private String condition;
    private Long roleId;
}
