package com.sjiwon.studyholic.web.view;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {
    @GetMapping("/login")
    public String loginPage() {
        return "authenticate/LoginPage";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "authenticate/SignUpPage";
    }
}
