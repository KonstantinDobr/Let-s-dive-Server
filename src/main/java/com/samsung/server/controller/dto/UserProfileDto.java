package com.samsung.server.controller.dto;


import com.samsung.server.domain.Place;
import com.samsung.server.domain.Record;
import com.samsung.server.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileDto {
    private long id;
    private String username;
    private String email;
    private String information;
    private String photoUrl;
    private Set<Record> records;
    private Set<Place> places;
}
