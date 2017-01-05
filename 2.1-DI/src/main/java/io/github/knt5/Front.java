package io.github.knt5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Front {
	@Autowired
	ArgumentResolver argumentResolver;
	@Autowired
	Calculator calculator;
	
	public void run() {
		System.out.print("Enter 2 parameters like 'a b': ");
		Argument argument = argumentResolver.resolve(System.in);
		
		int result = calculator.calc(argument.getA(), argument.getB());
		System.out.println("result: " + result);
	}
}
