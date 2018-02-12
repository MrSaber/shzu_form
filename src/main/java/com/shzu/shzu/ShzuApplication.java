package com.shzu.shzu;

import com.shzu.shzu.filter.AuthInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@SpringBootApplication
@ServletComponentScan(basePackages = "com.shzu.shzu.filter")
@EnableTransactionManagement
public class ShzuApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShzuApplication.class, args);
	}

	@Configuration
	static class WebMvcConfigurer extends WebMvcConfigurerAdapter {

		@Override
		public void addInterceptors(InterceptorRegistry registry) {
			registry.addInterceptor(new AuthInterceptor());
		}
	}

}
