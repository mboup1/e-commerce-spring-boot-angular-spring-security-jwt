package com.dame.users_microservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data 
@NoArgsConstructor @AllArgsConstructor
@Entity
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long user_id;

 @Column(unique=true)
	private String username;
	private String password;
	private Boolean enabled;
	private String email;


	@ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="user_role",joinColumns = @JoinColumn(name="user_id") , 
			   inverseJoinColumns = @JoinColumn(name="role_id")) 
	private List<Role> roles;


	public User(Long user_id, String username, String password, Boolean enabled, String email) {
		this.user_id = user_id;
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.email = email;
	}
}
