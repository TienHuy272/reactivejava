package com.spring.reactive.router;

import com.spring.reactive.dto.Customer;
import com.spring.reactive.handler.CustomerHandler;
import com.spring.reactive.handler.CustomerStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler customerHandler;

    @Autowired
    private CustomerStreamHandler customerStreamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        //it call method reference <input,output>
        Function<ServerRequest, Mono<ServerResponse>> methodRef = customerHandler::loadCustomer;
        Function<ServerRequest, Mono<ServerResponse>> methodStreamRef = customerStreamHandler::getCustomers;
        return RouterFunctions.route()
                .GET("/router/customers", methodRef::apply)
                .GET("/router/customers/stream", methodStreamRef::apply)
                .GET("/router/customer/{input}", customerHandler::findCustomer )
                .POST("/router/customer/save", customerHandler::saveCustomer)
                .build();
    }

}
