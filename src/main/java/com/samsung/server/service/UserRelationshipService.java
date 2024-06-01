package com.samsung.server.service;

import com.samsung.server.domain.UserRelationship;

import java.util.Set;

public interface UserRelationshipService {
    UserRelationship addUserRelationship(UserRelationship userRelationship);
    Set<UserRelationship> getByFirstId(long firstId);
}
