DROP TABLE IF EXISTS study_tag;
DROP TABLE IF EXISTS user_study;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS study;

CREATE TABLE users(
    id BIGINT AUTO_INCREMENT COMMENT '회원 ID(PK)',
    name VARCHAR(30) NOT NULL COMMENT '회원 이름',
    nickname VARCHAR(30) NOT NULL UNIQUE COMMENT '회원 이름',
    login_id VARCHAR(30) NOT NULL UNIQUE COMMENT '회원 로그인 ID',
    login_password VARCHAR(100) NOT NULL COMMENT '회원 로그인 비밀번호',
    birth DATE NOT NULL COMMENT '회원 생년월일',
    email VARCHAR(100) NOT NULL UNIQUE COMMENT '회원 이메일',
    join_date DATETIME NOT NULL COMMENT '회원가입 날짜',
    upload_name VARCHAR(100) NOT NULL COMMENT '업로드한 회원 사진명',
    storage_name VARCHAR(40) NOT NULL COMMENT '서버에 저장하는 회원 사진명',
    last_modified_date DATETIME NOT NULL COMMENT '마지막 회원정보 수정 날짜',

    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE study(
    id BIGINT AUTO_INCREMENT COMMENT '스터디 팀 ID(PK)',
    name VARCHAR(80) NOT NULL UNIQUE COMMENT '스터디 팀명',
    max_member INT NOT NULL COMMENT '스터디 최대 모집 멤버 수',
    brief_description VARCHAR(200) NOT NULL COMMENT '스터디 설명',
    description MEDIUMTEXT NOT NULL COMMENT '스터디 설명',
    recruit_deadline DATE NOT NULL COMMENT '스터디 모집 마감일',
    register_date DATETIME NOT NULL COMMENT '스터디 등록 날짜',
    last_modified_date DATETIME NOT NULL COMMENT '마지막 스터디 팀 정보 수정 날짜',

    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE user_study(
    id BIGINT AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    study_id BIGINT NOT NULL,
    is_team_leader TINYINT(1) NOT NULL,

    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE study_tag(
    id BIGINT AUTO_INCREMENT,
    study_id BIGINT NOT NULL,
    tag VARCHAR(100) NOT NULL,
    
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- FK 설정
ALTER TABLE user_study
ADD CONSTRAINT member_study_ibfk1_user_id
FOREIGN KEY (user_id)
REFERENCES users(id);

ALTER TABLE user_study
ADD CONSTRAINT member_study_ibfk2_study_id
FOREIGN KEY (study_id)
REFERENCES study(id);

ALTER TABLE study_tag
ADD CONSTRAINT study_tech_ibfk1_study_id
FOREIGN KEY (study_id)
REFERENCES study(id);