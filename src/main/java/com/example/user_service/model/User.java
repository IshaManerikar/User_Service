package com.example.user_service.model;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.*;

import jakarta.validation.constraints.*;
//import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
@SQLDelete(sql = "UPDATE users SET deleted = true WHERE id = ?")
//@Where(clause = "deleted = false")

public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name is required")
	private String name;

	@Column(nullable = false, unique = true)
	@Email(message = "Email should be valid")
	@NotBlank(message = "Email is required")
	private String email;

	@Min(value = 18, message = "Age must be at least 18")
	private int age;

	// GETTERS
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public int getAge() {
		return age;
	}

	// SETTERS
	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
