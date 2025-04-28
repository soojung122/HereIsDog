package com.software.hereisdog.service;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collections;

/**
 * MapService 인터페이스 구현 클래스
 */
@Service
public class MapServiceImpl implements MapService {

    @Override
    public List<Object> getAllPlaces() {
        // TODO: MyBatis DAO 호출해서 장소 목록 조회
        return Collections.emptyList();
    }
}
