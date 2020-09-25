package com.itheima.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("CLOUD-PROVIDER-HYSTRIX-PAYMENT")
public interface PaymentFeignService {

    @GetMapping("/payment/hystrix/ok/{id}")
    String getById(@PathVariable("id") Long id);

    @GetMapping("/payment/hystrix/timeout/{id}")
    String getPaymentTimeout(@PathVariable("id") Long id);

}
