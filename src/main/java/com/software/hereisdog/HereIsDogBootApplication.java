package com.software.hereisdog;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan("com.software.hereisdog.dao.mybatis.mapper") 
//메인 클래스가 하위 클래스 전부 스캔함. 
@SpringBootApplication
public class HereIsDogBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(HereIsDogBootApplication.class, args);
	}

}
