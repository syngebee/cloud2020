package com.itheima.springcloud.controller;

import com.itheima.springcloud.entities.CommonResult;
import com.itheima.springcloud.service.PaymentFeignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderFeignController {

    @Autowired
    private PaymentFeignService paymentFeignService;

    @GetMapping("/consumer/payment/get/{id}")
    CommonResult getById(@PathVariable Long id){
        return paymentFeignService.getById(id);
    }

    @GetMapping("/consumer/feign/timeout")
    public String getPaymentTimeout(){
        return paymentFeignService.getPaymentTimeout();
    }


}
