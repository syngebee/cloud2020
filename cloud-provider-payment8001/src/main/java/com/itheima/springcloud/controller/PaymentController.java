package com.itheima.springcloud.controller;

import com.itheima.springcloud.entities.CommonResult;
import com.itheima.springcloud.entities.Payment;
import com.itheima.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private DiscoveryClient discoveryClient;


    @PostMapping("/create")
    public CommonResult create(@RequestBody Payment payment){
        log.info(payment.toString());
        int result = paymentService.create(payment);
        log.info("**********插入结果为"+result);
        if (result>0){
            return new CommonResult<>(200, "插入数据库成功,serverPort "+serverPort, result);
        }
        return new CommonResult<>(444,"插入数据库失败",null);
    }

    @GetMapping("/get/{id}")
    public CommonResult getById(@PathVariable Long id){
        Payment payment = paymentService.getPaymentById(id);
        if (payment!=null){
            return new CommonResult<>(200,"查询成功,serverPort "+serverPort,payment);
        }

        return new CommonResult<>(444,"没有对应记录,查询id:"+id,null);
    }

    @GetMapping("/discovery")
    public Object discovery(){
        return discoveryClient.getServices();
    }

    @GetMapping("/lb")
    public String getPaymentLB(){
        return serverPort;
    }

    @GetMapping("/feign/timeout")
    //测试超时设置
    public String getPaymentTimeout(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

}
