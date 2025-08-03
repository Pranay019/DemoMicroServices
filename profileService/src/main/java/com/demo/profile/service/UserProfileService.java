package com.demo.profile.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.profile.Entitys.UserProfile;
import com.demo.profile.model.UserProfileDTO;
import com.demo.profile.repositary.UserRepo;
@Service
public class UserProfileService implements IuserProfileService {
	@Autowired
	UserRepo userRepo;

	@Override
	public UserProfileDTO saveProfile(UserProfileDTO userProfileDTO) {

		return this.userRepo.saveProfile(userProfileDTO);
	}

	@Override
	public UserProfile fetchUser(Integer id) {
		
		return this.userRepo.fetchuser(id);
	}

	@Override
	public List<UserProfile> getallusers() {
	
		
		List<UserProfile> allusers =this.userRepo.getallusers();
		
		return allusers;
	}

	@Override
	public UserProfile updateUser(UserProfile user) {
		
		return this.userRepo.updateUser(user);
	}

}
