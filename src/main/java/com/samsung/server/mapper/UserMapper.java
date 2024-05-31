package com.samsung.server.mapper;

import com.samsung.server.controller.dto.UserProfileDto;
import com.samsung.server.controller.dto.UserRegisterDto;
import com.samsung.server.domain.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public User toUserEntity (UserProfileDto userProfileDto) {
        return User.builder()
                .id(userProfileDto.getId())
                .username(userProfileDto.getUsername())
                .photoUrl(userProfileDto.getPhotoUrl())
                .records(userProfileDto.getRecords())
                .places(userProfileDto.getPlaces())
                .build();
    }

    public User toUserEntity (UserRegisterDto userRegisterDto) {
        User user = User.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .build();

        if (userRegisterDto.getId() != null) user.setId(userRegisterDto.getId());

        return user;
    }

    public UserProfileDto toUserProfileDto(User user) {
        return UserProfileDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .photoUrl(user.getPhotoUrl())
                .records(user.getRecords())
                .places(user.getPlaces())
                .build();
    }

    public UserRegisterDto toUserRegisterDto(User user) {

        return UserRegisterDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }

}
