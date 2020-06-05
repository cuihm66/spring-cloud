package org.example.springcloud.controller;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.springcloud.entities.CommonResult;
import org.example.springcloud.entities.Payment;
import org.example.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    private static final String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE";
    //private static final String PAYMENT_URL = "CLOUD-PAYMENT-SERVICE";
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/consumer/payment")
    public CommonResult<Payment> create(Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment", payment, CommonResult.class);
    }

    @GetMapping("/consumer/payment/{id}")
    public CommonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/" + id, CommonResult.class);
    }

    @Resource
    private DiscoveryClient discoveryClient;
    @Resource
    private LoadBalancer loadBalancer;
    @GetMapping("consumer/payment/lb")
    public String getPaymentLB() {
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(instances == null || instances.size() < 1) {
            return null;
        }
        ServiceInstance serviceInstance = loadBalancer.instances(instances);
        URI uri = serviceInstance.getUri();
        return  restTemplate.getForObject(uri + "payment/lb", String.class);
    }
}
