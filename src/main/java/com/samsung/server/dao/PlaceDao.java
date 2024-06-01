package com.samsung.server.dao;

import com.samsung.server.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaceDao extends JpaRepository<Place, Long> {
    Optional<Place> findByPlaceName(String placeName);
}
