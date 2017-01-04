package io.github.knt5;

import java.util.Scanner;

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
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter 2 parameters like 'a b': ");
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		
		Calculator calculator = app.getBean(Calculator.class);
		int result = calculator.calc(a, b);
		System.out.println("result: " + result);
		scanner.close();
	}
}
