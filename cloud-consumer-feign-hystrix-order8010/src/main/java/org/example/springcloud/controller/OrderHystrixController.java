package org.example.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.example.springcloud.service.PaymentHystrixService;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/consumer")
//@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
@Slf4j
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping("/payment/ok/{id}")
    public String getPaymentOk(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.getPaymentOK(id);
        log.info("=======result:{}", result);
        return result;
    }

    private String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "消费者80，对方支付系统繁忙，或自己运行出错请检查自己，请10秒后再试。";
    }

    @GetMapping("/payment/timeout/{id}")
        @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
 //   @HystrixCommand  //全局使用
    public String getPaymentTimeout(@PathVariable("id") Integer id) {
          //     int age = 10 / 0;
        String result = paymentHystrixService.getPaymentTimeout(id);
        log.info("=======result  :{}", result);
        return result;
    }

    /**
     * 全局fallback方法
     *
     * @return
     */
    private String payment_Global_FallbackMethod() {
        return "Global异常处理信息,请稍后再试。";
    }
}
