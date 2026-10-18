package me.vinald.project_two.config;

import me.vinald.project_two.MyFirstClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ApplicationConfig {
	@Bean
	@Primary
	public MyFirstClass myFirstBean() {
		return new MyFirstClass();
	}

	@Bean
	public MyFirstClass mySecondBean() {
		return new MyFirstClass();
	}
}
