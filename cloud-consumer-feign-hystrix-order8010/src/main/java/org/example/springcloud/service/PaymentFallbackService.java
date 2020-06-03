package org.example.springcloud.service;

import org.springframework.stereotype.Component;

/**
 * @ClassName: PaymentFallbackService
 * @description:
 * @author: XZQ
 * @create: 2020/3/8 21:32
 **/
@Component
public class PaymentFallbackService implements PaymentHystrixService {
    @Override
    public String getPaymentOK(Integer id) {
        return "----PaymentFallbackService fall back--paymentInfo_OK";
    }

    @Override
    public String getPaymentTimeout(Integer id) {
        return "----PaymentFallbackService fall back--paymentInfo_TimeOut";
    }
}
