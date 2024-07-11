package com.example.lams;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigClass implements WebMvcConfigurer {
    @Autowired
    EmployeeAuthInterceptor employeeAuthInterceptor;

    @Override
    public void addInterceptors(final InterceptorRegistry registry){
        registry.addInterceptor(employeeAuthInterceptor);
    }
}
