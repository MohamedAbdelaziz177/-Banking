package com.abdelaziz26.bank.eurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaserverApplication {

    public static void main(String[] args) {

        System.out.println("${server.port}");
        SpringApplication.run(EurekaserverApplication.class, args);
    }

}
