package com.cmpe273.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.cmpe273.kafka.Consumer;

@SpringBootApplication
@ComponentScan(basePackages={ "com.cmpe273.controller","com.cmpe273.dao","com.cmpe273.kafka"})
public class SpringConfig {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfig.class);
		Consumer consumer=new Consumer();
		consumer.receive();
	}
}
