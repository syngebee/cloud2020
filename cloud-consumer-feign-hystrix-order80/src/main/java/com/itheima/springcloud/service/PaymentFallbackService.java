package com.itheima.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements PaymentFeignService {
    @Override
    public String getById(Long id) {
        return "payment_fallback /(ㄒoㄒ)/~~";
    }

    @Override
    public String getPaymentTimeout(Long id) {
        return "payment_fallback /(ㄒoㄒ)/~~";
    }
}
