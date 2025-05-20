package com.software.hereisdog.service;

import com.software.hereisdog.controller.PlaceForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PlaceFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return PlaceForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PlaceForm form = (PlaceForm) target;

        if (isEmpty(form.getName())) {
            errors.rejectValue("name", "required", "장소 이름을 입력하세요.");
        }

        if (isEmpty(form.getAddress())) {
            errors.rejectValue("address", "required", "주소를 입력하세요.");
        }

        if (isEmpty(form.getType())) {
            errors.rejectValue("type", "required", "장소 유형을 입력하세요.");
        }

        if (form.getLat() == 0.0 || form.getLng() == 0.0) {
            errors.reject("invalidCoordinates", "위치 좌표가 정확하지 않습니다.");
        }
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}
