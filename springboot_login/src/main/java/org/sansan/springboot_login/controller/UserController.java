package org.sansan.springboot_login.controller;

import org.sansan.springboot_login.entity.PageBean;
import org.sansan.springboot_login.entity.Result;
import org.sansan.springboot_login.entity.User;
import org.sansan.springboot_login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 功能：提供User接口,返回数据
 * 作者：三三
 * 日期： 2024/9/26 下午5:31
 */
@CrossOrigin //允许跨域
@RestController
@RequestMapping("/user")
public class UserController {

    //使用@Autowired注解 获取UserService实例
    @Autowired
    private UserService userService;

    //查询全部用户信息
    @GetMapping("/selectAll")
    public Result selectAll(){
        //获取全部查询后的 数据
        List<User> listUser = userService.selectAllUser();
        return Result.success(listUser);
    }

    //查询单个用户信息 根据id
    @GetMapping("/selectById/{id}")
    //路径id查询
    public Result selectById(@PathVariable Integer id){
        User user = userService.selectByIdUser(id);
        return Result.success(user);
    }

    //查询一个用户的多条信息 根据name
    // 根据条件查询的时候，如果不确定查询的结果有多少个，那就统一返回一个List对象集合
    @GetMapping("/selectByName/{name}")
    //路径name查询
    public Result selectByName(@PathVariable String name){
        List<User> listUser = userService.selectByNameUser(name);
        return Result.success(listUser);
    }

    //多条件查询用户信息
    @GetMapping("/selectByMore")
    //根据param参数查询
    public Result selectByMore(@RequestParam String username,@RequestParam String name){
        List<User> listUser = userService.selectByMoreUser(username,name);
        return Result.success(listUser);
    }

    //多条件模糊查询用户信息
    @GetMapping("/selectByBlur")
    //根据param参数查询
    public Result selectByBlur(@RequestParam String username,@RequestParam String name){
        List<User> listUser = userService.selectByBlurUser(username,name);
        return Result.success(listUser);
    }

    //多条件模糊分页查询用户信息
    //PageNum 当前页码  PageSize 每页查询的数量
    @GetMapping("/selectByPage")
    //根据param参数查询
    public Result selectByPage(@RequestParam String username,
                               @RequestParam String name,
                               @RequestParam Integer pageNum,
                               @RequestParam Integer pageSize){
        PageBean<User> pagebean =  userService.selectByPageUser(username,name,pageNum,pageSize);
        return Result.success(pagebean);
    }

    //新增用户信息
    @PostMapping("/add")
    //请求体 json格式数据
    public Result add(@RequestBody User user){
        try{
            userService.insertUser(user);
        } catch (Exception e){
            if(e instanceof DuplicateKeyException){
                return Result.error("输入数据库的数据有问题");
            }else {
                return Result.error("系统错误");
            }
        }

        return Result.success();
    }

    //修改用户信息
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.updateUser(user);
        return Result.success();
    }

    //删除单个用户信息
    @DeleteMapping("/delete/{id}")
    //路径id删除
    public Result delete(@PathVariable Integer id){
        userService.deleteUser(id);
        return Result.success();
    }

    //批量删除用户信息
    @DeleteMapping("/delete/batch")
    //请求体 数组数据
    public Result batchDelete(@RequestBody List<Integer> ids){
        userService.batchDeleteUser(ids);
        return Result.success();
    }
}
