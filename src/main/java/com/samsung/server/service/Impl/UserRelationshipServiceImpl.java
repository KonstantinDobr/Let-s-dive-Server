package com.samsung.server.service.Impl;

import com.samsung.server.dao.UserRelationshipDao;
import com.samsung.server.domain.User;
import com.samsung.server.domain.UserRelationship;
import com.samsung.server.exception.UserRelationshipNotFoundException;
import com.samsung.server.service.UserRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserRelationshipServiceImpl implements UserRelationshipService {
    private final UserRelationshipDao userRelationshipDao;

    @Override
    public UserRelationship addUserRelationship(UserRelationship userRelationship) {
        return userRelationshipDao.save(userRelationship);
    }

    @Override
    public Set<UserRelationship> getByFirstId(long firstId) {
        Optional<Set<UserRelationship>> optional = userRelationshipDao.findByFirstId(firstId);
        if (!optional.isPresent()) throw new UserRelationshipNotFoundException("UserRelationship with firstId " + firstId + " not found");

        return optional.get();
    }
}
