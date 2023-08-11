package com.darknights.devigation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class DevigationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevigationApplication.class, args);
    }

}
