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
		
		System.out.print("Enter 2 parameters like 'a b': ");
		ArgumentResolver argumentResolver = app.getBean(ArgumentResolver.class);
		Argument argument = argumentResolver.resolve(System.in);
		
		Calculator calculator = app.getBean(Calculator.class);
		int result = calculator.calc(argument.getA(), argument.getB());
		System.out.println("result: " + result);
	}
}
