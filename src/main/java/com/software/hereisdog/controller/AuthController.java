package com.software.hereisdog.controller;

import com.software.hereisdog.controller.LoginForm;
import com.software.hereisdog.controller.SignupForm;
import com.software.hereisdog.service.AuthService;
//import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

import com.software.hereisdog.service.SignupFormValidator;
import com.software.hereisdog.service.LoginFormValidator;

/**
 * 로그인, 로그아웃, 회원가입을 담당하는 컨트롤러
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;
    private final SignupFormValidator signupFormValidator;
    private final LoginFormValidator loginFormValidator;

    @Autowired
    public AuthController(AuthService authService, 
    		SignupFormValidator signupFormValidator,
    		LoginFormValidator loginFormValidator) {
        this.authService = authService;
        this.signupFormValidator = signupFormValidator;
        this.loginFormValidator = loginFormValidator;
    }

    /** 로그인 폼 페이지 요청 */
    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "loginForm";
    }

    /** 로그인 처리 */
    @PostMapping("/login")
    public String loginSubmit(@ModelAttribute("loginForm") LoginForm loginForm,
                              BindingResult bindingResult,
                              HttpSession session) {

     // 수동 검증기 호출
        loginFormValidator.validate(loginForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "loginForm";
        }

        boolean result = authService.login(loginForm.getUsername(), loginForm.getPassword(), loginForm.getRole());

        if (!result) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 틀렸습니다.");
            return "loginForm";
        }

        session.setAttribute("loginUser", loginForm.getUsername());
        session.setAttribute("role", loginForm.getRole());

        return "redirect:/";  // 로그인 성공 시 메인 페이지로 이동
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
        return "signupForm";
    }

    /** 회원가입 처리 */
    /*@PostMapping("/signup")
    public String signup(@Valid @ModelAttribute SignupForm signupForm,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "signupForm";
        }

        authService.signup(signupForm);
        return "redirect:/auth/login";
    }*/
    /** 회원가입 처리 */
    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupForm form,
                         BindingResult bindingResult,
                         Model model) {

        // 수동 검증기 호출
        signupFormValidator.validate(form, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("signupForm", form);
            return "jsp/signupForm";
        }

        // 회원가입 성공 처리
        authService.signup(form);
        return "redirect:/auth/login";
    }
    @GetMapping("/find-id")
    public String showFindIdForm() {
        return "findIdForm";
    }

    @PostMapping("/verify-id")
    public String verifyId(@RequestParam String email, @RequestParam String role, Model model) {
        // 아이디 찾기 로직 후 model에 아이디 전달
        model.addAttribute("foundId", "user1234");
        return "idResultForm";  // 아이디 결과 보여주는 JSP
    }

    @GetMapping("/find-password")
    public String showFindPasswordForm() {
        return "findPasswordForm";
    }

    @PostMapping("/verify-password")
    public String verifyPassword(@RequestParam String email, @RequestParam String username, Model model) {
        // 비밀번호 재설정 화면으로 이동
        model.addAttribute("username", username);
        return "resetPasswordForm";
    }
    
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String username,
                                @RequestParam String newPassword,
                                @RequestParam String confirmPassword,
                                Model model) {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("username", username);
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "resetPasswordForm"; // 다시 폼으로
        }

        // TODO: 실제 비밀번호 저장 로직 (예: authService.updatePassword(username, newPassword))

        return "redirect:/auth/login"; // 🔁 로그인 페이지로 이동
    }
}
