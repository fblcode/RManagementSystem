package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.example.demo")
public class WebProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(WebProject1Application.class, args);
	}

}
