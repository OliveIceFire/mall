package com.example.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2//启用Swagger2
@MapperScan("com.example.mall.model.dao")
@EnableCaching//开启缓存
public class MallApplication {

	public static void main(String[] args) {
		try{
			SpringApplication.run(MallApplication.class, args);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
