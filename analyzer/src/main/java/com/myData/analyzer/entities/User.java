package com.myData.analyzer.entities;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

import java.util.List;

@Entity
@Data
public class User{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    private String username;
    
    @JsonIgnore
    private String password;
    
    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<Role> roles;
    
    private boolean active;
    
    
    public User() {}
    
    public User(String username, String password, List<Role> roles, boolean active) {
    	this.username = username;
    	this.password = password;
    	this.roles = roles;
    	this.active = active;
    }

}
