package com.itheima.springcloud.alibaba.controller;

import com.itheima.springcloud.alibaba.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/consumer")
public class OrderNacosController {

    @Autowired
    private RestTemplate restTemplate;  //restTemplate写法

    @Autowired
    private OrderService orderService; //OpenFeign写法

    @Value("${service-url.nacos-user-service}")
    private String serviceUrl;

    @GetMapping("/payment/nacos/{id}")
    public String paymentInfo(@PathVariable("id")String id){
//        return restTemplate.getForObject(serviceUrl+"/payment/nacos/"+id,String.class); //restTemplate
        return orderService.getPayment(id); //OpenFeign写法
    }
}
