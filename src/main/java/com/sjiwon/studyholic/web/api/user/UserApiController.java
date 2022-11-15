package com.sjiwon.studyholic.web.api.user;

import com.sjiwon.studyholic.domain.entity.user.service.UserService;
import com.sjiwon.studyholic.domain.etc.session.SessionRefreshService;
import com.sjiwon.studyholic.web.api.user.dto.request.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = {"사용자 API"})
public class UserApiController {
    private final UserService userService;
    private final SessionRefreshService sessionRefreshService;

    @PostMapping(value = "/user", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "회원가입 API - Version 1", notes = "회원가입을 위한 API (사용자가 업로드한 이미지로 프로필 적용)")
    public ResponseEntity<Void> joinUser(@ModelAttribute UserJoinRequest request) {
        Long joinUserId = userService.saveUser(request.toEntity(), request.getProfile());
        return ResponseEntity.created(URI.create("/user/" + joinUserId)).build();
    }

    @PostMapping("/user/default-profile")
    @ApiOperation(value = "회원가입 API - Version 2", notes = "회원가입을 위한 API (사용자 프로필 이미지 = 서버 기본 제공 이미지)")
    public ResponseEntity<Void> joinUserWithDefaultProfile(@ModelAttribute UserJoinRequestWithDefaultProfile request) {
        Long joinUserId = userService.saveUser(request.toEntity(), null);
        return ResponseEntity.created(URI.create("/user/" + joinUserId)).build();
    }

    @PatchMapping(value = "/user/change-profile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ApiOperation(value = "사용자 프로필 이미지 변경 API - Version 1", notes = "사용자의 현재 프로필 이미지를 새로 업로드하는 이미지로 변경하는 API")
    public ResponseEntity<Void> changeUserProfileImage(@ModelAttribute ChangeProfileRequest changeRequest, HttpServletRequest request) {
        userService.changeUserProfileImage(changeRequest.getUserId(), changeRequest.getProfile(), request);
        sessionRefreshService.refreshSession(changeRequest.getUserId(), request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/user/change-default-profile")
    @ApiOperation(value = "사용자 프로필 이미지 변경 API - Version 2", notes = "사용자의 현재 프로필 이미지를 서버 기본 제공 이미지로 변경하는 API")
    public ResponseEntity<Void> changeUserProfileImageToDefault(@RequestParam Long userId, HttpServletRequest request) {
        userService.changeUserProfileImageToDefault(userId, request);
        sessionRefreshService.refreshSession(userId, request);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/user/change-nickname")
    @ApiOperation(value = "사용자 닉네임 변경 API", notes = "닉네임을 변경하기 위한 API")
    public ResponseEntity<Void> changeUserNickname(@RequestBody ChangeNicknameRequest changeRequest, HttpServletRequest request) {
        userService.changeUserNickname(changeRequest.getUserId(), changeRequest.getNickname());
        sessionRefreshService.refreshSession(changeRequest.getUserId(), request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/duplicate-check")
    @ApiOperation(value = "사용자 중복 체크 API", notes = "회원가입 간 데이터 중복 체크를 위한 API [닉네임, 아이디]")
    public ResponseEntity<Void> checkUserDuplicateResource(@RequestBody UserDuplicateCheckRequest checkRequest) {
        String resource = checkRequest.getResource();
        String value = checkRequest.getValue();

        if (resource.equals("nickname")) { // 닉네임 중복 여부
            userService.hasDuplicateNickname(value);
            return ResponseEntity.noContent().build();
        } else if (resource.equals("id")) { // 아이디 중복 여부
            userService.hasDuplicateLoginId(value);
            return ResponseEntity.noContent().build();
        } else { // 잘못된 요청
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/user/find/login-id")
    @ApiOperation(value = "아이디 찾기 API", notes = "[이름, 이메일]을 통해서 해당 사용자의 아이디를 찾아주는 API")
    public ResponseEntity<String> findLoginId(@RequestBody FindIdRequest findIdRequest) {
        return ResponseEntity.ok(userService.findLoginId(findIdRequest.getName(), findIdRequest.getEmail()));
    }
}
