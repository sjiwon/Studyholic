package com.sjiwon.studyholic.web.view;

import com.sjiwon.studyholic.domain.entity.study.service.StudyService;
import com.sjiwon.studyholic.domain.entity.study.service.dto.response.StudySimpleInformation;
import com.sjiwon.studyholic.domain.entity.user.service.UserService;
import com.sjiwon.studyholic.domain.etc.login.dto.response.UserSession;
import com.sjiwon.studyholic.web.view.dto.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

import static com.sjiwon.studyholic.common.VariableFactory.*;

@Controller
@RequiredArgsConstructor
public class ViewController {
    private final UserService userService;
    private final StudyService studyService;

    @GetMapping({"", "/"})
    public String mainPage(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "registerDate") String sort,
            @RequestParam(required = false) String keyword,
            Model model
    ) {
        Page<StudySimpleInformation> studyList = studyService.getMainPageStudyList(PageRequest.of(page - 1, SIZE_PER_PAGE), sort, keyword);

        model.addAttribute("searchType", SORT_TO_KO.get(sort));
        model.addAttribute("keyword", keyword);
        model.addAttribute("studyList", studyList.getContent());
        model.addAttribute("pagination", new Pagination(studyList.getTotalElements(), studyList.getTotalPages(), page));

        return "main/MainPage";
    }

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
