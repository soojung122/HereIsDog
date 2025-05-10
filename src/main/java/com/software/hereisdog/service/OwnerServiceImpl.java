package com.software.hereisdog.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;

/**
 * OwnerService 인터페이스 구현 클래스
 */
@Service
public class OwnerServiceImpl implements OwnerService {

    @Override
    public List<Object> findPlacesByOwner(String ownerUsername) {
        // TODO: MyBatis DAO를 이용하여 사업자 소유 장소 조회
        return Collections.emptyList();
    }
}
