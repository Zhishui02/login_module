package org.sansan.springboot_login.mapper;

import org.apache.ibatis.annotations.*;
import org.sansan.springboot_login.entity.User;

import java.util.List;

@Mapper
public interface UserMapper {

    //向数据库中插入数据
    @Insert("insert into user values(null,#{username},#{password},#{name},#{phone},#{email},#{address},#{avatar})")
    void insert(User user);

    //修改数据
    @Update("update user set username=#{username},password=#{password},name=#{name},phone=#{phone}," +
            "email=#{email},address=#{address},avatar=#{avatar} where id = #{id} ")
    void update(User user);

    //删除数据
    @Delete("delete from user where id=#{id}")
    void delete(int id);

    //查询全部用户信息    order by id desc根据id进行逆序排序
    @Select("select * from user order by id desc ")
    List<User> selectAll();

    //查询单个用户信息
    @Select("select * from user where id=#{id}")
    User selectById(Integer id);

    //查询多个用户信息
    @Select("select * from user where name=#{name}")
    List<User> selectByName(String name);

    //多条件查询用户信息
    @Select("select * from user where username=#{username} and name=#{name}")
    // param参数查询需要加上@Param("")注解
    List<User> selectByMore(@Param("username") String username,@Param("name") String name);

    //多条件模糊查询用户信息
    @Select("select * from user where username like concat('%',#{username},'%') or name like concat('%',#{name},'%')")
    List<User> selectByBlur(@Param("username") String username,@Param("name") String name);

    //多条件模糊分页查询用户信息
    @Select("select * from user where username like concat('%',#{username},'%') and name like concat('%',#{name},'%') limit #{skipNum},#{pageSize}")
    List<User> selectByPage(@Param("username") String username,@Param("name") String name,@Param("skipNum") Integer skipNum,@Param("pageSize") Integer pageSize);
}
