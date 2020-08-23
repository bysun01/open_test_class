package com.example.demo.config;

import com.example.demo.common.ApiResponse;
import com.example.demo.common.BizEnum;
import com.example.demo.exception.BizException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ares
 */
@ControllerAdvice
public class ExceptionHandle {

    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public ApiResponse myExceptionHandler(BizException ex) {
        return ApiResponse.ofStatus(ex.getBizEnum());
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public ApiResponse defaultExceptionHandler(HttpServletRequest req, Exception e) {

        return ApiResponse.ofMessage(BizEnum.NO_PERMISSION, null);
    }
}
