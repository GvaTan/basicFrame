package com.zhys.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private ResponseResultInterceptor responseResultInterceptor;

    @Autowired
    private AllowCrossDomainInterceptor allowCrossDomainInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String apiUri = "/**";
        //返回拦截处理
        registry.addInterceptor(responseResultInterceptor).addPathPatterns(apiUri);
      //跨域拦截
        registry.addInterceptor(allowCrossDomainInterceptor).addPathPatterns(apiUri);
    }

}
