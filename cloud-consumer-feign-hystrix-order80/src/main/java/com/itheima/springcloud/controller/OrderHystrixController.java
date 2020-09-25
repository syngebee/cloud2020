package com.itheima.springcloud.controller;

import com.itheima.springcloud.entities.CommonResult;
import com.itheima.springcloud.service.PaymentFeignService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderHystrixController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/hystrix/get/{id}")
    public String getById(@PathVariable Long id){
        return paymentFeignService.getById(id);
    }

    @GetMapping("/consumer/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {
            @HystrixProperty(name= "execution.isolation.thread.timeoutInMilliseconds",value= "2000")
    })
    public String getPaymentTimeout(@PathVariable Long id){
        return paymentFeignService.getPaymentTimeout(id);
    }
    public String paymentTimeOutFallbackMethod(@PathVariable Long id){
        return "我是消费者80方,对方支付系统繁忙请稍后重试 或者 检查下自己吧o(╥﹏╥)o";
    }

}
