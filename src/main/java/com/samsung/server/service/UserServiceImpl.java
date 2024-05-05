package com.samsung.server.service;

import com.samsung.server.dao.UserDao;
import com.samsung.server.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.save(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User getById(long id) {
        return userDao.findById(id).orElse(null);
    }

    @Override
    public User update(long id, User user) {
        Optional<User> userOptional = userDao.findById(id);
        if (!userOptional.isPresent()) throw new RuntimeException("User with Id " + id + " not found");

        User updateUser = userOptional.get();
        updateUser.setName(user.getName());
        updateUser.setEmail(user.getEmail());
        updateUser.setPhotoUrl(user.getPhotoUrl());

        return userDao.save(user);
    }

    @Override
    public void deleteById(long id) {
        userDao.deleteById(id);
    }
}