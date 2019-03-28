package com.chenyifaer.back;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableEurekaClient
@SpringBootApplication
public class BackServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackServiceApplication.class, args);
    }

}
