package com.samsung.server.service.Impl;

import com.samsung.server.dao.PlaceDao;
import com.samsung.server.domain.Place;
import com.samsung.server.exception.PlaceNotFoundException;
import com.samsung.server.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService {

    private final PlaceDao placeDao;

    @Override
    public Place getById(long id) {
        Optional<Place> optionalPlace = placeDao.findById(id);

        if (!optionalPlace.isPresent()) throw new PlaceNotFoundException("Place with Id " + id + " not found");

        return optionalPlace.get();
    }

    @Override
    public Place add(Place place) {
        return placeDao.save(place);
    }
}
