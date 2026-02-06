package com.example.user_service.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//import com.example.order_service.model.User;
import com.example.user_service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

//    boolean existsByEmail(String email);

//	Page<User> findAllByDeletedFalse(Pageable pageable);
//	boolean existsByEmailAndDeletedFalse(String email);
//	List<User> findAllByDeletedFalse();
	Optional<User> findByIdAndDeletedFalse(Long id);
	Optional<User> findByIdAndDeletedTrue(Long id);
	Page<User> findAllByDeletedFalse(Pageable pageable);

	boolean existsByEmail(String email);
	
	Page<User>findByNameContainingIgnoreCase(
			String name,
			Pageable pageable
			);
	
	Page<User>findByAgeBetween(
			int minAge,
			int maxAge,
			Pageable pageable
			);
    Page<User> findByNameContainingIgnoreCaseAndAgeBetween(
            String name,
            int minAge,
            int maxAge,
            Pageable pageable
    );




}
