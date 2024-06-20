package com.dame.users_microservice.security;


import com.dame.users_microservice.entities.User;
import com.dame.users_microservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	UserService userService;

@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user = userService.findUserByUsername(username);

if (user==null)
    throw new UsernameNotFoundException("User not found !");

	List<GrantedAuthority> auths = new ArrayList<>();

	 user.getRoles().forEach(role -> {
		 //Si l'utilisateur existe boucler (forEach) pour récupérer les roles
		 GrantedAuthority auhority = new SimpleGrantedAuthority(role.getRole());
		 //ajouter les roles à l'utilisateur
		 auths.add(auhority);
	 });

	return new org.springframework.security.core.
			userdetails.User(user.getUsername(),user.getPassword(),auths);
  }
}
