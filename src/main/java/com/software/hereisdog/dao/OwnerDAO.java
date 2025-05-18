package com.software.hereisdog.dao;

import com.software.hereisdog.domain.Place;
import java.util.List;

public interface OwnerDAO {
    List<Place> findPlacesByOwnerUsername(String ownerUsername);  
    // 또는 ownerId(Long ownerId)로 받아도 됨!
}
