package org.sansan.springboot_login.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 功能：接口统一返回包装类
 * 作者：三三
 * 日期： 2024/9/26 上午10:12
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    /*
    请求返回的状态码
    200：成功
    401：客户端失败
    500：服务器失败
     */
    public static final String CODE_SUCCESS = "200";
    public static final String CODE_AUTH_ERROR = "401";
    public static final String CODE_SYS_ERROR= "500";

    private String code;
    // msg表示错误的详细信息
    private String msg;
    /*
    * 返回的数据
    * user :Object类型就是User
    * List :Object类型就是List
    * Map :Object类型就是Map
    * Object
    */
    private Object data;

    public static Result success() {
        return Result.builder().code(CODE_SUCCESS).msg("请求成功").build();
    }

    public static Result success(Object data) {
        return new Result(CODE_SUCCESS,"请求成功",data);
    }

    public static Result error() {
        return new Result(CODE_SYS_ERROR,"系统错误",null);
    }

    public static Result error(String msg) {
        return new Result(CODE_SYS_ERROR,msg,null);
    }

    public static Result error(String code,String msg) {
        return new Result(code,msg,null);
    }
}
