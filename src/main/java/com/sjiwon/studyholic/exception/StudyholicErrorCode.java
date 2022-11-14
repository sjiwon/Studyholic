package com.sjiwon.studyholic.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum StudyholicErrorCode {
    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자 정보가 존재하지 않습니다"),
    WRONG_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다"),
    DUPLICATE_USER_NICKNAME(HttpStatus.CONFLICT, "중복된 닉네임입니다"),
    DUPLICATE_USER_LOGIN_ID(HttpStatus.CONFLICT, "중복된 로그인 아이디입니다"),
    DUPLICATE_USER_EMAIL(HttpStatus.CONFLICT, "중복된 이메일입니다"),
    EMAIL_NEVER_AUTHENTICATED(HttpStatus.CONFLICT, "인증내역이 없는 이메일입니다"),
    ILLEGAL_REQUEST_BY_ANONYMOUS(HttpStatus.BAD_REQUEST, "타인의 정보에 접근할 수 없습니다"),
    SAME_USER_NICKNAME_AS_BEFORE(HttpStatus.CONFLICT, "이전과 동일한 닉네임은 사용 불가능합니다"),

    // Study
    STUDY_NOT_FOUND(HttpStatus.NOT_FOUND, "스터디 정보가 존재하지 않습니다"),
    BAD_UPDATE_REQUEST_FROM_ANONYMOUS_USER(HttpStatus.BAD_REQUEST, "스터디 수정은 스터디 리더만 가능합니다"),
    DUPLICATE_STUDY_NAME(HttpStatus.CONFLICT, "중복된 스터디 이름입니다"),

    ;

    private final HttpStatus status;
    private final String message;

    StudyholicErrorCode(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
