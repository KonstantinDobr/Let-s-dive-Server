package com.samsung.server.controller;

import com.samsung.server.controller.dto.UserProfileDto;
import com.samsung.server.controller.dto.UserRegisterDto;
import com.samsung.server.domain.Authority;
import com.samsung.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/server/v1/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public UserProfileDto add(@RequestBody UserRegisterDto userRegisterDto) {
        return userService.add(userRegisterDto);
    }

    @GetMapping()
    public List<UserProfileDto> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserProfileDto getById(@PathVariable long id) {
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public UserProfileDto update(@PathVariable long id, @RequestBody UserProfileDto userProfileDto) {
        return userService.update(id, userProfileDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable long id) {
        userService.deleteById(id);
    }

    @PutMapping("/authority/{id}")
    public void update(@PathVariable Long id, @RequestBody Authority authority) {
        userService.update(id, authority);
    }

    @GetMapping("/username/{username}")
    public UserProfileDto getByUsername(@PathVariable String username) {
        UserProfileDto byUsername = userService.getByUsername(username);

        return userService.getByUsername(username);
    }

    @GetMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public UserProfileDto login(Authentication authentication) {
        return userService.getByUsername(authentication.getName());
    }
}
