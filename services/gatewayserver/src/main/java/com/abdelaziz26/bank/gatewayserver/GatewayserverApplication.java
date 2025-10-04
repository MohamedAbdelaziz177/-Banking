package com.abdelaziz26.bank.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r ->
                        r.path("/accounts/**")
                                .filters(f -> f
                                        .addRequestHeader("time-stamp", LocalDateTime.now().toString())
                                        .addResponseHeader("service", "ACC_SERVICE"))
                                .uri("lb://ACCOUNTS"))
                .route(r ->
                        r.path("/cards/**")
                                .filters(f -> f
                                        .addRequestHeader("time-stamp", LocalDateTime.now().toString())
                                        .addResponseHeader("service", "CARD_SERVICE"))
                                .uri("lb://CARDS"))
                .route(r ->
                        r.path("/loans/**")
                                .filters(f -> f
                                        .addRequestHeader("time-stamp", LocalDateTime.now().toString())
                                        .addResponseHeader("service", "LOAN_SERVICE")
                                )
                                .uri("lb://LOANS"))
                .build();
    }
}
