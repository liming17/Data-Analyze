package com.myData.analyzer.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistration {
	private String username;
	private String password;
	private String passwordConfirmation;

}
