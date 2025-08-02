package com.demo.profile.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.profile.Entitys.UserProfile;

public interface IuserProfileRepo extends JpaRepository<UserProfile, Integer> {

}
