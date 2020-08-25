package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.common.BizEnum;
import com.example.demo.entity.User;
import com.example.demo.exception.BizException;
import com.example.demo.query.LoginQuery;
import com.example.demo.service.UserService;
import com.example.demo.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ares
 */
@RestController
@RequestMapping("/v1/user/")
public class UserController {

    private UserService userService;

    @Autowired
    public void setBean(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ApiResponse login(@RequestBody LoginQuery loginQuery) {
        if (StringUtils.isBlank(loginQuery.getUsername()) || StringUtils.isBlank(loginQuery.getPassword())) {
            throw new BizException(BizEnum.INVALID_PARAM);
        }
        return ApiResponse.ofSuccess(userService.login(loginQuery.getUsername(), loginQuery.getPassword()));
    }

    @PostMapping("saveUser")
    public ApiResponse addUser(@RequestBody UserVo userVo) {
        if (userVo == null) {
            throw new BizException(BizEnum.INVALID_PARAM);
        }
        User user = new User();
        userService.save(userVo);
        return ApiResponse.ofSuccess();
    }

    @PostMapping("delUser")
    public ApiResponse delUser(@RequestParam Long userId) {
        if (null == userId) {
            throw new BizException(BizEnum.INVALID_PARAM);
        }
        userService.delUser(userId);
        return ApiResponse.ofSuccess();
    }

    @GetMapping("/getUser")
    public ApiResponse getUser(Long userId) {
        if (null == userId) {
            throw new BizException(BizEnum.INVALID_PARAM);
        }
        return ApiResponse.ofSuccess(userService.getUser(userId));
    }

    @GetMapping("/getUsers")
    public ApiResponse getUsers(@RequestParam(defaultValue = "1") Integer pageNo,
                                @RequestParam(defaultValue = "10") Integer pageSize,
                                @RequestParam(required = false) String condition,
                                @RequestParam(required = false) Long roleId) {
        return ApiResponse.ofSuccess(userService.getUsers(pageNo, pageSize, condition, roleId));
    }

}
