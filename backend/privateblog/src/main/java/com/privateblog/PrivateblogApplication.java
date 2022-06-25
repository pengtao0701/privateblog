package com.privateblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EntityScan("com.privateblog.mapper")
@ComponentScan(basePackages = { "com.privateblog.controller", "com.privateblog.service", "com.privateblog.config" })
public class PrivateblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrivateblogApplication.class, args);
	}

}
