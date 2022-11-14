package com.sjiwon.studyholic.web.api.user;

import com.sjiwon.studyholic.domain.user.service.UserService;
import com.sjiwon.studyholic.web.api.user.dto.request.UserDuplicateCheckRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = {"사용자 API"})
public class UserApiController {
    private final UserService userService;

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
}
