package com.samsung.server.service;

import com.samsung.server.controller.dto.UserProfileDto;
import com.samsung.server.controller.dto.UserRegisterDto;
import com.samsung.server.domain.Authority;
import com.samsung.server.domain.Record;
import com.samsung.server.domain.User;

import java.util.List;

public interface UserService {
    UserProfileDto add(UserRegisterDto userRegisterDto);

    List<UserProfileDto> getAll();

    UserProfileDto getById(long id);

    UserProfileDto update(long id, UserProfileDto userProfileDto);

    void update(Long id, Authority authority);

    void deleteById(long id);

    UserProfileDto getByUsername(String username);

    UserProfileDto addRecord(long userId, long recordId);
}
