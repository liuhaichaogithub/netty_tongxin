package com.tongxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages="com.tongxin.mapper")
@ComponentScan(basePackages = {"com.tongxin","org.n3r.idworker"})
//扫描mapper包（mybatis）
/*@EnableScheduling
@Enable Async*/
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
