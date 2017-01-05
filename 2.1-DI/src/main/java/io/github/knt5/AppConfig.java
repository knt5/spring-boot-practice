package io.github.knt5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	@Bean
	public Calculator calculator() {
		return new AdditionCalculator();
		//return new MultiplicationCalculator();
	}
	
	@Bean
	public ArgumentResolver argumentResolver() {
		return new ScanArgumentResolver();
	}
	
	@Bean
	public Front front() {
		return new Front();
	}
}
