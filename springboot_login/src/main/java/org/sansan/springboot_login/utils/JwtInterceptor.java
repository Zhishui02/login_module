package org.sansan.springboot_login.utils;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.sansan.springboot_login.entity.User;
import org.sansan.springboot_login.exception.ServiceException;
import org.sansan.springboot_login.mapper.UserMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能：token拦截器规则
 * 作者：三三
 * 日期： 2024/9/28 下午4:39
 */
public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 1.放行OPTIONS请求
        String method = request.getMethod();
        if ("OPTIONS".equals(method)) {
            return true;
        }
        // 2.获取请求头或者param参数的token
        String token = request.getHeader("token"); //请求头传递的参数
        if (StrUtil.isBlank(token)) {
            token = request.getParameter("token"); // url参数   ?token=xxx
        }
        // 3.执行认证 token是否存在
        if (StrUtil.isBlank(token)) {
            throw new ServiceException("401","请先登录");
        }
        // 4.获取token中的user id 为字符串类型
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0); //JWT.decode(token)解码 jwt token
        }catch (JWTDecodeException e){
            throw new ServiceException("401","请先登录");
        }
        // 5.根据token中的userid 转换成 Integer类型 查询数据库
        User user = userMapper.selectById(Integer.valueOf(userId));
        if (user == null) {
            throw new ServiceException("401","登录信息错误");
        }
        // 6.通过加密用户密码之后生成一个验证器
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);  // 7.验证token
        } catch (JWTVerificationException e) {
            throw new ServiceException("401","请先登录");
        }
        // 8.验证通过，放行
        return true;
    }
}
