package com.samsung.server.service.Impl;

import com.samsung.server.controller.dto.UserProfileDto;
import com.samsung.server.controller.dto.UserRegisterDto;
import com.samsung.server.dao.AuthorityDao;
import com.samsung.server.dao.PlaceDao;
import com.samsung.server.dao.RecordDao;
import com.samsung.server.dao.UserDao;
import com.samsung.server.domain.Authority;
import com.samsung.server.domain.Place;
import com.samsung.server.domain.Record;
import com.samsung.server.domain.User;
import com.samsung.server.exception.PlaceNotFoundException;
import com.samsung.server.exception.RecordNotFoundException;
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
    private final RecordDao recordDao;
    private final PlaceDao placeDao;

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
        if (userProfileDto.getRecords() != null) user.setRecords(userProfileDto.getRecords());

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

    @Override
    public UserProfileDto addRecord(long userId, long recordId) {
        Optional<Record> optionalRecord = recordDao.findById(recordId);
        if(!optionalRecord.isPresent()) throw new RecordNotFoundException("Record with Id " + recordId + " not found");
        Record record = optionalRecord.get();

        Optional<User> optionalUser = userDao.findById(userId);
        if(!optionalUser.isPresent()) throw new UserNotFoundException("User with Id " + userId + " not found");
        User user = optionalUser.get();

        Set<Record> records = user.getRecords();
        records.add(record);

        user.setRecords(records);

        User result = userDao.save(user);

        return UserMapper.toUserProfileDto(result);
    }

    @Override
    public UserProfileDto addPlace(long userId, long placeId) {
        Optional<Place> optionalPlace = placeDao.findById(placeId);
        if (!optionalPlace.isPresent()) throw  new PlaceNotFoundException("Place with Id " + placeId + " not found");
        Place place  = optionalPlace.get();

        Optional<User> optionalUser = userDao.findById(userId);
        if (!optionalUser.isPresent()) throw  new UserNotFoundException("User with Id " + userId + " not found");
        User user = optionalUser.get();

        Set<Place> places = user.getPlaces();
        places.add(place);
        user.setPlaces(places);

        return UserMapper.toUserProfileDto(userDao.save(user));

    }

    @Override
    public UserProfileDto deletePlace(long userId, long placeId) {
        Optional<User> optionalUser = userDao.findById(userId);
        if (!optionalUser.isPresent()) throw  new UserNotFoundException("User with Id " + userId + " not found");
        User user = optionalUser.get();

        for (Place place : user.getPlaces()) {
            if (place.getId() == placeId) {
                Set<Place> places = user.getPlaces();
                places.remove(place);
                user.setPlaces(places);
                break;
            }
        }

        return UserMapper.toUserProfileDto(userDao.save(user));
    }

    @Override
    public UserProfileDto updateInfo(long id, String email, String info) {
        Optional<User> optionalUser = userDao.findById(id);
        if (!optionalUser.isPresent()) throw  new UserNotFoundException("User with Id " + id + " not found");
        User user = optionalUser.get();

        info = info.substring(1, info.length() - 1);

        user.setEmail(email);
        user.setInformation(info);

        return UserMapper.toUserProfileDto(userDao.save(user));
    }
}