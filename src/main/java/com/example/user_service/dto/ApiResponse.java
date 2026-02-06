package com.example.user_service.dto;

public class ApiResponse<T> {

	private String message;
	private int status;
	private T data;

	// REQUIRED constructor
	public ApiResponse(String message, int status, T data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}

	// getters & setters
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
