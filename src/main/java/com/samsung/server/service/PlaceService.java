package com.samsung.server.service;

import com.samsung.server.domain.Place;

public interface PlaceService {

    Place getById(long id);

    Place add(Place place);

}
