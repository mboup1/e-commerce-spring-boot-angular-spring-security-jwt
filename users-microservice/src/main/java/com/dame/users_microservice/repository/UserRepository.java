package com.dame.users_microservice.repository;


import com.dame.users_microservice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

		User findByUsername(String username);

}
