package com.darknights.devigation;

import com.darknights.devigation.configuration.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class DevigationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevigationApplication.class, args);
    }

}
