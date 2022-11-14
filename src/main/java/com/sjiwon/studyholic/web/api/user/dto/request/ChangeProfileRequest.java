package com.sjiwon.studyholic.web.api.user.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ChangeProfileRequest {
    @ApiModelProperty(value = "정보 변경할 사용자 ID(PK)", example = "1", required = true)
    private Long userId;

    @ApiModelProperty(value = "변경할 프로필 이미지", required = true)
    private MultipartFile profile;
}
