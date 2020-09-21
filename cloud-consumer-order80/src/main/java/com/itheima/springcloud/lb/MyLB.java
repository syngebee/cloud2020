package com.itheima.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class MyLB implements LoadBalancer {
    //成员角标
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    //获取下一个值
    public final int getAndIncrement(){
        int current;
        int next;
        do {
            current = this.atomicInteger.get();
            next = current >= 2147483647 ? 0:current+1;
            //current是当前值,也是期望值, 期望当前值==atomicInteger
            //如果相等,则使用next的值修改atomicInteger
        }while(!this.atomicInteger.compareAndSet(current,next));
        System.out.println("*****第几次访问,次数next: "+next);
        return next;
    }

    @Override
    //模拟负载均衡,返回具体哪个服务实现类 所有服务的列表由参数传递过来
    public ServiceInstance instances(List<ServiceInstance> serviceInstances) {
        int index = this.getAndIncrement()% serviceInstances.size();
        return serviceInstances.get(index);
    }
}
