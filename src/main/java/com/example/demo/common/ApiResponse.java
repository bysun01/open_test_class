package com.example.demo.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author ares
 */
@Data
@Slf4j
public class ApiResponse<T> {
    private Integer code;
    private String message;
    private T data;

    public ApiResponse() {

    }

    public ApiResponse(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 定义常用静态方法
     */
    public static <T> ApiResponse ofSuccess(T data) {
        return new ApiResponse<T>(BizEnum.SUCCESS.getCode(), BizEnum.SUCCESS.getMessage(), data);
    }

    public static ApiResponse ofSuccess() {
        return new ApiResponse<>(BizEnum.SUCCESS.getCode(), BizEnum.SUCCESS.getMessage(), null);
    }

    public static ApiResponse ofError() {
        return new ApiResponse<>(BizEnum.ERROR.getCode(), BizEnum.ERROR.getMessage(), null);
    }

    public static <T> ApiResponse ofError(T data) {
        return new ApiResponse<T>(BizEnum.ERROR.getCode(), BizEnum.ERROR.getMessage(), data);
    }

    public static ApiResponse ofMessage(int code, String message) {
        return new ApiResponse(code, message, null);
    }

    public static ApiResponse ofStatus(BizEnum status) {
        return new ApiResponse<>(status.getCode(), status.getMessage(), null);
    }

    public static <T> ApiResponse ofMessage(BizEnum status, T data) {
        return new ApiResponse<T>(status.getCode(), status.getMessage(), data);
    }
}
