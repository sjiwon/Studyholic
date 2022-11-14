package com.sjiwon.studyholic.web.api.study;

import com.sjiwon.studyholic.domain.entity.study.service.StudyService;
import com.sjiwon.studyholic.web.api.study.dto.request.StudyCreateRequest;
import com.sjiwon.studyholic.web.api.study.dto.request.UpdateStudyInformationRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api(tags = {"스터디 API"})
public class StudyApiController {
    private final StudyService studyService;

    @PostMapping("/study")
    @ApiOperation(value = "스터디 생성 API", notes = "스터디를 생성하는 API")
    public ResponseEntity<Void> createStudy(@RequestBody StudyCreateRequest request) {
        Long createStudy = studyService.createNewStudy(request.getUserId(), request.toEntity(), request.getTagList());
        return ResponseEntity.created(URI.create("/study/" + createStudy)).build();
    }

    @PatchMapping("/study/{studyId}")
    @ApiOperation(value = "스터디 정보 수정 API", notes = "스터디의 정보를 수정하는 API [스터디 팀명, 간단한 설명, 설명, 모집 마감일, 최대 모집 멤버]")
    public ResponseEntity<Void> changeStudyInformation(@PathVariable Long studyId, @RequestBody UpdateStudyInformationRequest updateRequest, HttpServletRequest request) {
        studyService.changeInformation(request, studyId, updateRequest.getName(), updateRequest.getBriefDescription(), updateRequest.getDescription(), updateRequest.getRecruitDeadline(), updateRequest.getMaxMember());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/study/{studyId}")
    @ApiOperation(value = "스터디 삭제 API", notes = "스터디를 삭제하는 API")
    public ResponseEntity<Void> deleteStudy(@PathVariable Long studyId, HttpServletRequest request) {
        studyService.deleteStudy(studyId, request);
        return ResponseEntity.noContent().build();
    }
}
