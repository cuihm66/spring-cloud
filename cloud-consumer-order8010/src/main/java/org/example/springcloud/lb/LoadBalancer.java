package org.example.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

public interface LoadBalancer {
    // 收集服务器总共有多少台能够提供服务的机器，并放到 list 里面     
    ServiceInstance instances (List<ServiceInstance> serviceInstances);
}
