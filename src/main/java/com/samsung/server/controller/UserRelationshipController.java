package com.samsung.server.controller;

import com.samsung.server.controller.dto.UserProfileDto;
import com.samsung.server.domain.UserRelationship;
import com.samsung.server.service.UserRelationshipService;
import com.samsung.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@RequestMapping("/server/v1/userRelationship")
public class UserRelationshipController {

    private final UserRelationshipService userRelationshipService;

    @PostMapping()
    public UserRelationship addRelationship(@RequestBody UserRelationship relationship) {
        return userRelationshipService.addUserRelationship(relationship);
    }

    @GetMapping("/firstId/{firstId}")
    public Set<UserRelationship> getByFirstId(@PathVariable long firstId) {
        return userRelationshipService.getByFirstId(firstId);
    }
}
