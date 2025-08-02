package com.demo.login.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDTO {

	private Integer id;
	private String fullName;
	private String email;
	private String phone;

	private AddressDTO address;

	private String fullAddress;

	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
}