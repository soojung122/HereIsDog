package com.software.hereisdog.service;

import com.software.hereisdog.controller.LoginForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LoginFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LoginForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LoginForm form = (LoginForm) target;

        if (form.getUsername() == null || form.getUsername().trim().isEmpty()) {
            errors.rejectValue("username", "required", "아이디를 입력하세요.");
        }

        if (form.getPassword() == null || form.getPassword().trim().isEmpty()) {
            errors.rejectValue("password", "required", "비밀번호를 입력하세요.");
        }
    }
}
