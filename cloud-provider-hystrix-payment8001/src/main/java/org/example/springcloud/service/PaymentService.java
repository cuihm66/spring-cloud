package org.example.springcloud.service;

public interface PaymentService {
    String getPaymentOK(Integer id);

    String getPaymentTimeout(Integer id);
}
