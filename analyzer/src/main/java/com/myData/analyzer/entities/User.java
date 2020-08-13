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
    
    @ManyToMany(fetch = FetchType.EAGER,cascade =
        {
                CascadeType.DETACH,
                CascadeType.MERGE,
                CascadeType.REFRESH,
                CascadeType.PERSIST
        },
        targetEntity = Role.class)
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")})
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
