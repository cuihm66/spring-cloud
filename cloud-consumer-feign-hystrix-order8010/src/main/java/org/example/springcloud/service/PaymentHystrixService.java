package org.example.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PAYMENT-HYSTRIX-SERVICE")
public interface PaymentHystrixService {
    @GetMapping("/payment/ok/{id}")
    String getPaymentOK(@PathVariable("id") Integer id);

    @GetMapping("/payment/timeout/{id}")
    String getPaymentTimeout(@PathVariable("id") Integer id);
}
