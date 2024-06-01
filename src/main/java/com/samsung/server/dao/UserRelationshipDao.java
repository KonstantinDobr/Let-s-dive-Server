package com.samsung.server.dao;

import com.samsung.server.domain.User;
import com.samsung.server.domain.UserRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRelationshipDao extends JpaRepository<UserRelationship, Long> {
    Optional<Set<UserRelationship>> findByFirstId(long firstId);
}
