package com.dame.users_microservice;

import com.dame.users_microservice.entities.Role;
import com.dame.users_microservice.entities.User;
import com.dame.users_microservice.repository.UserRepository;
import com.dame.users_microservice.service.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UsersMicroserviceApplication {

	@Autowired
	UserService userServiceImpl;

	@Autowired
	UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(UsersMicroserviceApplication.class, args);
	}

   @PostConstruct
	void init_users() {
	   if (userRepository.count() == 0) {
		   //ajouter les rôles
		   userServiceImpl.addRole(new Role(null, "ADMIN"));
		   userServiceImpl.addRole(new Role(null, "USER"));

		   //ajouter les users
		   userServiceImpl.saveUser(new User(null, "admin", "123", true, null));
		   userServiceImpl.saveUser(new User(null, "dame", "123", true, "dame@dame.com"));
		   userServiceImpl.saveUser(new User(null, "abdou", "123", true, null));

		   //ajouter les rôles aux users
		   userServiceImpl.addRoleToUser("admin", "ADMIN");
		   userServiceImpl.addRoleToUser("dame", "ADMIN");
		   userServiceImpl.addRoleToUser("dame", "USER");
		   userServiceImpl.addRoleToUser("admin", "USER");
		   userServiceImpl.addRoleToUser("abdou", "USER");
	   }
   }

	@Bean
	BCryptPasswordEncoder getBCE() {
		return new BCryptPasswordEncoder();

	}


}
