package com.itheima.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder
                .routes()
                .route("path_route_itheima1",r->r.path("/guonei").uri("http://news.baidu.com/guonei"))
                .route("path_route_itheima2",r->r.path("/guoji").uri("http://news.baidu.com/guoji"))
                .route("path_route_itheima3",r->r.path("/mil").uri("http://news.baidu.com/mil"))
                .route("path_route_itheima4",r->r.path("/finance").uri("http://news.baidu.com/finance"))
                .build();
    }
}
