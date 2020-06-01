package org.example.springcloud.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.example.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {
    @Override
    public String getPaymentOK(Integer id) {
        return "线程" + Thread.currentThread().getName() + "payment id:" + id;
    }

    @Override
    @HystrixCommand(fallbackMethod = "paymentTimeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String getPaymentTimeout(Integer id) {
        Integer time = 5;
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            log.error("{}", e);
        }
        return "线程" + Thread.currentThread().getName() + "payment id:" + id + "time:" + time;
    }

    public String paymentTimeoutHandler(Integer id) {
        return "系统繁忙，请稍后重试，线程" + Thread.currentThread().getName() + "payment timeout id:" + id;
    }
}
