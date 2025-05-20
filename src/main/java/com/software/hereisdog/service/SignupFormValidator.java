package com.software.hereisdog.service;

import com.software.hereisdog.controller.SignupForm;
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
        }

        // 비밀번호
        if (isEmpty(form.getPassword())) {
            errors.rejectValue("password", "required", "비밀번호를 입력하세요.");
        } else if (form.getPassword().length() < 6) {
            errors.rejectValue("password", "length", "비밀번호는 최소 6자 이상이어야 합니다.");
        }

        // 이메일
        if (isEmpty(form.getEmail())) {
            errors.rejectValue("email", "required", "이메일을 입력하세요.");
        } else if (!EMAIL_REGEX.matcher(form.getEmail()).matches()) {
            errors.rejectValue("email", "format", "올바른 이메일 형식이어야 합니다.");
        }

        // 닉네임
        if (isEmpty(form.getNickname())) {
            errors.rejectValue("nickname", "required", "닉네임을 입력하세요.");
        } else if (form.getNickname().length() < 2 || form.getNickname().length() > 20) {
            errors.rejectValue("nickname", "length", "닉네임은 2자 이상 20자 이하로 입력하세요.");
        }

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