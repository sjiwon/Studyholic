package com.sjiwon.studyholic.web.api.userstudy;

import com.sjiwon.studyholic.domain.entity.userstudy.service.UserStudyService;
import com.sjiwon.studyholic.web.api.userstudy.dto.request.UserStudyParticipateRequest;
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
@Api(tags = {"사용자 <-> 스터디 관련 API"})
public class UserStudyApiController {
    private final UserStudyService userStudyService;

    @PostMapping("/user/join-study")
    @ApiOperation(value = "스터디 참여 API", notes = "사용자가 특정 스터디에 참여하기 위한 API")
    public ResponseEntity<Void> studyParticipate(@RequestBody UserStudyParticipateRequest participateRequest) {
        userStudyService.participateStudy(participateRequest.getStudyId(), participateRequest.getUserId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/cancel-study")
    @ApiOperation(value = "스터디 참여 취소 API", notes = "사용자가 참여중인 스터디에서 참여를 취소하기 위한 API")
    public ResponseEntity<Void> studyParticipateCancel(@RequestBody UserStudyParticipateRequest cancelRequest) {
        userStudyService.participateCancel(cancelRequest.getStudyId(), cancelRequest.getUserId());
        return ResponseEntity.noContent().build();
    }
}
