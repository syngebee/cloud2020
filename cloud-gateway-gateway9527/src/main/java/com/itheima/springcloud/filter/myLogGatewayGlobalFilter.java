package com.itheima.springcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
public class myLogGatewayGlobalFilter implements GlobalFilter,Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("###################come in myLogGatewayFilter"+new Date());
        String username = exchange.getRequest().getQueryParams().getFirst("username");
        if (username==null){
            //设置响应状态码
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            //返回完成响应
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    @Override
    //数字越小越先执行
    public int getOrder() {
        return 0;
    }
}
