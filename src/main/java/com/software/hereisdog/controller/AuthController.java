package com.software.hereisdog.controller;

import com.software.hereisdog.controller.LoginForm;
import com.software.hereisdog.controller.SignupForm;
import com.software.hereisdog.domain.User;
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
import com.software.hereisdog.service.PasswordResetvalidation;
import com.software.hereisdog.domain.Customer;
import com.software.hereisdog.domain.Owner;


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

    	// 관리자 하드코딩 체크 (아이디 + 비번)
        if ("adminUsername".equals(loginForm.getUsername()) 
            && "12345678".equals(loginForm.getPassword())) {
            
            User admin = new User() {
                { setUsername("adminUsername"); }
            };
            session.setAttribute("loginUser", admin);
            return "redirect:/admin";
        }
        

        boolean result = authService.login(
            loginForm.getUsername(),
            loginForm.getPassword(),
            loginForm.getRole()
        );
        if (!result) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 틀렸습니다.");
            return "loginForm";
        }

        String role = loginForm.getRole() == null 
                      ? "" 
                      : loginForm.getRole().trim().toLowerCase();

        if ("owner".equals(role)) {

            Owner owner = (Owner) authService.getUserByUsernameAndRole(
                loginForm.getUsername(), loginForm.getRole()
            );
            session.setAttribute("loginUser", owner);
            session.setAttribute("userId", owner.getId());
        } else {
           
            Customer customer = (Customer) authService.getUserByUsernameAndRole(
                loginForm.getUsername(), loginForm.getRole()
            );
            session.setAttribute("loginUser", customer);
            session.setAttribute("userId", customer.getId());
        }

        return "redirect:/user";
    }

    /** 로그아웃 처리 */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); //세션 전부 제거
        return "redirect:/";
    }

    /** 회원가입 폼 요청 */
    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("signupForm", new SignupForm());
        return "signupForm";
    }


    @GetMapping("/check-session")
    public String checkSession(HttpSession session, Model model) {
        Object loginUser = session.getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:/auth/login";  // 로그인 안 되어 있으면 로그인 페이지로
        }

        model.addAttribute("username", loginUser);
        return "sessionInfoPage";  // 로그인된 사용자 정보 보여주는 뷰
    }

    
    /** 회원가입 처리 */
    @PostMapping("/signup")
    public String signup(@ModelAttribute SignupForm form,
                         BindingResult bindingResult,
                         Model model) {

        // 수동 검증기 호출
        signupFormValidator.validate(form, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("signupForm", form);
            return "signupForm";
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
        
        String foundId = authService.findUsernameByEmailAndRole(email, role);
        
        if (foundId != null) {
            model.addAttribute("foundId", foundId);
        } else {
            model.addAttribute("foundId", "입력한 정보와 일치하는 아이디가 없습니다.");
        }
        return "idResultForm";  // 아이디 결과 보여주는 JSP
    }

    @GetMapping("/find-password")
    public String showFindPasswordForm() {
        return "findPasswordForm";
    }

    @PostMapping("/verify-password")
    public String verifyPassword(@RequestParam String email,
                                 @RequestParam String username,
                                 Model model) {
        SignupForm form = new SignupForm();
        form.setUsername(username);  // 히든필드용
        model.addAttribute("signupForm", form);  // ✅ modelAttribute 등록

        return "resetPasswordForm";
    }
    
    @Autowired
    private PasswordResetvalidation passwordRevalidation;

    @PostMapping("/reset-password")
    public String resetPassword(@ModelAttribute("signupForm") SignupForm form,
                                BindingResult result,
                                Model model) {

        passwordRevalidation.validate(form, result);

        if (result.hasErrors()) {
            model.addAttribute("username", form.getUsername());  // 다시 넣어줘야 hidden 필드 유지됨
            return "resetPasswordForm";
        }

        authService.updatePassword(form.getUsername(), form.getPassword());
        return "redirect:/auth/login";
    }
}
