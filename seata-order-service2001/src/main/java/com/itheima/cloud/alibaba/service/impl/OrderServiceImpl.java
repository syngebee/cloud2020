package com.itheima.cloud.alibaba.service.impl;

import com.itheima.cloud.alibaba.dao.OrderDao;
import com.itheima.cloud.alibaba.domain.CommonResult;
import com.itheima.cloud.alibaba.domain.Order;
import com.itheima.cloud.alibaba.service.AccountService;
import com.itheima.cloud.alibaba.service.OrderService;
import com.itheima.cloud.alibaba.service.StorageService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderDao orderDao;
    @Resource
    private StorageService stroageService;
    @Resource
    private AccountService accountService;

    @Override
    @GlobalTransactional(name = "orderGlobalTransactional",rollbackFor = Exception.class)
    public void create(Order order) {
        //新建订单
        log.info("------>开始新建订单");
        orderDao.create(order);
        //减库存
        log.info("------>订单微服务开始调用库存服务,做扣减");
        CommonResult result1 = stroageService.decrease(order.getProductId(),order.getCount());
        //减账户
        log.info("------>订单微服务开始调用账户服务,做扣减");
        CommonResult result2 = accountService.decrease(order.getUserId(),order.getMoney());
        //更新订单
        log.info("------>修改订单状态开始");
        orderDao.update(order.getUserId(),0);
        log.info("------>修改订单状态结束");
        log.info("------>下单状态结束");
    }
}
