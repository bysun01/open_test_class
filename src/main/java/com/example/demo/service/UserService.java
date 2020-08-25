package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.vo.UserVo;
import org.springframework.data.domain.Page;


/**
 * @author ares
 */
public interface UserService {

    /**
     * 登录接口, 返回access_token和type
     *
     * @param username
     * @param password
     * @return
     */
    UserVo login(String username, String password);

    /**
     * 验证token
     *
     * @param token
     */
    Boolean checkToken(String token);


    /**
     * 保存用户
     *
     * @param user
     * @return
     */
    User save(UserVo uservo);

    /**
     * 获取单个用户
     *
     * @param id
     * @return
     */
    User getUser(Long id);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    User delUser(Long id);

    /**
     * 获取用户列表
     *
     * @param pageNo
     * @param pageSize
     * @param condition
     * @param roleId
     * @return
     */
    Page<User> getUsers(Integer pageNo, Integer pageSize, String condition, Long roleId);

}
