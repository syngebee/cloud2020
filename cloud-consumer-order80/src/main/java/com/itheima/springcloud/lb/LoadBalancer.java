package com.itheima.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    //获取Eureka上注册的机器总数 ServiceInstance
    ServiceInstance instances(List<ServiceInstance> serviceInstances);
}
