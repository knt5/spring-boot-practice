package io.github.knt5.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "customers")  // Guard from circular reference 
public class User {
	@Id
	private String username;
	
	@JsonIgnore
	private String encodedPassword;
	
	@JsonIgnore
	@OneToMany(
		cascade = CascadeType.ALL,  // Delete customers associated with the user 
		fetch = FetchType.LAZY,  // Lazy load customers
		mappedBy = "user")
	private List<Customer> customers;
}
