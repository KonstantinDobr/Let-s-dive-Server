package com.samsung.server.controller;

import com.samsung.server.domain.Place;
import com.samsung.server.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/server/v1/place")
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/{id}")
    public Place getById(@PathVariable long id) {
        return placeService.getById(id);
    }

    @PostMapping()
    public Place add(@RequestBody Place place) {
        return placeService.add(place);
    }

}
