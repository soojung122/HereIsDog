package com.software.hereisdog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainHome {

    @GetMapping("/")
    public String home() {
        return "index";  // → ViewResolver가 /WEB-INF/jsp/index.jsp 로 연결
    }
}
