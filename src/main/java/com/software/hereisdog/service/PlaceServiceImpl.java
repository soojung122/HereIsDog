package com.software.hereisdog.service;

import com.software.hereisdog.controller.PlaceForm;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;

/**
 * PlaceService 인터페이스 구현 클래스
 */
@Service
public class PlaceServiceImpl implements PlaceService {

    @Override
    public void registerPlace(PlaceForm placeForm) {
        // TODO: MyBatis DAO를 이용하여 장소 등록
    }

    @Override
    public List<Object> findAll() {
        // TODO: MyBatis DAO를 이용하여 장소 전체 조회
        return Collections.emptyList();
    }
    
    public List<PlaceForm> getPlacesByType(String type) {
        // TODO: DAO 연결 시 교체
        return Collections.emptyList(); // 임시 구현
    }
}
