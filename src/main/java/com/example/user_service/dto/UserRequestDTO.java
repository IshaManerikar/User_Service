package com.example.user_service.dto;

import jakarta.validation.constraints.*;

public class UserRequestDTO {

	private long id;
	@Pattern(regexp = "^[A-Za-z ]+$", message = "Name must contain only letters and spaces")
	@NotBlank(message = "name is required!")
	private String name;

	@NotBlank(message = "Email is required!")
	@Email(message = "Enter valid email!")
	private String email;

	@Min(value = 18, message = "Age should be above 18!")
	private Integer age;

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
