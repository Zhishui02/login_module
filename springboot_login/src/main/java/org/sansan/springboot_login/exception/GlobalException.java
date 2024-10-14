package org.sansan.springboot_login.exception;

import org.sansan.springboot_login.entity.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能：自定义的异常
 * 作者：三三
 * 日期： 2024/9/28 上午10:01
 */
@ControllerAdvice
public class GlobalException {

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    public Result serviceException(ServiceException e) {
        return Result.error(e.getCode(), e.getMessage());
    }
}
