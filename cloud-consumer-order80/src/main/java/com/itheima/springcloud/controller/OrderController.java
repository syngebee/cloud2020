package com.itheima.springcloud.controller;

import com.itheima.myrule.MyselfRule;
import com.itheima.springcloud.entities.CommonResult;
import com.itheima.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/consumer")
@RibbonClient(name= "CLOUD-PAYMENT-SERVICE",configuration=MyselfRule.class)
public class OrderController {
//    public static final String PAYMENT_URL="http://localhost:8001";
    public static final String PAYMENT_URL="http://CLOUD-PAYMENT-SERVICE";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/payment/create")
    public CommonResult creat(@RequestBody Payment payment){
        return restTemplate.postForObject(PAYMENT_URL+"/payment/create",payment,CommonResult.class);
    }

    @GetMapping("/payment/get/{id}")
    public CommonResult getById(@PathVariable Long id){
        return restTemplate.getForObject(PAYMENT_URL+"/payment/get/"+id,CommonResult.class);
    }

    @GetMapping("/payment/getForEntity/{id}")
    public CommonResult getById2(@PathVariable Long id){
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, CommonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()){
            return entity.getBody();
        }
        return new CommonResult(444,"操作失败");
    }


}
