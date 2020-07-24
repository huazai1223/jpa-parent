package com.jiadonghua.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceAppliction {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAppliction.class);
    }
}
