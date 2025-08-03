package com.demo.profile.service;



import java.util.List;

import com.demo.profile.Entitys.UserProfile;
import com.demo.profile.model.UserProfileDTO;


public interface IuserProfileService {
	 UserProfileDTO saveProfile(UserProfileDTO userProfileDTO);

	UserProfile fetchUser(Integer id);

	List<UserProfile> getallusers();

	UserProfile updateUser(UserProfile user);
}
