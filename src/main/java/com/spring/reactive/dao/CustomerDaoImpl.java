package com.spring.reactive.dao;

import com.spring.reactive.dto.Customer;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Repository
public class CustomerDaoImpl implements  CustomerDao{

    private static void sleepExecution(int i) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customer> getCustomers() {
        return IntStream.rangeClosed(1, 50)
                .peek(CustomerDaoImpl::sleepExecution)
                .peek(i -> System.out.println("processing count " + i ))
                .mapToObj(i -> new Customer(i, "customer" + i))
                .collect(Collectors.toList());
    }

    @Override
    public Flux<Customer> getCustomersStream() {
        return Flux.range(1, 50)
                .delayElements(Duration.ofSeconds(1))
                .doOnNext(i -> System.out.println("processing count " + i ))
                .map(i -> new Customer(i, "customer" + i));
    }

    @Override
    public Flux<Customer> getCustomerList() {
        return Flux.range(1, 50)
                .doOnNext(i -> System.out.println("processing count " + i ))
                .map(i -> new Customer(i, "customer" + i));
    }
}
