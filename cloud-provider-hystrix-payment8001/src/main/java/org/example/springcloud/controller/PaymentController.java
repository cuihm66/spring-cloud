package org.example.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.example.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;
    @GetMapping("/payment/ok/{id}")
    public String getPaymentOK(@PathVariable("id") Integer id) {
        String result = paymentService.getPaymentOK(id);
        log.info("=========:{}", result);
        return result;
    }

    @GetMapping("/payment/timeout/{id}")
    public String getPaymentTimeout(@PathVariable("id") Integer id) {
        String result = paymentService.getPaymentTimeout(id);
        log.info("=========:{}", result);
        return result;
    }

    /**
     * 服务熔断
     * http://localhost:8001/payment/circuit/1
     *
     * @param id
     * @return
     */
    @GetMapping("/circuit/{id}")
    @HystrixCommand
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("***result:" + result);
        return result;
    }
}
