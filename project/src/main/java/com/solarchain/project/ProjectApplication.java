package com.solarchain.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication

@EnableFeignClients
@ComponentScan(basePackages = "com.solarchain.project.controller")
@ComponentScan("com.solarchain.project.services")
@ComponentScan("com.solarchain.project.repository")


public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
