package com.sparta.sch_user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SchUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchUserApplication.class, args);
    }

}
