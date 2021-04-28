package com.we.manager.api;

/**
 * 封装API的错误码
 * @author we
 * @date 2021-03-01 20:23
 **/
public interface IErrorCode {
    long getCode();

    String getMessage();
}
