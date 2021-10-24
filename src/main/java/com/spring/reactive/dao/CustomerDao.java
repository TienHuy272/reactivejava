package com.spring.reactive.dao;

import com.spring.reactive.dto.Customer;
import reactor.core.publisher.Flux;

import java.util.List;

public interface CustomerDao {
    List<Customer> getCustomers();
    Flux<Customer> getCustomersStream();
    Flux<Customer> getCustomerList();
}
