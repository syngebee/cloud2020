package com.itheima.cloud.alibaba.controller;

import com.itheima.cloud.alibaba.domain.CommonResult;
import com.itheima.cloud.alibaba.domain.Order;
import com.itheima.cloud.alibaba.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    //这里写GetMapping只是为了方便使用浏览器测试
    public CommonResult create(Order order){
        orderService.create(order);
        return new CommonResult(200,"订单创建成功");
    }



}
