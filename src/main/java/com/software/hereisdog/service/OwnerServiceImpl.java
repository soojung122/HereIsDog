package com.software.hereisdog.service;

import com.software.hereisdog.dao.OwnerDAO;
import com.software.hereisdog.domain.Place;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * OwnerService 인터페이스 구현 클래스
 */
@Service
public class OwnerServiceImpl implements OwnerService {

	@Autowired
    private OwnerDAO ownerDAO;

    @Override
    public List<Place> findPlacesByOwner(String ownerUsername) {
        return ownerDAO.findPlacesByOwnerUsername(ownerUsername);
    }
}
