package com.samsung.server.dao;

import com.samsung.server.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityDao extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthority(String authority);
}
