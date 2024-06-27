package com.dame.users_microservice.restControllers;

import com.dame.users_microservice.entities.User;
import com.dame.users_microservice.repository.UserRepository;
import com.dame.users_microservice.service.UserServiceImpl;
import com.dame.users_microservice.service.register.RegistrationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
//@CrossOrigin(origins = "http://localhost:4200")
public class UserRestController {

	@Autowired
	UserRepository userRep;

	@Autowired
	UserServiceImpl userServiceImpl;

	@RequestMapping(path = "all",method = RequestMethod.GET)
	public List<User> getAllUsers() {
		return userRep.findAll();
	 }

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Optional<User> getUserRoles(@PathVariable long id){return userRep.findById(id); }

	@PostMapping("/register")
	public User register(@RequestBody RegistrationRequest request) {
		return userServiceImpl.registerUser(request);

	}


	@GetMapping("/verifyEmail/{token}")
	public User verifyEmail(@PathVariable("token") String token){
		return userServiceImpl.validateToken(token);
	}


}
