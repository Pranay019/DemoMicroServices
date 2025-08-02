package com.demo.profile.repositary;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.demo.profile.DtoMapper.UserProfileMapper;
import com.demo.profile.Entitys.UserProfile;
import com.demo.profile.model.UserProfileDTO;

@Repository
public class UserRepo {

	@Autowired
	private IuserProfileRepo userRepo;

	public UserProfileDTO saveProfile(UserProfileDTO dto) {
		UserProfile entity = UserProfileMapper.toEntity(dto);
		UserProfile savedEntity = userRepo.save(entity);
		return UserProfileMapper.toDTO(savedEntity);
	}

	public UserProfile fetchuser(Integer id) {

		Optional<UserProfile> user = this.userRepo.findById(id);

		if (user.isEmpty()) {
			throw new RuntimeException("No user Found With : " + id);
		}

		return user.get();
	}

	public List<UserProfile> getallusers() {

		List<UserProfile> allUSers = this.userRepo.findAll();

		if (allUSers == null) {
			throw new RuntimeException("No entries Found In User database");
		}

		return allUSers;
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
