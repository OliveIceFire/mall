package com.example.mall.config;

import com.example.mall.filter.UserFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserFilterConfig {

    @Bean
    public UserFilter UserFilter() {
        return new UserFilter();
    }

    @Bean(name = "UserFilterCon")
    public FilterRegistrationBean UserFilterRegistration() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(UserFilter());
        filterRegistrationBean.addUrlPatterns("/cart/*");
        filterRegistrationBean.addUrlPatterns("/order/*");
        filterRegistrationBean.addUrlPatterns("/user/update");

        filterRegistrationBean.setName("UserFilterConfig");
        return filterRegistrationBean;
    }
}
