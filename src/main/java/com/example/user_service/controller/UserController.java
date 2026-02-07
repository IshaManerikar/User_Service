package com.example.user_service.controller;
import com.example.user_service.dto.UserResponseDTO;
import com.example.user_service.dto.ApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.user_service.service.UserService;
import jakarta.validation.Valid;
import com.example.user_service.dto.UserRequestDTO;


@RestController
@RequestMapping("/users")
public class UserController {

	private final UserService service;

	public UserController(UserService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@Valid @RequestBody UserRequestDTO dto) 
	{
		UserResponseDTO order = service.createUser(dto);

		ApiResponse<UserResponseDTO> response = new ApiResponse<>
		(
				"User created successfully",
				201, 
				order
		);

		return ResponseEntity.status(201).body(response);
	}

	@GetMapping
	public ResponseEntity<ApiResponse<Page<UserResponseDTO>>> 
	getAllUsers(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size
			    ) 
	{

		Page<UserResponseDTO> users = service.getAllUsers(page, size);

		ApiResponse<Page<UserResponseDTO>> response = new ApiResponse<>("Users fetched successfully", 200, users);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponseDTO>> getUser(@PathVariable long id) 
	{
		UserResponseDTO user = service.getUser(id);
		ApiResponse<UserResponseDTO> response = new ApiResponse<>
		(
				"User Details fetched successfully",
				200,
				user
		);

		return ResponseEntity.ok(response);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id)
	{
		service.deleteUser(id);
		ApiResponse<Void> response = new ApiResponse<>
		(
				"User deleted successfully",
				200,
				null
		);
		return ResponseEntity.ok(response);
	}
	
	@PatchMapping("restore/{id}")
	public ResponseEntity<ApiResponse<Void>> restoreDelete(@PathVariable Long id)
	{
		service.restoreUser(id);
		ApiResponse<Void> response = new ApiResponse<>
		(
				"User restored successfully",
				200,
				null
		);
		return ResponseEntity.ok(response);
	}

	//TODO Not saving in db 
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<UserResponseDTO>> 
	update(
			@PathVariable Long id,
			@Valid @RequestBody UserRequestDTO dto
		   ) 
	{

		UserResponseDTO user = service.updateUser(id, dto);

		ApiResponse<UserResponseDTO> response = new ApiResponse<>
		(
				"User info updated successfully",
				200, 
				user
		);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/search")
	public ResponseEntity<ApiResponse<Page<UserResponseDTO>>> searchUsers(
			@RequestParam(required = false) String name,
			@RequestParam(required = false) Integer minAge,
			@RequestParam(required = false) Integer maxAge,
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) 
	{
		Page<UserResponseDTO> result =
				service.searchUsers
				(
						name,
						minAge, 
						maxAge, 
						page, 
						size
				);

		return ResponseEntity.ok(new ApiResponse<>
		(
				"Users fetched successfully",
				200, 
				result
		)
		);
	}

}
