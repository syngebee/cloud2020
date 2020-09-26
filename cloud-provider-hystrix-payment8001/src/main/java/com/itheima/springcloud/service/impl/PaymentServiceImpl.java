package com.itheima.springcloud.service.impl;

import cn.hutool.core.util.IdUtil;
import com.itheima.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String paymentInfo_OK(Integer id) {
        return Thread.currentThread().getName()+" paymentInfo_OK,id: "+id+"\t"+"O(∩_∩)O哈哈~";
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentInfo_TimeoutHandler",commandProperties = {
            @HystrixProperty(name= "execution.isolation.thread.timeoutInMilliseconds",value= "3000")
    })//三秒内出正常，超时触发降级方法
    public String paymentInfo_Timeout(Integer id) {
        //int i = 1/0; //模拟错误，主动触发降级方法

        int timeNumber=5;
        try{TimeUnit.SECONDS.sleep(timeNumber);}catch(InterruptedException e) {e.printStackTrace();}

        return Thread.currentThread().getName()+" paymentInfo_Timeout,id: "+id+"\t"+"o(*￣▽￣*)ブ"+" 耗时(秒): "+timeNumber;
    }

    //fallback方法，降级方法↑
    public String paymentInfo_TimeoutHandler(Integer id) {
        return Thread.currentThread().getName()+" 系统繁忙或运行报错，请稍后再试,id "+id+"\t"+"/(ㄒoㄒ)/~~";
    }

    //==================服务熔断==============================
    //服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),  //是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),   //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),  //跳闸后，拒绝请求到再次尝试请求并决定回路是否继续打开的时间
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60"), //失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id){
        if (id < 0){
            throw new RuntimeException("*****id 不能负数");
        }
        //hutool工具包
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName()+"\t"+"调用成功,流水号："+serialNumber;
    }
    public String paymentCircuitBreaker_fallback(Integer id){
        return "id 不能负数，请稍候再试,(┬＿┬)/~~     id: " +id;
    }

}
