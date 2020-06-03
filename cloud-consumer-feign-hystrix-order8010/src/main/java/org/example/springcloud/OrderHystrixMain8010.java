package org.example.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableEurekaClient
@EnableFeignClients
@EnableCircuitBreaker
public class OrderHystrixMain8010 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixMain8010.class, args);
    }
}
