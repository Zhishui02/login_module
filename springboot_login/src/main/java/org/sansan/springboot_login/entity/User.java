package org.sansan.springboot_login.entity;

import lombok.Data;

/**
 * 功能：用户实体类
 * 作者：三三
 * 日期： 2024/9/26 上午11:42
 */

@Data   // @Data注解 自动创建getter和setter方法
//@AllArgsConstructor  //全参构造器
//@NoArgsConstructor   //无参构造器
public class User {
    private Integer id;
    private String username;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String avatar;

    private String token;


    /*
    全参构造
    public User(Integer age, Integer id, String username, String password, String name, String phone, String email, String address, String avatar) {
        this.age = age;
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.avatar = avatar;
    }
     无参构造
     public User() {}
     */


}
