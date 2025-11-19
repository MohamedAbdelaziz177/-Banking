package com.abdelaziz26.bank.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.springframework.cloud.gateway.support.RouteMetadataUtils.CONNECT_TIMEOUT_ATTR;
import static org.springframework.cloud.gateway.support.RouteMetadataUtils.RESPONSE_TIMEOUT_ATTR;

@SpringBootApplication
public class GatewayserverApplication {

    @Bean
    public RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(1, 2, 2);
    }

    @Bean
    public KeyResolver keyResolver() {
        return exchange -> Mono.just(exchange
                .getRequest()
                .getRemoteAddress()
                .getAddress()
                .getHostAddress()
        );
    }

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, RedisRateLimiter redisRateLimiter, KeyResolver keyResolver) {
        return builder.routes()
                .route(r ->
                        r.path("/api/accounts/**", "/api/transactions/**", "/api/customer/**")

                                .filters(f -> f
                                        .addRequestHeader("time-stamp", LocalDateTime.now().toString())


                                        .addResponseHeader("service", "ACC_SERVICE")

                                        /*  --- circuit breaker's timeout & retry 's autoconfig always takes higher priority than
                                         explicitly added ones thus, if u need to customize and test them like here -> comment circuit breaker */

                                        .circuitBreaker(cb -> cb
                                                .setName("accounts-circuit-breaker")
                                                .setFallbackUri("forward:/fallback/contactSupport")
                                        )

                                        .retry(rc ->
                                                rc
                                                        .setRetries(3)
                                                        .setMethods(HttpMethod.GET)
                                                        .setBackoff(Duration.ofSeconds(1), Duration.ofSeconds(10),2, true)
                                        )
                                        .requestRateLimiter(rlc -> rlc.setRateLimiter(redisRateLimiter()).setKeyResolver(keyResolver()))
                                )
                                .metadata(RESPONSE_TIMEOUT_ATTR, 200)
                                .metadata(CONNECT_TIMEOUT_ATTR, 200)
                                .uri("lb://ACCOUNTS")
                )


                .route(r ->
                        r.path("/api/cards/**")
                                .filters(f -> f
                                        .addRequestHeader("time-stamp", LocalDateTime.now().toString())
                                        .addResponseHeader("service", "CARD_SERVICE")
                                        .circuitBreaker(config -> {
                                            config.setName("cards-circuit-breaker");
                                            config.setFallbackUri("forward:/fallback/contactSupport");
                                        })
                                        .requestRateLimiter(rlc -> rlc.setRateLimiter(redisRateLimiter).setKeyResolver(keyResolver))
                                )
                                .uri("lb://CARDS"))
                .route(r ->
                        r.path("/api/loans/**")
                                .filters(f -> f
                                        .addRequestHeader("time-stamp", LocalDateTime.now().toString())
                                        .addResponseHeader("service", "LOAN_SERVICE")
                                        .requestRateLimiter(rlc -> rlc.setRateLimiter(redisRateLimiter).setKeyResolver(keyResolver))
                                )
                                .uri("lb://LOANS"))
                .build();
    }
}
