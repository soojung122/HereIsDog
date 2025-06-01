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

        // content는 선택사항
        if (form.getContent() == null || form.getContent().trim().isEmpty()) {
           if (form.getContent().length() > 500) {
           		errors.rejectValue("content", "length", "리뷰는 500자 이내로 작성해주세요.");
           }
        }
        
        //평점은 필수사항
        // 선택하지 않은 경우
        if (form.getRating() == null) {
            errors.rejectValue("rating", "required", "평점을 선택해주세요.");
        }
        // 평점이 1~5 범위를 벗어나는 경우
        else if (form.getRating() < 1 || form.getRating() > 5) {
            errors.rejectValue("rating", "range", "평점은 1점 이상 5점 이하로 입력해주세요.");
        }
    }
}
