package com.sjiwon.studyholic.web.api.study;

import com.sjiwon.studyholic.domain.entity.study.service.StudyService;
import com.sjiwon.studyholic.web.api.study.dto.request.StudyCreateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
