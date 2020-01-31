package com.mpocket.disk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mpocket.disk.dao")
public class DiskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiskApplication.class, args);
	}

}
