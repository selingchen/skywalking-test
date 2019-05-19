package com.seling.server04;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
@MapperScan("com.seling.server04.entity.mapper")
@SpringBootApplication(scanBasePackages = {"com.seling"})
@EnableTransactionManagement
public class Service04Application {

	public static void main(String[] args) {
		SpringApplication.run(Service04Application.class, args);
	}

}
