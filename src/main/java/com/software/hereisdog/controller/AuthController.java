package com.software.hereisdog.controller;

import com.software.hereisdog.controller.LoginForm;
import com.software.hereisdog.controller.SignupForm;
import com.software.hereisdog.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;


/**
 * 로그인, 로그아웃, 회원가입을 담당하는 컨트롤러
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /** 로그인 폼 페이지 요청 */
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "auth/loginForm";
    }

    /** 로그인 처리 */
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm,
                        BindingResult bindingResult,
                        HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "auth/loginForm";
        }

        if (authService.login(loginForm.getUsername(), loginForm.getPassword())) {
            session.setAttribute("username", loginForm.getUsername());
            return "redirect:/";
        } else {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "auth/loginForm";
        }
    }

    /** 로그아웃 처리 */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    /** 회원가입 폼 요청 */
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "auth/signupForm";
    }

    /** 회원가입 처리 */
    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute SignupForm signupForm,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "auth/signupForm";
        }

        authService.signup(signupForm);
        return "redirect:/auth/login";
    }
}
