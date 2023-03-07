package com.springboot.blog.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"roles"})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "email", nullable = false, unique = true)
	private String email;
	
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	
	@Column(name = "name")
	private String name;
			
	@Column(name = "password", nullable = false)
	private String password;
	
	//	cascade > 부모에 대해 어떤 작업을 수행할 때마다 해당 작업이 자식에게도 적용됨
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "users_roles"
			 , joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
			 , inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
			)
	private Set<Role> roles;
	
	
}
