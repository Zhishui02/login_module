package org.sansan.springboot_login.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 功能：token拦截器配置
 * 作者：三三
 * 日期： 2024/9/28 下午5:07
 */
@Configuration  //配置类
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())  //配置jwt的拦截器的规则
                .addPathPatterns("/**")  //拦截所有的请求路径
                .excludePathPatterns("/login","/register");  //放行login页面

        super.addInterceptors(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        return new JwtInterceptor();
    }
}
