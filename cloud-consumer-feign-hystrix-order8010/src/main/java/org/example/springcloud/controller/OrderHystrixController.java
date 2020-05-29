package org.example.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springcloud.service.PaymentHystrixService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/consumer/payment/ok/{id}")
    public String getPaymentOK(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.getPaymentOK(id);
        log.info("=======result:{}", result);
        return result;
    }

    @GetMapping("/consumer/payment/timeout/{id}")
    public String getPaymentTimeout(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.getPaymentTimeout(id);
        log.info("=======result  :{}", result);
        return result;
    }


}
