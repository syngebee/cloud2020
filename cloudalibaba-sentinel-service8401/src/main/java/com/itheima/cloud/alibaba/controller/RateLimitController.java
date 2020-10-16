package com.itheima.cloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.itheima.cloud.alibaba.myhandler.CustomerBlockHandler;
import com.itheima.springcloud.entities.CommonResult;
import com.itheima.springcloud.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/rateLimiter")
public class RateLimitController {

    @GetMapping("/getResource")
    @SentinelResource(value = "getResource",blockHandler = "handleException")
    public CommonResult getResource(){
        return new CommonResult(200,"按资源名称限流测试",new Payment(2020L,"serial001"));
    }
    public CommonResult handleException(BlockException exception){
        return new CommonResult(4444,exception.getClass().getCanonicalName()+"\t服务不可用");
    }

    @GetMapping("/customerBlockHandler")
    @SentinelResource(value = "customerBlockHandler"
            ,blockHandlerClass = CustomerBlockHandler.class
            ,blockHandler = "handerException")
    public CommonResult customerBlockHandler(){
        return new CommonResult(200,"按客户自定义",new Payment(2020L,"serial1002"));
    }

    @GetMapping("/customerBlockHandler2")
    @SentinelResource(value = "customerBlockHandler2"
            ,blockHandlerClass = CustomerBlockHandler.class
            ,blockHandler = "handerException2")
    public CommonResult customerBlockHandler2(){
        return new CommonResult(200,"按客户自定义",new Payment(2020L,"serial1003"));
    }



}
