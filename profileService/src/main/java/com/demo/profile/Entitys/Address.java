package com.demo.profile.Entitys;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "STREET", nullable = false)
	private String street;

	@Column(name = "CITY", nullable = false)
	private String city;

	@Column(name = "DISTRICT", nullable = false)
	private String district;

	@Column(name = "STATE", nullable = false)
	private String state;

	@Column(name = "ZIP_CODE", nullable = false)
	private String zipCode;
}
