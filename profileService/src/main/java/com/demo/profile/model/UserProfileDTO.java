package com.demo.profile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
