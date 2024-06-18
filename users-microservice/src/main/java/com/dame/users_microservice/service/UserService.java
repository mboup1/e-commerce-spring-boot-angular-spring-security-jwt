package com.dame.users_microservice.service;


import com.dame.users_microservice.entities.Role;
import com.dame.users_microservice.entities.User;
import com.dame.users_microservice.repository.RoleRepository;
import com.dame.users_microservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class UserService {

	@Autowired
	private UserRepository userRep;

	@Autowired
	private RoleRepository roleRep;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRep.save(user);
	}

	public User addRoleToUser(String username, String rolename) {
		User usr = userRep.findByUsername(username);
		Role r = roleRep.findByRole(rolename);

		usr.getRoles().add(r);
		return userRep.save(usr);
	}

	public Role addRole(Role role) {
		return roleRep.save(role);
	}

	public User findUserByUsername(String username) {
		return userRep.findByUsername(username);
	}
}
