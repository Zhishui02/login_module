package org.sansan.springboot_login.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.sansan.springboot_login.entity.User;

@Mapper
public interface LoginMapper {

    //登录
    @Select("select * from user where username=#{username}")
    User login(@Param("username") String username);

    //注册
    @Insert("insert into user values (null,#{username},#{password},null,null,null,null,null)")
    void insert(User user);
}
