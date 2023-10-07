package com.spring.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.spring.*", "com.spring.dao"})
@EnableJpaRepositories(basePackages = {"com.spring.dao.repositories"})
@EntityScan(basePackages = {"com.spring.dao.models", "com.spring.app.config"})
public class AppApplication {



	public static void main(String[] args) {



		ApplicationContext ctx = SpringApplication.run(AppApplication.class, args);



	}

}
