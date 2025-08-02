package com.demo.profile.DtoMapper;

import com.demo.profile.Entitys.Address;
import com.demo.profile.Entitys.UserProfile;
import com.demo.profile.model.AddressDTO;
import com.demo.profile.model.UserProfileDTO;

public class UserProfileMapper {

    public static UserProfile toEntity(UserProfileDTO dto) {
        if (dto == null) return null;

        Address address = new Address(
            dto.getAddress().getStreet(),
            dto.getAddress().getCity(),
            dto.getAddress().getDistrict(),
            dto.getAddress().getState(),
            dto.getAddress().getZipCode()
        );

        UserProfile entity = new UserProfile();
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAddress(address);

        return entity;
    }

    public static UserProfileDTO toDTO(UserProfile entity) {
        if (entity == null) return null;

        Address address = entity.getAddress();
        AddressDTO addressDTO = new AddressDTO(
            address.getStreet(),
            address.getCity(),
            address.getDistrict(),
            address.getState(),
            address.getZipCode()
        );

        return new UserProfileDTO(
            entity.getId(),
            entity.getFullName(),
            entity.getEmail(),
            entity.getPhone(),
            addressDTO,
            entity.getFullAddress(),
            entity.getCreatedDate(),
            entity.getUpdatedDate()
        );
    }
}
