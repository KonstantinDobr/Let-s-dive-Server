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
                .name(userProfileDto.getName())
                .username(userProfileDto.getUsername())
                .email(userProfileDto.getEmail())
                .photoUrl(userProfileDto.getPhotoUrl())
                .build();
    }

    public User toUserEntity (UserRegisterDto userRegisterDto) {
        User user = User.builder()
                .username(userRegisterDto.getUsername())
                .password(userRegisterDto.getPassword())
                .email(userRegisterDto.getEmail())
                .build();

        if (userRegisterDto.getId() != null) user.setId(userRegisterDto.getId());

        return user;
    }

    public UserProfileDto toUserProfileDto(User user) {
        return UserProfileDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .photoUrl(user.getPhotoUrl())
                .build();
    }

    public UserRegisterDto toUserRegisterDto(User user) {

        return UserRegisterDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getPassword())
                .build();
    }

}
