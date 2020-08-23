package com.example.demo.exception;


import com.example.demo.common.BizEnum;

/**
 * @author ares
 */
public class BizException extends RuntimeException {

    BizEnum bizEnum;

    public BizException() {
    }

    public BizException(BizEnum bizEnum) {
        this.bizEnum = bizEnum;
    }

    public BizEnum getBizEnum() {
        return bizEnum;
    }

    public void setBizEnum(BizEnum bizEnum) {
        this.bizEnum = bizEnum;
    }
}