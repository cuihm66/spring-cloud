package org.example.springcloud.Controller;

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
}
