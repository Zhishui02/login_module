package org.sansan.springboot_login.exception;

import lombok.Data;
import lombok.Getter;

/**
 * 功能：
 * 作者：三三
 * 日期： 2024/9/28 上午10:03
 */
@Getter
public class ServiceException extends RuntimeException {

    private final String code;

    public ServiceException(String message) {
        super(message);
        this.code = "500";
    }

    public ServiceException(String code,String message) {
        super(message);
        this.code = code;
    }
}
