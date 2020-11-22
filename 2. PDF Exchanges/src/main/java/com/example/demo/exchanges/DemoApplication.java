package com.example.demo.exchanges;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "com.example.demo.exchanges",
        "com.example.demo.config",
        "com.example.demo.controllers",
        "com.example.demo.services"
})
public class DemoApplication  {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }


}
