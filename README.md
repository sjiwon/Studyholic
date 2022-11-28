# 🔥 Studyholic

> 자유롭게 스터디를 만들고 여러 사람들과 스터디를 진행하기 위한 웹 애플리케이션 서비스

- [스터디 설명 영상 바로가기](https://www.canva.com/design/DAFS9EAmuT0/XOB5PHaDTQaXCiGU7j6CFQ/watch?utm_content=DAFS9EAmuT0&utm_campaign=share_your_design&utm_medium=link&utm_source=shareyourdesignpanel)
- [스프링 스터디 문제 해설 바로가기](문제%20해설/스프링%20문제%20해설.md)
- [실행 가이드 바로가기](리드미%20추가%20자료/docs/실행%20가이드.md)

<br>
<hr>

# 💻 개발 환경

### Version 1

![](리드미%20추가%20자료/images/기술%20스택.png)

<br>
<hr>

# ⚒️ 기능 개발

## 1. View

- 메인페이지 : <code>GET /</code>
- 로그인 페이지 : <code>GET /login</code>
- 회원가입 페이지 : <code>GET /signup</code>
- 마이페이지 : <code>GET /mypage</code>
- 참여중인 스터디 페이지 : <code>GET /mypage/study</code>
- 아이디 찾기 페이지 : <code>GET /find-id</code>
- 비밀번호 초기화 페이지 : <code>GET /reset-password</code>
- 비밀번호 변경 페이지 : <code>GET /change-password</code>
- 스터디 상세 정보 페이지 : <code>GET /study/{randomSequence}</code>
- 스터디 등록 페이지 : <code>GET /study/post</code>
- 스터디 수정 페이지 : <code>GET /study/edit?seq={randomSequence}</code>

<br>


## 2. API

### 사용자 API

- 회원가입 API Version 1 (커스텀 업로드 이미지) : <code>POST /api/user</code>
- 회원가입 API Version 2 (서버 기본 제공 이미지) : <code>POST /api/user/default-profile</code>
- 사용자 프로필 이미지 변경 API Version 1 (커스텀 업로드 이미지) <code>PATCH /api/user/change-profile</code>
- 사용자 프로필 이미지 변경 API Version 2 (서버 기본 제공 이미지) <code>PATCH /api/user/change-default-profile</code>
- 사용자 닉네임 변경 API : <code>PATCH /api/user/change-nickname</code>
- 사용자 정보 중복 체크 API : <code>POST /api/user/duplicate-check</code>
    - 회원가입 닉네임 중복 체크
    - 회원가입 아이디 중복 체크
- 아이디 찾기 API : <code>POST /api/user/find/login-id</code>
- 임시 비밀번호 발급 API : <code>POST /api/user/random-password</code>
- 비밀번호 변경 API : <code>PATCH /api/user/change-password</code>

### 스터디 API

- 스터디 생성 API : <code>POST /api/study</code>
- 스터디 정보 수정 API : <code>PATCH /api/study/{studyId}</code>
- 스터디 정보 수정에 대한 권한 체크 API : <code>POST /api/study/{studyId}/validate/{userId}</code>
- 스터디 삭제 API : <code>DELETE /api/study/{studyId}</code>
- 스터디 중복 체크 API <code>POST /api/study/{type}/duplicate-check</code>
    - 스터디 등록 간 스터디 이름 중복 체크
    - 스터디 수정 간 스터디 이름 중복 체크

### 사용자 <-> 스터디 관련 API

- 스터디 참여 API : <code>POST /api/user/join-study</code>
- 스터디 참여 취소 API : <code>POST /api/user/cancel-study</code>

### 이메일 인증 API

- 이메일 인증 API : <code>POST /api/email/authenticate</code>
    - 회원가입 이메일 인증
    - 아이디 찾기 이메일 인증
    - 비밀번호 찾기 이메일 인증
