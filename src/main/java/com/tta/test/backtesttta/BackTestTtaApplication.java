package com.tta.test.backtesttta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class BackTestTtaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackTestTtaApplication.class, args);
	}

}
