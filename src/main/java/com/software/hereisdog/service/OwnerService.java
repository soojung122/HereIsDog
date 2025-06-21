package com.software.hereisdog.service;

import com.software.hereisdog.domain.Place;
import java.util.List;

/**
 * 사업자(Owner) 기능 비즈니스 로직을 처리하는 서비스 인터페이스
 */
public interface OwnerService {
    List<Place> findPlacesByOwner(String ownerUsername);
    Place getPlaceByOwner(String ownerUsername);
    
    public void updatePlace(Place place);

}
