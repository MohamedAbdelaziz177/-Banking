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
                        r.path("/api/accounts/**", "/api/transactions/**", "/api/customer/**")
                                .filters(f -> f
                                        .addRequestHeader("time-stamp", "#{T(java.time.LocalDateTime).now()}")
                                        .addResponseHeader("service", "ACC_SERVICE")
                                       // .rewritePath("/api/accounts/(?<segment>.*)", "/${segment}")
                                        .circuitBreaker(cb -> cb
                                                .setName("accounts-circuit-breaker")
                                                .setFallbackUri("forward:/fallback/contactSupport"))
                                )
                                .uri("lb://ACCOUNTS")
                )


                .route(r ->
                        r.path("/api/cards/**")
                                .filters(f -> f
                                        .addRequestHeader("time-stamp", "#{T(java.time.LocalDateTime).now()}")
                                        .addResponseHeader("service", "CARD_SERVICE")
                                        // .rewritePath("/api/cards/(?<segment>.*)", "/${segment}")
                                )
                                .uri("lb://CARDS"))
                .route(r ->
                        r.path("/api/loans/**")
                                .filters(f -> f
                                        .addRequestHeader("time-stamp", "#{T(java.time.LocalDateTime).now()}")
                                        .addResponseHeader("service", "LOAN_SERVICE")
                                       // .rewritePath("/api/loans/(?<segment>.*)", "/${segment}")
                                )
                                .uri("lb://LOANS"))
                .build();
    }
}
