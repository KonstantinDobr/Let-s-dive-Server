package com.samsung.server.service;

import com.samsung.server.domain.Place;

public interface PlaceService {

    Place getById(long id);

    Place add(Place place);

    void deleteById(long id);

    void update(long id, Place place);

    Place getByPlaceName(String placeName);

}
