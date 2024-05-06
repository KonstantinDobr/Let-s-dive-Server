package com.samsung.server.service.Impl;

import com.samsung.server.dao.AuthorityDao;
import com.samsung.server.domain.Authority;
import com.samsung.server.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityDao authorityDao;

    @Override
    public Authority add(Authority authority) {

        Optional<Authority> optionalAuthority = authorityDao.findByAuthority(authority.getAuthority());

        //return optionalAuthority.orElseGet(() -> authorityRepository.save(authority));

        if (optionalAuthority.isPresent()) return optionalAuthority.get();

        else return authorityDao.save(authority);
    }

    @Override
    public List<Authority> getAll() {
        return authorityDao.findAll();
    }
}
