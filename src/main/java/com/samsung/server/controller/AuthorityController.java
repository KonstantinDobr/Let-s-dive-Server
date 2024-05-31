package com.samsung.server.controller;

import com.samsung.server.domain.Authority;
import com.samsung.server.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/server/v1/authority")
public class AuthorityController {

    private final AuthorityService authorityService;

    @PostMapping
    public Authority add(@RequestBody Authority authority) {
        return authorityService.add(authority);
    }

    @GetMapping
    public List<Authority> getAll() {
        return authorityService.getAll();
    }

}
