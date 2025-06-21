package com.software.hereisdog.service;

import com.software.hereisdog.dao.OwnerDAO;
import com.software.hereisdog.dao.mybatis.mapper.OwnerMapper;
import com.software.hereisdog.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * OwnerService 인터페이스 구현 클래스
 */
@Service
public class OwnerServiceImpl implements OwnerService {

    private final OwnerMapper ownerMapper;

    @Autowired
    public OwnerServiceImpl(OwnerMapper ownerMapper) {
        this.ownerMapper = ownerMapper;
    }

    @Override
    public List<Place> findPlacesByOwner(String ownerUsername) {
        return ownerMapper.selectPlacesByOwner(ownerUsername);
    }

    @Override
    public Place getPlaceByOwner(String ownerUsername) {
        return ownerMapper.selectPlaceByOwner(ownerUsername); // ✅ 오류 없음
    }
    
    @Override
    public void updatePlace(Place place) {
        ownerMapper.updatePlace(place);
    }

}

