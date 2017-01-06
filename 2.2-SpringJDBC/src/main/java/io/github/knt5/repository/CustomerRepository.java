package io.github.knt5.repository;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.knt5.domain.Customer;

@Repository
@Transactional
public class CustomerRepository {
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate;
	
	SimpleJdbcInsert simpleJdbcInsert;
	
	@PostConstruct
	public void init() {
		simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) jdbcTemplate.getJdbcOperations())
				.withTableName("customers")
				.usingGeneratedKeyColumns("id");
	}
	
	private static final RowMapper<Customer> cutomerRowMapper = (resultSet, rowNum) -> new Customer(
			resultSet.getInt("id"),
			resultSet.getString("first_name"),
			resultSet.getString("last_name")
	);
	
	public List<Customer> findAll() {
		return jdbcTemplate.query(
				"SELECT id,first_name,last_name FROM customers ORDER BY id",
				cutomerRowMapper
		);
	}
	
	public Customer findOne(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return jdbcTemplate.queryForObject(
				"SELECT id,first_name,last_name FROM customers WHERE id=:id",
				param,
				cutomerRowMapper
		);
	}
	
	public Customer save(Customer customer) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(customer);
		if (customer.getId() == null) {
			// Insert
			Number key = simpleJdbcInsert.executeAndReturnKey(param);
			customer.setId(key.intValue());
		} else {
			// Update
			jdbcTemplate.update(
					"UPDATE customers SET first_name=:firstName,last_name=:lastName WHERE id=:id)",
					param
			);
		}
		
		return customer;
	}
	
	public void delete(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		jdbcTemplate.update(
				"DELETE FROM customers WHERE id=:id)",
				param
		);
	}
}
