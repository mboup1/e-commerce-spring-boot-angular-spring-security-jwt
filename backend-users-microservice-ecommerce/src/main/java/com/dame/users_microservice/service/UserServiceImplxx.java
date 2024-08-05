//package com.dame.users_microservice.service;
//
//
//import com.dame.users_microservice.entities.Role;
//import com.dame.users_microservice.entities.User;
//import com.dame.users_microservice.repository.RoleRepository;
//import com.dame.users_microservice.repository.UserRepository;
//import com.dame.users_microservice.service.exceptions.EmailAlreadyExistsException;
//import com.dame.users_microservice.service.exceptions.InvalidTokenException;
//import com.dame.users_microservice.service.register.RegistrationRequest;
//
//import com.dame.users_microservice.service.exceptions.ExpiredTokenException;
//import com.dame.users_microservice.service.register.VerificationToken;
//import com.dame.users_microservice.service.register.VerificationTokenRepository;
//import com.dame.users_microservice.util.EmailSender;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
//@Transactional
//@Service
//public class UserServiceImpl {
//
//	@Autowired
//	private UserRepository userRep;
//
//	@Autowired
//	private RoleRepository roleRep;
//
//	@Autowired
//	private BCryptPasswordEncoder bCryptPasswordEncoder;
//
//	@Autowired
//	VerificationTokenRepository verificationTokenRepo;
//
//	@Autowired
//	EmailSender emailSender;
//
//	public User saveUser(User user) {
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//		return userRep.save(user);
//	}
//
//	public User addRoleToUser(String username, String rolename) {
//		User usr = userRep.findByUsername(username);
//		Role r = roleRep.findByRole(rolename);
//
//		usr.getRoles().add(r);
//		return userRep.save(usr);
//	}
//
//	public Role addRole(Role role) {
//		return roleRep.save(role);
//	}
//
//	public User findUserByUsername(String username) {
//		return userRep.findByUsername(username);
//	}
//
//	public User registerUser(RegistrationRequest request) {
//
//		Optional<User> optionalUser = userRep.findByEmail(request.getEmail());
//		if(optionalUser.isPresent())
//			throw new EmailAlreadyExistsException("Email déjà existant!");
//
//		User newUser = new User();
//		newUser.setUsername(request.getUsername());
//		newUser.setEmail(request.getEmail());
//
//		newUser.setPassword( bCryptPasswordEncoder.encode( request.getPassword() )  );
//		newUser.setEnabled(false);
//
//		userRep.save(newUser);
//
//		Role r = roleRep.findByRole("USER");
//		List<Role> roles = new ArrayList<>();
//		roles.add(r);
//		newUser.setRoles(roles);
//
//		//génére le code secret
//		String code = this.generateCode();
//
//		VerificationToken token = new VerificationToken(code, newUser);
//		verificationTokenRepo.save(token);
//
//		//envoyer le code par email à l'utilisateur
//		sendEmailUser(newUser,token.getToken());
//
//
//		return userRep.save(newUser);
//	}
//
//	private String generateCode() {
//		Random random = new Random();
//		Integer code = 100000 + random.nextInt(900000);
//
//		return code.toString();
//
//	}
//
//	public void sendEmailUser(User u, String code) {
//		String emailBody ="Bonjour "+ "<h1>"+u.getUsername() +"</h1>" +
//				" Votre code de validation est "+"<h1>"+code+"</h1>";
//
//		emailSender.sendEmail(u.getEmail(), emailBody);
//	}
//
//	public User validateToken(String code) {
//		VerificationToken token = verificationTokenRepo.findByToken(code);
//
//		if(token == null){
//			throw new InvalidTokenException("Invalid Token !!!!!!!");
//		}
//
//		User user = token.getUser();
//
//		Calendar calendar = Calendar.getInstance();
//
//		if ((token.getExpirationTime().getTime() - calendar.getTime().getTime()) <= 0){
//			verificationTokenRepo.delete(token);
//			throw new ExpiredTokenException("expired Token");
//		}
//
//		user.setEnabled(true);
//		userRep.save(user);
//		return user;
//	}
//}
