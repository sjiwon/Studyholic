INSERT INTO users(name, nickname, login_id, login_password, birth, email, join_date, upload_name, storage_name, role, last_modified_date)
VALUES
    ('관리자', '관리자', 'admin', '1234', '1999-01-01', 'test1@gmail.com', '2022-10-10 10:00:00', 'test.png', 'admin.png', 'ROLE_ADMIN', now()),
    ('홍길동1', 'User1', 'user1', '1234', '1999-01-01', 'test2@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'ROLE_USER', now()),
    ('홍길동2', 'User2', 'user2', '1234', '1999-01-01', 'test3@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'ROLE_USER', now()),
    ('홍길동3', 'User3', 'user3', '1234', '1999-01-01', 'test4@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'ROLE_USER', now()),
    ('홍길동4', 'User4', 'user4', '1234', '1999-01-01', 'test5@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'ROLE_USER', now()),
    ('홍길동5', 'User5', 'user5', '1234', '1999-01-01', 'test6@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'ROLE_USER', now()),
    ('홍길동6', 'User6', 'user6', '1234', '1999-01-01', 'test7@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'ROLE_USER', now()),
    ('홍길동7', 'User7', 'user7', '1234', '1999-01-01', 'test8@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'ROLE_USER', now()),
    ('홍길동8', 'User8', 'user8', '1234', '1999-01-01', 'test9@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'ROLE_USER', now()),
    ('홍길동9', 'User9', 'user9', '1234', '1999-01-01', 'test10@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'ROLE_USER', now()),
    ('홍길동10', 'User10', 'user10', '1234', '1999-01-01', 'test11@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'ROLE_USER', now());

INSERT INTO study(name, max_member, brief_description, description, recruit_deadline, register_date, last_modified_date)
VALUES
    ('이펙티브 자바 스터디', 7, '이펙티브 자바를 통해서 자바에 대해 더 자세히 공부해보실분 구합니다', '설명 1', ADDDATE(CURRENT_DATE, 4), SUBDATE(CURRENT_DATE, 3), now()),
    ('코틀린 스터디', 5, '코틀린 같이 공부하실분~', '설명 2', ADDDATE(CURRENT_DATE, 10), SUBDATE(CURRENT_DATE, 2), now()),
    ('스프링 스터디', 5, '자바 기반 웹 프레임워크인 스프링에 대해서 같이 공부하실분 모집합니다', '설명 3', ADDDATE(CURRENT_DATE, 12), '2022-07-21 21:13:00', now()),
    ('JPA 스터디', 6, '자바 ORM 표준 JPA 같이 공부하실분~', '설명 4', ADDDATE(CURRENT_DATE, 15), SUBDATE(CURRENT_DATE, 1), now()),
    ('QueryDSL 스터디', 6, '기존 JPQL의 동적 쿼리 문제를 해결하기 위한 QueryDSL 스터디 입니다', '설명 5', ADDDATE(CURRENT_DATE, 30), SUBDATE(CURRENT_DATE, 5), now()),
    ('디자인 패턴 스터디', 5, '약 23개의 디자인 패턴 같이 공부하실분 모집합니다', '설명 6', ADDDATE(CURRENT_DATE, 16), SUBDATE(CURRENT_DATE, 10), now()),
    ('JSP 스터디', 6, 'JSP 공부하실분', '설명 7', ADDDATE(CURRENT_DATE, 2), SUBDATE(CURRENT_DATE, 3), now()),
    ('Spring Data JPA 스터디', 4, 'Spring Data JPA 스터디', '설명 8', ADDDATE(CURRENT_DATE, 7), SUBDATE(CURRENT_DATE, 8), now()),
    ('SQLD 스터디', 5, '이 스터디는 SQLD 자격증을 위한 스터디로써 내년 1월 SQLD 시험을 목표로 합니다', '설명 9', ADDDATE(CURRENT_DATE, 1), SUBDATE(CURRENT_DATE, 7), now()),
    ('Spring Security 스터디', 6, 'Spring Security 공부하실분 구합니다', '설명 10', ADDDATE(CURRENT_DATE, 24), SUBDATE(CURRENT_DATE, 4), now()),
    ('Spring Batch 스터디', 4, 'Batch Job에 대한 수많은 API를 제공해주는 Spring Batch 공부하실분~', '설명 11', ADDDATE(CURRENT_DATE, 20), SUBDATE(CURRENT_DATE, 7), now());

INSERT INTO study_tag(study_id, tag)
VALUES
    (1, '이펙티브 자바'),
    (1, '자바'),
    (1, '프로그래밍 언어'),

    (2, '코틀린'),
    (2, 'Kotlin In Action'),
    (2, '프로그래밍 언어'),
    (2, '자바'),

    (3, '자바'),
    (3, '스프링'),
    (3, '스프링 프레임워크'),
    (3, '스프링 부트'),
    (3, 'Spring Boot'),
    (3, '김영한'),

    (4, '김영한'),
    (4, 'JPA'),
    (4, 'Spring Data JPA'),
    (4, 'QueryDSL'),
    (4, 'JPQL'),

    (5, 'JPA'),
    (5, 'Spring Data JPA'),
    (5, 'QueryDSL'),

    (6, '디자인 패턴'),
    (6, '자바'),
    (6, 'GoF'),

    (7, 'JSP'),
    (7, '자바'),
    (7, '뷰 템플릿'),

    (8, 'Spring Data JPA'),
    (8, 'JPA'),
    (8, 'Spring Boot'),

    (9, 'SQLD'),
    (9, '자격증'),
    (9, 'DB'),

    (10, 'Spring Security'),
    (10, 'Spring Boot'),
    (10, '스프링 시큐리티'),

    (11, 'Spring Batch'),
    (11, 'Spring Boot'),
    (11, '스프링 배치');

INSERT INTO user_study(user_id, study_id, is_team_leader)
VALUES
    (1, 1, 1),
    (2, 1, 0),
    (5, 1, 0),
    (7, 1, 0),
    (10, 1, 0),

    (1, 2, 1),
    (4, 2, 0),
    (5, 2, 0),
    (8, 2, 0),
    (9, 2, 0),

    (5, 3, 1),

    (2, 4, 1),
    (3, 4, 0),
    (5, 4, 0),

    (4, 5, 1),
    (1, 5, 0),
    (3, 5, 0),
    (9, 5, 0),
    (10, 5, 0),

    (9, 6, 1),
    (3, 6, 0),

    (10, 7, 1),
    (1, 7, 0),
    (9, 7, 0),
    (11, 7, 0),

    (11, 8, 1),
    (2, 8, 0),
    (5, 8, 0),

    (8, 9, 1),
    (6, 9, 0),

    (11, 10, 1),
    (3, 10, 0),
    (7, 10, 0),
    (8, 10, 0),
    (1, 10, 0),
    (2, 10, 0),

    (1, 11, 1);
