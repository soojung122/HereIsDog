package com.software.hereisdog.service;

import com.software.hereisdog.controller.SignupForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PasswordResetvalidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return SignupForm.class.isAssignableFrom(clazz); // SignupForm 사용
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignupForm form = (SignupForm) target;

        String password = form.getPassword();
        String confirm = form.getConfirmPassword();

        if (password == null || password.trim().isEmpty()) {
            errors.rejectValue("password", "required", "비밀번호를 입력하세요.");
        } else if (password.length() < 8) {
            errors.rejectValue("password", "length", "비밀번호는 8자 이상이어야 합니다.");
        }

        if (confirm == null || confirm.trim().isEmpty()) {
            errors.rejectValue("confirmPassword", "required", "비밀번호 확인을 입력하세요.");
        } else if (!password.equals(confirm)) {
            errors.rejectValue("confirmPassword", "mismatch", "비밀번호가 일치하지 않습니다.");
        }
    }
}
