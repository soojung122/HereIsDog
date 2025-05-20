package com.software.hereisdog.service;

import com.software.hereisdog.controller.ReviewForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ReviewFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ReviewForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ReviewForm form = (ReviewForm) target;

        // 리뷰 내용 검사
        if (form.getContent() == null || form.getContent().trim().isEmpty()) {
            errors.rejectValue("content", "required", "리뷰 내용을 입력하세요.");
        } else if (form.getContent().length() > 500) {
            errors.rejectValue("content", "length", "리뷰는 500자 이내로 작성해주세요.");
        }
        
        // 평점 검사 (1 ~ 5)
        if (form.getRating() < 1 || form.getRating() > 5) {
            errors.rejectValue("rating", "range", "평점은 1점 이상 5점 이하로 입력해주세요.");
        }
    }
}
