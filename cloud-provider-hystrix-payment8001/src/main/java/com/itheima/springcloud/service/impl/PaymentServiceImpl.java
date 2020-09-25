package com.itheima.springcloud.service.impl;

import com.itheima.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;

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

}
