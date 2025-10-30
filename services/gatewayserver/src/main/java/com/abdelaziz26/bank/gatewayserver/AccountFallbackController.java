package com.abdelaziz26.bank.gatewayserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController @RequestMapping("/fallback")
public class AccountFallbackController {

    @RequestMapping("/contactSupport")
    public Mono<ResponseEntity<String>> contactSupport() {
        return Mono.just(
                ResponseEntity
                        .status(200)
                        .body("Fallback response: service temporarily unavailable")
        );
    }

}
