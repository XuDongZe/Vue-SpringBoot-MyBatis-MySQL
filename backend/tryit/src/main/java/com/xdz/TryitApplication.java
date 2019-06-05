package com.xdz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import tk.mybatis.spring.annotation.MapperScan;

//springboot项目入口文件
@SpringBootApplication
//扫描通用mapper的dao接口
@MapperScan("com.xdz.dao")
//扫描com.xdz.config目录下的配置文件
@Configuration 
public class TryitApplication {
	public static void main(String[] args) {
		SpringApplication.run(TryitApplication.class, args);
	}
}
