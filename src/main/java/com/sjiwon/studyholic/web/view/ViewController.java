package com.sjiwon.studyholic.web.view;

import com.sjiwon.studyholic.domain.entity.study.service.StudyService;
import com.sjiwon.studyholic.domain.entity.study.service.dto.response.StudySimpleInformation;
import com.sjiwon.studyholic.domain.entity.user.service.UserService;
import com.sjiwon.studyholic.security.principal.UserPrincipal;
import com.sjiwon.studyholic.web.view.dto.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Locale;

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
            Locale locale,
            Model model
    ) {
        Page<StudySimpleInformation> studyList = studyService.getMainPageStudyList(PageRequest.of(page - 1, SIZE_PER_PAGE), sort, keyword, locale);

        if (locale.getLanguage().equalsIgnoreCase(LOCALE_KOREA)) { // locale: ko
            model.addAttribute("searchType", SORT_TO_KO.get(sort));
        } else { // locale: other
            model.addAttribute("searchType", SORT_TO_ENG.get(sort));
        }
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

    @GetMapping("/mypage")
    public String myPage(@AuthenticationPrincipal UserPrincipal userPrincipal, Locale locale, Model model) {
        model.addAttribute("userDetail", userService.getUserDetailInformation(userPrincipal.getUser().getId(), locale));
        return "main/MyPage";
    }

    @GetMapping("/mypage/study")
    public String userParticipateStudyPage(@AuthenticationPrincipal UserPrincipal userPrincipal, Locale locale, Model model) {
        model.addAttribute("participateStudyDetail", userService.getUserParticipateStudyInformation(userPrincipal.getUser().getId(), locale));
        return "main/ParticipateStudyDetailPage";
    }

    @GetMapping("/study/{randomSequence}")
    public String studyDetailPage(@PathVariable String randomSequence, Locale locale, Model model) {
        model.addAttribute("studyDetail", studyService.getStudyDetailInformation(randomSequence, locale));
        return "main/StudyDetailPage";
    }

    @GetMapping("/study/post")
    public String studyRegisterPage() {
        return "main/StudyRegisterPage";
    }

    @GetMapping("/study/edit")
    public String studyEditPage(@RequestParam(name = "seq") String randomSequence, Model model) {
        model.addAttribute("studyDetailToEdit", studyService.getStudyDefailtToEditInformation(randomSequence));
        return "main/StudyEditPage";
    }

    @GetMapping("/find-id")
    public String findIdPage() {
        return "main/FindIdPage";
    }

    @GetMapping("/reset-password")
    public String resetPasswordPage() {
        return "main/ResetPasswordPage";
    }

    @GetMapping("/change-password")
    public String changePasswordPage() {
        return "main/ChangePasswordPage";
    }
}
