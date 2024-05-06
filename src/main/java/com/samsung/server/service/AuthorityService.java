package com.samsung.server.service;

import com.samsung.server.domain.Authority;

import java.util.List;

public interface AuthorityService {

    Authority add(Authority authority);

    List<Authority> getAll();

}
