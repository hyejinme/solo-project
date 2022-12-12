package com.hj.soloproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Section4 To-do App 솔로 프로젝트 실습
 * https://todobackend.com
 */
@SpringBootApplication
public class SoloProjectApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(SoloProjectApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(SoloProjectApplication.class);
	}

}
