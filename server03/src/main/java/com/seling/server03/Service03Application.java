package com.seling.server03;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class Service03Application {

	public static void main(String[] args) {
		SpringApplication.run(Service03Application.class, args);
	}

}
