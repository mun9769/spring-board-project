package com.fastcampus.projectboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan // ch02.09 06:30
@SpringBootApplication
public class SpringBoardProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBoardProjectApplication.class, args);
    }

}
