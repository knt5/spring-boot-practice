package io.github.knt5;

import org.springframework.stereotype.Component;

@Component
public class AdditionCalculator implements Calculator {
	@Override
	public int calc(int a, int b) {
		return a + b;
	}
}
