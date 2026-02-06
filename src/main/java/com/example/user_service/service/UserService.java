package com.example.user_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.user_service.exception.DuplicateResourceException;
import com.example.user_service.exception.UserNotFoundException;
import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.dto.UserRequestDTO;
import com.example.user_service.dto.UserResponseDTO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {

	@Autowired
	private final UserRepository _repo;

	public UserService(UserRepository _repo) {
		this._repo = _repo;
	}

	private UserResponseDTO mapToResponse(User user) {
		UserResponseDTO dto = new UserResponseDTO();
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setAge(user.getAge());
		dto.setCreatedAt(user.getCreatedAt());
		dto.setUpdatedAt(user.getUpdatedAt());
		return dto;
	}

	public UserResponseDTO createUser(UserRequestDTO dto) {
		if (_repo.existsByEmail(dto.getEmail())) {
			throw new DuplicateResourceException("Email already exists: " + dto.getEmail());
		}

		User user = new User();
//    	user.setId(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setAge(dto.getAge());

		User savedUser = _repo.save(user);
		System.out.println("Saved ID = " + savedUser.getId());

		return mapToResponse(savedUser);
	}

	public Page<UserResponseDTO> getAllUsers(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);

		return _repo.findAllByDeletedFalse(pageable).map(this::mapToResponse);
	}

	public UserResponseDTO getUser(Long id) {
		User user = _repo.findByIdAndDeletedFalse(id).orElseThrow(() -> new UserNotFoundException("User not found"));
		return mapToResponse(user);
	}

//    public void deleteUser(long id)
//    {
//    	 if (!_repo.existsById(id)) {
//    	        throw new UserNotFoundException("User not found with id " + id);
//    	    }
//    	_repo.deleteById(id);
//
//    }

	@Transactional
	public void deleteUser(Long id) {
		User user = _repo.findByIdAndDeletedFalse(id).orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

		_repo.delete(user); // triggers @SQLDelete
	}
	
	
	public void restoreUser(Long id) {

	    User user = _repo.findByIdAndDeletedTrue(id)
	            .orElseThrow(() ->
	                    new UserNotFoundException("User not found with id " + id));

	    user.setDeleted(false);   //  restore

	    _repo.save(user);         //  persist
	}

	public UserResponseDTO updateUser(long id, UserRequestDTO updatedUser) 
	{

		User existingUser = _repo.findByIdAndDeletedFalse(id)
				.orElseThrow(() -> new UserNotFoundException("User not found with id " + id));

		// Update entity using DTO
		existingUser.setName(updatedUser.getName());
		existingUser.setEmail(updatedUser.getEmail());
		existingUser.setAge(updatedUser.getAge());

		User savedUser = _repo.save(existingUser);

		// Convert entity → response DTO
		return mapToResponse(savedUser);

	}

	public Page<UserResponseDTO> searchUsers(String name, Integer minAge, Integer maxAge, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);

		Page<User> users;

		if (name != null && minAge != null && maxAge != null) {
			users = _repo.findByNameContainingIgnoreCaseAndAgeBetween(name, minAge, maxAge, pageable);
		} else if (name != null) {
			users = _repo.findByNameContainingIgnoreCase(name, pageable);
		} else if (minAge != null && maxAge != null) {
			users = _repo.findByAgeBetween(minAge, maxAge, pageable);
		} else {
			users = _repo.findAllByDeletedFalse(pageable);
		}

		return users.map(this::mapToResponse);
	}

}