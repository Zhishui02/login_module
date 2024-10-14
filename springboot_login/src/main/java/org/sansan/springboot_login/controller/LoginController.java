package org.sansan.springboot_login.controller;

import cn.hutool.core.util.StrUtil;
import org.sansan.springboot_login.entity.Result;
import org.sansan.springboot_login.entity.User;
import org.sansan.springboot_login.service.LoginService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 功能：提供登录接口,返回数据
 * 作者：三三
 * 日期： 2024/9/26 上午9:56
 */
@CrossOrigin //允许跨域
@RestController
public class LoginController {

    @Resource //与Autowired也可以注入对象
    LoginService loginService;

    //定义一个方法处理请求,接受浏览器提交get请求
    @GetMapping("/")
    public Result hello(){
        return Result.success("请求成功");
    }

    //登录
    @GetMapping("/login")
    //请求体 json格式数据
    public Result login(User user){
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())){
            return Result.error("401","输入的用户名或密码不能为空");
        }
        User result = loginService.login(user);
        return Result.success(result);
    }

    //注册
    @PostMapping("/register")
    //请求体 json格式数据
    public Result register(@RequestBody User user){
        if (StrUtil.isBlank(user.getUsername()) || StrUtil.isBlank(user.getPassword())){
            return Result.error("输入的用户名或密码不能为空");
        }
        loginService.register(user);
        return Result.success("注册成功");
    }
}
