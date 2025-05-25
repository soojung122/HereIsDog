package com.software.hereisdog.service;

import com.software.hereisdog.controller.SignupForm;
import com.software.hereisdog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.regex.Pattern;


@Component
public class SignupFormValidator implements Validator {

    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");

    private static final Pattern BUSINESS_NUMBER_REGEX =
            Pattern.compile("^[0-9]{10}$");
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public boolean supports(Class<?> clazz) {
        return SignupForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignupForm form = (SignupForm) target;

        // 아이디
        if (isEmpty(form.getUsername())) {
            errors.rejectValue("username", "required", "아이디를 입력하세요.");
        }else if (userRepository.existsByUsername(form.getUsername())) {
            errors.rejectValue("username", "duplicate", "이미 사용 중인 아이디입니다.");
        }

        // 비밀번호
        if (isEmpty(form.getPassword())) {
            errors.rejectValue("password", "required", "비밀번호를 입력하세요.");
        } else if (form.getPassword().length() < 8) {
            errors.rejectValue("password", "length", "비밀번호는 최소 8자 이상이어야 합니다.");
        }

        // 비밀번호 확인
        if (isEmpty(form.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "required", "비밀번호 확인을 입력하세요.");
        } else if (!form.getPassword().equals(form.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "mismatch", "비밀번호가 일치하지 않습니다.");
        }
        
        // 이메일
        if (isEmpty(form.getEmail())) {
            errors.rejectValue("email", "required", "이메일을 입력하세요.");
        } else if (!EMAIL_REGEX.matcher(form.getEmail()).matches()) {
            errors.rejectValue("email", "format", "올바른 이메일 형식이어야 합니다.");
        }

        // 닉네임 필드 관련 삭제

        // 사업자 번호 (옵션, 입력됐을 경우만 체크)
        if (!isEmpty(form.getBusinessNumber())) {
            if (!BUSINESS_NUMBER_REGEX.matcher(form.getBusinessNumber()).matches()) {
                errors.rejectValue("businessNumber", "format", "사업자 번호는 숫자 10자리여야 합니다.");
            }
        }
    }

    private boolean isEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }
}