package com.itheima.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig {

    @Bean
//    @LoadBalanced  //注释: 使用手动实现的RoundLoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
