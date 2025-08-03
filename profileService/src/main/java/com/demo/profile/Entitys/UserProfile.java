package com.demo.profile.Entitys;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USER_PROFILE_DETAILS")
@EntityListeners(AuditingEntityListener.class)
@Data
@JacksonXmlRootElement(localName = "userProfile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FULL_NAME", nullable = false)
    private String fullName;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Embedded
    private Address address;

    @Column(name = "FULL_ADDRESS")
    private String fullAddress;

    @LastModifiedDate
    @Column(name = "UPDATED_DATE", updatable = true)
    private LocalDateTime updatedDate;

    @CreatedDate
    @Column(name = "CREATED_DATE", updatable = false)
    private LocalDateTime createdDate;

    @PrePersist
    @PreUpdate
    private void generateFullAddress() {
        if (address != null) {
            this.fullAddress = Stream.of(address.getStreet(), address.getCity(), address.getState(), address.getZipCode())
                    .filter(Objects::nonNull)
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .collect(Collectors.joining(", "));
        }
    }
}
