package com.samsung.server.controller.dto;


import com.samsung.server.domain.Record;
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
    private String photoUrl;
    private Set<Record> records;
}
