package com.sjiwon.studyholic.common;

import java.util.HashMap;
import java.util.Map;

public class VariableFactory {
    public static final String SESSION_KEY = "SPECIAL_SESSION";

    public static final String DEFAULT_IMAGE_NAME = "default.png";

    public static final String AUTHENTICATION_EMAIL_SUBJECT = "[Studyholic] 회원가입 이메일 인증";
    public static final String AUTHENTICATION_LOGIN_ID_SUBJECT = "[Studyholic] 아이디 찾기 이메일 인증";
    public static final String AUTHENTICATION_PASSWORD_SUBJECT = "[Studyholic] 비밀번호 찾기 이메일 인증";
    public static final String RANDOM_PASSWORD_SUBJECT = "[Studyholic] 임시 비밀번호 발급";
    public static final String AUTHENTICATION_EMAIL_BODY = "인증번호";
    public static final String RANDOM_PASSWORD_EMAIL_BODY = "임시 비밀번호";

    public static final int SIZE_PER_PAGE = 6; // 데이터 페이징 기준
    public static final int RANGE_PER_PAGE = 10; // 페이지 상에서 range 범위

    public static final String REGISTER_DATE_KO = "등록 날짜";
    public static final String REGISTER_DATE_ENG = "Register Date";
    public static final String REGISTER_DATE_SORT = "registerDate";
    public static final String POPULARITY_KO = "참여 인원";
    public static final String POPULARITY_ENG = "Popularity";
    public static final String POPULARITY_SORT = "popularity";
    public static final String RECRUIT_DEADLINE_KO = "모집 마감일";
    public static final String RECRUIT_DEADLINE_ENG = "Recruit Deadline";
    public static final String RECRUIT_DEADLINE_SORT = "recruitDeadline";
    public static final String MAX_MEMBER_KO = "모집 정원";
    public static final String MAX_MEMBER_ENG = "Max Member";
    public static final String MAX_MEMBER_SORT = "maxMember";
    public static final Map<String, String> SORT_TO_KO = new HashMap<>() {
        {
            put(REGISTER_DATE_SORT, REGISTER_DATE_KO);
            put(POPULARITY_SORT, POPULARITY_KO);
            put(RECRUIT_DEADLINE_SORT, RECRUIT_DEADLINE_KO);
            put(MAX_MEMBER_SORT, MAX_MEMBER_KO);
        }
    };
    public static final Map<String, String> SORT_TO_ENG = new HashMap<>() {
        {
            put(REGISTER_DATE_SORT, REGISTER_DATE_ENG);
            put(POPULARITY_SORT, POPULARITY_ENG);
            put(RECRUIT_DEADLINE_SORT, RECRUIT_DEADLINE_ENG);
            put(MAX_MEMBER_SORT, MAX_MEMBER_ENG);
        }
    };
}
