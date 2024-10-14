package org.sansan.springboot_login.service;

import org.sansan.springboot_login.entity.PageBean;
import org.sansan.springboot_login.entity.User;
import org.sansan.springboot_login.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能：User的业务逻辑功能
 * 作者：三三
 * 日期： 2024/9/26 下午5:30
 */

@Service
public class UserService {

    //使用@Autowired注解 获取UserMapper实例
    @Autowired
    private UserMapper userMapper;

    public void insertUser(User user) {
        userMapper.insert(user);
    }

    public void updateUser(User user) {
        userMapper.update(user);
    }

    public void deleteUser(Integer id) {
        userMapper.delete(id);
    }

    //批量删除
    public void batchDeleteUser(List<Integer> ids) {
        //通过循环依次删除 数组里的id
        for (Integer id : ids) {
            userMapper.delete(id);
        }
    }

    public List<User> selectAllUser() {
        return userMapper.selectAll();
    }

    public User selectByIdUser(Integer id) {
        return userMapper.selectById(id);
    }

    public List<User> selectByNameUser(String name) {
        return userMapper.selectByName(name);
    }

    public List<User> selectByMoreUser(String username, String name) {
        return userMapper.selectByMore(username,name);
    }

    public List<User> selectByBlurUser(String username, String name) {
        return userMapper.selectByBlur(username,name);
    }

    public PageBean<User> selectByPageUser(String username, String name, Integer pageNum, Integer pageSize) {
        //计算每页该跳过的显示数量(每页该从哪里显示)   1 -> 0,5  2 -> 5,5  3 -> 10,5
        Integer skipNum = (pageNum - 1) * pageSize;

        //返回一个PageBean<>实体类实例用于存储数据
        PageBean<User> pageBean = new PageBean<>();
        List<User> listUser = userMapper.selectByPage(username, name, skipNum, pageSize);
        //存放查询的数据
        pageBean.setList(listUser);
        //存放查询数据的总数
        pageBean.setTotal(listUser.size());
        return pageBean;
    }
}
