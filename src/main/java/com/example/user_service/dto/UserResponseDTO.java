package com.example.user_service.dto;

import java.time.LocalDateTime;

public class UserResponseDTO {
	
	 	private Long id;
	    private String name;
	    private String email;
	    private int age;
	    private LocalDateTime createdAt;
	    private LocalDateTime updatedAt;

	    
	    public Long getId()
	    {
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
	    public LocalDateTime getCreatedAt() {
	        return createdAt;
	    }

	    public void setCreatedAt(LocalDateTime createdAt) {
	        this.createdAt = createdAt;
	    }

	    public LocalDateTime getUpdatedAt() {
	        return updatedAt;
	    }

	    public void setUpdatedAt(LocalDateTime updatedAt) {
	        this.updatedAt = updatedAt;
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
