package com.itheima.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/*@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker*/
@SpringCloudApplication
public class PaymentHystrixMain8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentHystrixMain8001.class,args);
    }
}
