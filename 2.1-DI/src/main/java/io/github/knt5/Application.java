package io.github.knt5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

//@SpringBootApplication
@EnableAutoConfiguration
@Import(AppConfig.class)
public class Application {
	public static void main(String[] args) {
		ApplicationContext app = SpringApplication.run(Application.class, args);
		
		Front front = app.getBean(Front.class);
		front.run();
	}
}
