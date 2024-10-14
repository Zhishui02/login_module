package org.sansan.springboot_login.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.sansan.springboot_login.entity.User;
import org.sansan.springboot_login.mapper.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 功能：
 * 作者：三三
 * 日期： 2024/9/29 下午2:22
 */
@Component
public class TokenUtils {

    private static UserMapper staticUserMapper;

    @Resource
    UserMapper userMapper;

    @PostConstruct
    public void setUserService() {
        staticUserMapper = userMapper;
    }

    //生成token
    public static String createToken(String userId, String password) {
        return JWT.create().withAudience(userId) //将userid保存带token里面。作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(), 48))  //48小时后，token过期
                .sign(Algorithm.HMAC256(password));  //以password作为token的密钥
    }

    //获取当前登录的用户信息
    public static User getCurrentUser(){
        try{
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String token = request.getHeader("token");
            if (StrUtil.isNotBlank(token)){
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserMapper.selectById(Integer.parseInt(userId));
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }

}