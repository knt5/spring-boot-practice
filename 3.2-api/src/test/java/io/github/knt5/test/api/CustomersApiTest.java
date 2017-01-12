package io.github.knt5.test.api;

import org.junit.Test;

public class CustomersApiTest extends ApiTest {
	@Test public void test1_1() {
		get("http://localhost:8080/api/customers");
	}
}
