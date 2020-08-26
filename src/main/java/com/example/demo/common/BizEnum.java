package com.example.demo.common;

import lombok.Getter;


/**
 * @author ares
 */

@Getter
public enum BizEnum {
    //成功
    SUCCESS(200, "OK"),
    ERROR(1, "fail"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "page not found"),
    INTERNAL_SERVER_ERROR(500, "server internal error"),
    NOT_LOGIN(40005, "not login"),
    NO_PERMISSION(40003, "沒有权限"),
    SMS_FAIL(10000, "发送信息失败"),
    SMS_CHECK_FAIL(10001, "短信验证码验证失败"),
    TOKEN_FAIL(401, "TOKEN失效,请重新登录"),
    WX_AUTH_FAIL(10003, "微信授权失败"),
    WX_HAS_REG(10004, "微信已注册"),
    GET_USER_FAIL(10005, "获取用户信息失败"),
    INVALID_PARAM(10006, "参数不全"),
    NAME_OR_PASSWOR_IS_ERROR(10007, "账号或密码错误"),
    NO_REG(10008, "未注册"),
    NO_SELECT(10009, "没有查到该用户"),
    NO_DATA(10010, "没有查到数据"),
    UPDATE_MOBILE_FAIL(10011, "手机号已经注册"),
    NAME_ERROR(10012, "昵称不能为空"),
    MOBILE_ERROR(10013, "请输入正确的手机号"),
    TIMEFORMAT(10014, "时间格式不正确"),
    EMAIl_FAIL(10015, "email发送信息失败"),
    EMAIL_CHECK_FAIL(10016, "email验证码验证失败"),
    USERNAME_EXIST(10017, "用户名已存在")
    ;


    private Integer code;

    private String message;

    BizEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
