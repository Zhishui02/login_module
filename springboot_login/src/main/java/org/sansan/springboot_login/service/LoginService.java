package org.sansan.springboot_login.service;

import org.sansan.springboot_login.entity.User;
import org.sansan.springboot_login.exception.ServiceException;
import org.sansan.springboot_login.mapper.LoginMapper;
import org.sansan.springboot_login.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能：
 * 作者：三三
 * 日期： 2024/9/27 下午9:39
 */
@Service
public class LoginService {

    @Autowired
    LoginMapper loginMapper;

    public User login(User user) {
        //根据用户名查询数据库的用户信息
        User dbUser = loginMapper.login(user.getUsername());
        //判断用户名是否正确 ，为空说明用户名不存在
        if (dbUser == null) {
            //抛出一个自定义的异常
            throw new ServiceException("输入的用户名或密码有误!");
        }
        //判断密码是否正确
        if (!user.getPassword().equals(dbUser.getPassword())) {
            throw new ServiceException("输入的用户名或密码有误!");
        }
        // 根据密码生成token
        String token = TokenUtils.createToken(dbUser.getId().toString(),dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    public void register(User user) {
        //根据用户名查询数据库的用户信息
        User dbUser = loginMapper.login(user.getUsername());
        //判断用户名是否为空,不为空则说明数据库中已经存在该用户名
        if (dbUser != null) {
            //抛出一个自定义的异常
            throw new ServiceException("用户名已存在!");
        }
        loginMapper.insert(user);
    }
}
