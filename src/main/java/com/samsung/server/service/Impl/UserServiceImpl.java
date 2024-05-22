package com.samsung.server.service.Impl;

import com.samsung.server.controller.dto.UserProfileDto;
import com.samsung.server.controller.dto.UserRegisterDto;
import com.samsung.server.dao.AuthorityDao;
import com.samsung.server.dao.UserDao;
import com.samsung.server.domain.Authority;
import com.samsung.server.domain.User;
import com.samsung.server.exception.UserAlreadyExistsException;
import com.samsung.server.exception.UserNotFoundException;
import com.samsung.server.mapper.UserMapper;
import com.samsung.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final AuthorityDao authorityDao;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserProfileDto add(UserRegisterDto userRegisterDto) {
        if (userDao.findByUsername(userRegisterDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists!");
        }

        Optional<Authority> optionalAuthority = authorityDao.findByAuthority("ROLE_USER");
        if (!optionalAuthority.isPresent()) throw new RuntimeException("Authority not found!");

        User user = UserMapper.toUserEntity(userRegisterDto);
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        Set<Authority> authoritySet = new HashSet<>();
        authoritySet.add(optionalAuthority.get());
        user.setAuthorities(authoritySet);

        return UserMapper.toUserProfileDto(userDao.save(user));
    }

    @Override
    public List<UserProfileDto> getAll() {
        return userDao.findAll()
                .stream()
                .map(UserMapper::toUserProfileDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfileDto getById(long id) {
        Optional<User> optionalUser = userDao.findById(id);

        if (!optionalUser.isPresent()) throw new UserNotFoundException("User with Id " + id + " not found");

        return UserMapper.toUserProfileDto(optionalUser.get());
    }

    @Override
    public UserProfileDto update(long id, UserProfileDto userProfileDto) {
        Optional<User> optionalUser = userDao.findById(id);

        if (!optionalUser.isPresent()) throw new UserNotFoundException("User with Id " + id + " not found");

        User user = optionalUser.get();
        if (userProfileDto.getUsername() != null) user.setUsername(userProfileDto.getUsername());
        if (userProfileDto.getPhotoUrl() != null) user.setPhotoUrl(userProfileDto.getPhotoUrl());

        return UserMapper.toUserProfileDto(userDao.save(user));
    }

    @Override
    public void update(Long id, Authority authority) {
        Optional<User> userOptional = userDao.findById(id);
        if (!userOptional.isPresent()) throw new UserNotFoundException("User with Id " + id + " not found");

        Optional<Authority> authorityOptional = authorityDao.findByAuthority(authority.getAuthority());
        if (!authorityOptional.isPresent()) throw new RuntimeException("Authority not found!");

        User user = userOptional.get();
        authority = authorityOptional.get();

        Set<Authority> authorities = new HashSet<>();
        authorities.add(authority);
        user.setAuthorities(authorities);

        userDao.save(user);
    }

    @Override
    public void deleteById(long id) {
        Optional<User> optionalUser = userDao.findById(id);

        if (!optionalUser.isPresent()) throw new UserNotFoundException("User with Id " + id + " not found");

        userDao.deleteById(id);
    }

    @Override
    public UserProfileDto getByUsername(String username) {
        Optional<User> optionalUser = userDao.findByUsername(username);

        if (!optionalUser.isPresent()) throw new UserNotFoundException("User with username " + username + " not found");

        return UserMapper.toUserProfileDto(optionalUser.get());
    }
}