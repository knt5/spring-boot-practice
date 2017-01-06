package io.github.knt5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import io.github.knt5.domain.Customer;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Override
	public void run(String... strings) throws Exception {
		// Create SQL
		String sql = "SELECT id, first_name, last_name FROM customers WHERE id = :id";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("id", 5);
		
		// Query
		Customer result = jdbcTemplate.queryForObject(sql, param, (resultSet, rowNum) -> new Customer(
				resultSet.getInt("id"),
				resultSet.getString("first_name"),
				resultSet.getString("last_name")
		));
		
		System.out.println("result = " + result);
	}
}
