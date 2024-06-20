package com.dame.users_microservice.repository;


import com.dame.users_microservice.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRole(String role);
	
}
