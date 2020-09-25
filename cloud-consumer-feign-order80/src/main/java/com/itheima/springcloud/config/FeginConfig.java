package com.itheima.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeginConfig {
    @Bean
    //Logger.Level是一个枚举类
    public Logger.Level feginLoggerLevel(){
        return Logger.Level.FULL;
    }

}
