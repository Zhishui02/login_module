package org.sansan.springboot_login.entity;

import lombok.Data;

import java.util.List;

/**
 * 功能：
 * 作者：三三
 * 日期： 2024/9/27 下午12:06
 */
@Data
public class PageBean<T> {
    private Integer total;  //查询单页数据的总数
    private List<T> list;   //数据列表
}
