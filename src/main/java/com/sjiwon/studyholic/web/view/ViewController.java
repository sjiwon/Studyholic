package com.sjiwon.studyholic.web.view;

import com.sjiwon.studyholic.domain.entity.user.service.UserService;
import com.sjiwon.studyholic.domain.etc.login.dto.response.UserSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import static com.sjiwon.studyholic.common.VariableFactory.SESSION_KEY;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "authenticate/LoginPage";
    }

    @GetMapping("/signup")
    public String signUpPage() {
        return "authenticate/SignUpPage";
    }

    @GetMapping("/user/{userId}")
    public String myPage(@PathVariable Long userId, HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        delegateIllegalUrlRequest(userId, request, response);
        model.addAttribute("userDetail", userService.getUserDetailInformation(userId));
        return "main/MyPage";
    }

    private void delegateIllegalUrlRequest(Long userId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!Objects.equals(getUserSession(request).getId(), userId)) {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.println("<script>alert('올바르지 않은 요청입니다'); location.href = '/';</script>");
            writer.flush();
        }
    }

    private UserSession getUserSession(HttpServletRequest request) {
        return (UserSession) request.getSession(false).getAttribute(SESSION_KEY);
    }
}
