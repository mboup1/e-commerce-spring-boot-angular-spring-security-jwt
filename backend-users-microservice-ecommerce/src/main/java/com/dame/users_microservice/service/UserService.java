package com.dame.users_microservice.service;

import com.dame.users_microservice.entities.Role;
import com.dame.users_microservice.entities.User;
import com.dame.users_microservice.service.register.RegistrationRequest;

public interface UserService {
    User saveUser(User user);
    User addRoleToUser(String username, String rolename);
    Role addRole(Role role);
    User findUserByUsername(String username);
    User registerUser(RegistrationRequest request);
    void sendEmailUser(User user, String code);
    User validateToken(String code);
}
