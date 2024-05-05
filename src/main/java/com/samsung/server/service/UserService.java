package com.samsung.server.service;

import com.samsung.server.domain.User;

import java.util.List;

public interface UserService {
    User add(User user);

    List<User> getAll();

    User getById(long id);

    User update(long id, User user);

    void deleteById(long id);
}
