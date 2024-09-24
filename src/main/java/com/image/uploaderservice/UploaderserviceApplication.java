package com.image.uploaderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
@ComponentScan({ "com.image.uploaderservice" })
public class UploaderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UploaderserviceApplication.class, args);
	}

}
