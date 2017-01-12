package io.github.knt5.test.api;

import io.restassured.RestAssured;

public class ApiTest {
	public ApiTest get(String url) {
		RestAssured
			.get(url)
			.then()
			.statusCode(200);
		
		return this;
	}
}
