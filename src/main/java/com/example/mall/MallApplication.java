package com.example.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.mall.model.dao")
public class MallApplication {

	public static void main(String[] args) {
		try{
			SpringApplication.run(MallApplication.class, args);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
