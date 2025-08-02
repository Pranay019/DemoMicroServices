package com.demo.login.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class LoginDetails {

	private String username;

	private String password;

	private String email;

	private String token;
	
	private Integer id;

}
