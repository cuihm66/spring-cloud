package org.example.springcloud.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springcloud.entities.CommonResult;
import org.example.springcloud.entities.Payment;
import org.example.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("插入结果  ：{}", result);
        if(result > 0) {
            return new CommonResult(200, "插入成功", result);
        } else {
            return new CommonResult(444,"插入失败");
        }
    }

    @GetMapping("/payment/{id}")
    public CommonResult getById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getById(id);
        log.info("查询结果：{}", payment);
        if(payment != null) {
            return new CommonResult(200, "查询成功", payment);
        } else {
            return new CommonResult(444,"查询为空");
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();
        for (String element: services) {
            log.info("=====element:{}", element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance: instances) {
            log.info(instance.getInstanceId() + " " + instance.getHost() + " " + instance.getPort());
            log.info(" " + instance.getUri());
        }
        return this.discoveryClient;
    }
    @GetMapping("/payment/lb")
    public String paymentLB() {
        return "servicePort 8001";
    }

    @GetMapping("/payment/feign/timeout")
    public String paymentTimeout() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            log.error("{}", e);
        }
        return "8001";
    }
}
