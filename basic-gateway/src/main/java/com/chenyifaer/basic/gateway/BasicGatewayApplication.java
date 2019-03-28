package com.chenyifaer.basic.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class BasicGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(BasicGatewayApplication.class, args);
    }

}
