INSERT INTO users(name, nickname, login_id, login_password, birth, email, join_date, upload_name, storage_name, role, last_modified_date)
VALUES
    ('관리자', '관리자', 'admin', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-10', 'sjiwon4491@gmail.com', '2022-10-10 10:00:00', 'test.png', 'admin.png', 'ADMIN', now()),
    ('홍길동1', '스프링 초보', 'user1', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-02', 'test1@gmail.com', '2022-10-10 10:00:00', 'spring.png', '4a2935a79f.png', 'USER', now()),
    ('홍길동2', '코툴린', 'user2', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-03', 'test2@gmail.com', '2022-10-10 10:00:00', 'kotlin.png', '5be847dac7.png', 'USER', now()),
    ('홍길동3', 'Kotlin Programmer', 'user3', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-04', 'test3@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'USER', now()),
    ('홍길동4', '영한킴', 'user4', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-05', 'test4@gmail.com', '2022-10-10 10:00:00', 'woowa.png', '3f29fbe905.png', 'USER', now()),
    ('홍길동5', 'Whiteship', 'user5', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-06', 'test5@gmail.com', '2022-10-10 10:00:00', 'whiteship.png', '2b716c23e8.png', 'USER', now()),
    ('홍길동6', 'joduldu', 'user6', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-07', 'test6@gmail.com', '2022-10-10 10:00:00', 'joduldu.png', 'b49015de3f.png', 'USER', now()),
    ('홍길동7', 'DB Master', 'user7', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-08', 'test7@gmail.com', '2022-10-10 10:00:00', 'db.png', '19391465e3.png', 'USER', now()),
    ('홍길동8', 'Tech', 'user8', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-09', 'test8@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'USER', now()),
    ('홍길동9', '자바 마스터', 'user9', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-10', 'test9@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'USER', now()),
    ('홍길동10', '경기대 컴공', 'user10', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-11', 'test10@gmail.com', '2022-10-10 10:00:00', 'kgu.png', '2e6f267dbd.png', 'USER', now()),
    ('홍길동11', 'SNU', 'user11', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-12', 'test11@gmail.com', '2022-10-10 10:00:00', 'snu.png', 'a4ddc250c6.png', 'USER', now()),
    ('홍길동12', '네메', 'user12', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-13', 'test12@gmail.com', '2022-10-10 10:00:00', 'neme.png', 'a4cd37ba19.png', 'USER', now()),
    ('홍길동13', '흑호랑이', 'user13', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-14', 'test13@gmail.com', '2022-10-10 10:00:00', 'tiger.png', '130cbc719d.png', 'USER', now()),
    ('홍길동14', '강싸이', 'user14', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-15', 'test14@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'USER', now()),
    ('홍길동15', '슈거', 'user15', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-16', 'test15gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'USER', now()),
    ('홍길동16', '제임스 고슬링', 'user16', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-17', 'test16@gmail.com', '2022-10-10 10:00:00', 'james.png', 'c2ed49afc0.png', 'USER', now()),
    ('홍길동17', '컴파일러', 'user17', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-18', 'test17@gmail.com', '2022-10-10 10:00:00', 'flower.png', '7a4b7c3dfc.png', 'USER', now()),
    ('홍길동18', 'JVM', 'user18', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-19', 'test18@gmail.com', '2022-10-10 10:00:00', 'jvm.png', '9e321bc40f.png', 'USER', now()),
    ('홍길동19', 'Cyborg', 'user19', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-20', 'test19@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'USER', now()),
    ('홍길동20', 'boris', 'user20', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-21', 'test20@gmail.com', '2022-10-10 10:00:00', 'street.png', '36c58ebefb.png', 'USER', now()),
    ('홍길동21', '하얀나방', 'user21', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-22', 'test21@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'USER', now()),
    ('홍길동22', 'Kevin', 'user22', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-23', 'test22@gmail.com', '2022-10-10 10:00:00', 'kevin.png', '1925d3kdi2.png', 'USER', now()),
    ('홍길동23', 'Faker', 'user23', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-24', 'test23@gmail.com', '2022-10-10 10:00:00', 'faker.png', '46b31a9693.png', 'USER', now()),
    ('홍길동24', '플래시', 'user24', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-25', 'test24@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'USER', now()),
    ('홍길동25', '알고리즘', 'user25', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-26', 'test25@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'USER', now()),
    ('홍길동26', 'HTTP', 'user26', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-27', 'test26@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'USER', now()),
    ('홍길동27', 'bibe00', 'user27', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-28', 'test27@gmail.com', '2022-10-10 10:00:00', 'wolf.png', '6faa998c82.png', 'USER', now()),
    ('홍길동28', 'Sky', 'user28', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-29', 'test28@gmail.com', '2022-10-10 10:00:00', 'sky.png', '2de3280459.png', 'USER', now()),
    ('홍길동29', '사이다', 'user29', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-30', 'test29@gmail.com', '2022-10-10 10:00:00', 'default.png', 'default.png', 'USER', now()),
    ('홍길동30', '지크', 'user30', '{bcrypt}$2a$10$YmNbBCVmbQkndS2R/9Q9yO7617jGB2GR6NJU3vOZC/NqqQKf0wjCm', '1999-01-31', 'test30@gmail.com', '2022-10-10 10:00:00', 'cat.png', 'd00204d6f0.png', 'USER', now());

INSERT INTO study(name, max_member, brief_description, description, recruit_deadline, random_sequence, register_date, last_modified_date)
VALUES
    ('이펙티브 자바 스터디', 10, '이펙티브 자바를 통해서 자바에 대해 더 자세히 공부해보실분 구합니다', '<h3>    <strong>🔥 스터디 목표</strong></h3><p>    단순히 내용을 정리해서 발표하는 것으로 마무리 하지 않고</p><p>    서로 발표간에 궁금한 부분들과 발표에 있지 않은 내용이라도 공부하는 도중에 생긴 의문점을 같이 해결할 수 있는 스터디를 지향합니다</p><hr><h3>    <strong>📆 일정</strong></h3><ul style="list-style-type:disc;">    <li>        매주 토요일 PM 8시 ~ PM 10시        <ul>            <li>                아이템 총 90개            </li>            <li>                일주일에 5개의 아이템씩 공부하고 그에 대한 발표를 진행            </li>        </ul>    </li></ul><hr><h3>    <strong>✍️ 진행 방식</strong></h3><ul style="list-style-type:disc;">    <li>        스터디의 진행 방식은 다음과 같습니다        <ol>            <li style="list-style-type:disc;">                이펙티브 자바의 각 아이템을 읽고 자신의 블로그나 Github에 기록            </li>            <li style="list-style-type:disc;">                필요에 따라 <a href="https://www.inflearn.com/users/@whiteship">백기선님의 이펙티브 자바 강의</a> 수강            </li>            <li style="list-style-type:disc;">                매주 토요일에 발표 순서에 따라 각자 맡은 아이템에 대한 발표 및 토론            </li>        </ol>    </li></ul><p style="list-style-type:disc;">    <code>발표 및 토론은 줌 또는 구글 미트로 진행될 예정입니다</code></p><hr><h3 style="list-style-type:disc;">    <strong>🤝 이런 분들을 모십니다</strong></h3><blockquote>    <p style="list-style-type:disc;">        성장하고 싶은 개발자들을 환영합니다    </p></blockquote><ul>    <li style="list-style-type:disc;">        자바에 대한 기본 지식 보유 (실무 경험 우대)    </li>    <li style="list-style-type:disc;">        열심히 참여해주실 분    </li></ul>', DATE_FORMAT(ADDDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 8) HOUR), '%Y-%m-%d %H'), 'd0993873621b4397ae2515aeb253986a', DATE_FORMAT(SUBDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 6) HOUR), '%Y-%m-%d %H'), now()),
    ('코틀린 스터디', 5, '코틀린 같이 공부하실분~', '<h1>    <span style="font-size:22px;"><strong>📚코틀린 스터디 모집</strong></span></h1><hr><p>    코틀린에 대해서 학습하실 분 모집합니다</p><ul>    <li>        교재 : <code>Kotlin In Action</code>        <ul>            <li>                <a href="http://www.yes24.com/Product/Goods/55148593">구매 링크</a>            </li>        </ul>    </li>    <li>        목표 : Kotlin에 대한 깊은 이해    </li>    <li>        스터디 진행 : 매주 일요일 오전 11시    </li>    <li>        예상 모집 인원 : 5명    </li></ul><p>    &nbsp;</p><hr><h4>    <strong>⚒️스터디 주의 사항</strong></h4><blockquote>    <p>        정말 열심히 하실 분들만 신청해주세요    </p></blockquote>', DATE_FORMAT(ADDDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 9) HOUR), '%Y-%m-%d %H'), '5dd022c047fa40afa28427d6b5060702', DATE_FORMAT(SUBDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 5) HOUR), '%Y-%m-%d %H'), now()),
    ('초보와 함께 하는 스프링 스터디', 7, '자바 기반 웹 프레임워크인 스프링에 대해서 같이 공부하실분 모집합니다', '<h1>
    <strong>스프링 스터디에 여러분을 초대합니다</strong>
</h1>
<hr>
<p>
    이 스터디는 <code>스프링의 원리에 대해서 알고 싶은 분</code> 또는 <code>스프링에 대해 더 깊은 학습을 원하시는 분</code>들을 모집합니다
</p>
<p>
    &nbsp;
</p>
<h2>
    🧵스터디 타겟
</h2>
<ul>
    <li>
        <code><i>@Component</i></code><i>를 붙이면 스프링 빈으로 등록된다고 하던데 이게 어떻게 동작하는거지?</i> <strong>라는 물음을 가지신 분</strong>
    </li>
    <li>
        <i>요청 데이터를 받는 방법에는 대표적으로 </i><code><i>@RequestParam, @ModelAttribute, @RequestBody</i></code><i>가 있는데 어떻게 값들이 바인딩 되는거지?</i> <strong>라는 물음을 가지신 분</strong>
    </li>
    <li>
        <i>스프링은 </i><code><i>AOP, DI, 프록시</i></code><i> 같은 개념이 중요하다고 하는데 이게 도대체 뭐지?</i> <strong>라는 물음을 가지신 분</strong>
    </li>
</ul>
<p>
    &nbsp;
</p>
<h2>
    🔎스터디 진행 방법
</h2>
<ul>
    <li>
        본 스터디는 <a href="https://www.inflearn.com/users/@yh">인프런의 김영한님 스프링 강의</a>를 기초로 학습합니다
    </li>
    <li>
        매주 토요일에 각자 맡은 파트 별 발표를 진행하고 그에 따른 팀원들의 질문 및 토론이 이어집니다
    </li>
    <li>
        매주 스터디 리더가 다음 파트에 대한 문제를 내고 리더 및 팀원들은 해당 문제를 풀어서 Github에 PR을 보내고 상호간의 코드 리뷰를 진행합니다
    </li>
</ul>
<p>
    &nbsp;
</p>
<h2>
    ✏️ 문제 샘플
</h2>
<p>
    매주마다 문제풀이가 진행되고 다음은 문제의 예제입니다.
</p>
<pre><code class="Java">// MainController
@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;
    public ResponseEntity&lt;Void&gt; transactionalTestMethod() {
        mainService.methodA();
        return ResponseEntity.ok().build();
    }
}
// MainService
@Service
public class MainService {
    public void methodA() {
        // 비즈니스 로직 A ....
        methodB();
    }
    @Transactional
    public void methodB() {
        // 비즈니스 로직 B ....
    }
}</code></pre>
<p>
    <mark class="marker-yellow"><strong>Question</strong></mark>&nbsp;
</p>
<p>
    <code>MainController</code>에서는 현재 <code>MainService를 주입</code>받고 있습니다. 그리고 transactionalTestMethod에서는 <code>mainService의 methodA를 호출</code>하고 있습니다
</p>
<p>
    그러면 여기서 현재 methodB에는 @Transactional 애노테이션을 붙임으로써 트랜잭션 처리를 유도하고 있는데 과연 mainService.methodA()의 진행 과정에서 methodB에는 트랜잭션 처리가 정상적으로 될까요?
</p>
<p>
    만약 되지 않는다면 그에 대한 설명을 적어주세요
</p>
<p>
    &nbsp;
</p>
<blockquote>
    <p>
        정답은 <strong>“트랜잭션 처리가 되지 않는다”</strong> 입니다.
    </p>
    <p>
        그 이유는 …….
    </p>
</blockquote>
<p>
    &nbsp;
</p>', DATE_FORMAT(ADDDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 13) HOUR), '%Y-%m-%d %H'), '55fb383eee2c4fd383faa54cbde3ae0b', DATE_FORMAT(SUBDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 1) HOUR), '%Y-%m-%d %H'), now()),
    ('JPQL 스터디', 6, 'JPA의 필수요소 JPQL에 대해 공부하실분~', '<p>    여러분은 JPQL이라는 쿼리 언어를 들어보신적이 있으신가요?</p><p>    JPQL은 JPA를 통해서 웹 개발을 할 때 필연적으로 사용하는 쿼리 언어입니다.</p><p>    일반적인 <code>DB Query는 테이블을 대상</code>으로 쿼리를 작성하지만 <code>JPQL은 엔티티를 대상</code>으로 쿼리를 작성합니다</p><p>    여기서 더 나아가서 JPQL의 빌더 역할을 수행해주는 <code>QueryDSL</code>을 도입한다면 <code>type-safe하게 쿼리를 작성</code>할 수 있게 됩니다&nbsp;</p><hr><h3>    <strong>스터디 목표</strong></h3><ul>    <li>        이 스터디는 <code>기본적인 JPQL에 대한 이해</code>와 더불어서 <code>QueryDsl, jOOQ와 같은 type-safe하게 쿼리를 작성하는 방법</code>을 공부합니다    </li></ul><p>    &nbsp;</p><h3>    <strong>스터디 선수 지식</strong></h3><p>    아래 나열된 <code>기본 지식을 숙지하신 분들을 대상</code>으로 모집합니다</p><ul>    <li>        영속성 컨텍스트 개념    </li>    <li>        1차 캐시의 이점    </li>    <li>        지연로딩과 즉시로딩    </li>    <li>        JPA N + 1 문제 발생 원인과 그에 대한 해결책    </li>    <li>        DB Query에 대한 이해    </li></ul><p>    &nbsp;</p>', DATE_FORMAT(ADDDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 9) HOUR), '%Y-%m-%d %H'), 'c3d4e9a676e34e48b4937df37e706c50', DATE_FORMAT(SUBDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 5) HOUR), '%Y-%m-%d %H'), now()),
    ('디자인 패턴 - GoF', 5, '필수 디자인 패턴 같이 공부하실분 모집합니다', '<blockquote>    <p>        싱글톤 패턴, 빌더 패턴, 프록시 패턴, 메멘토 패턴, … 이 수많은 패턴들은 무엇일까?    </p></blockquote><p>    <code>디자인 패턴</code>은 소프트웨어 개발 시에 마주할 다양한 문제와 패턴을 모아놓은 지식의 산물입니다.</p><p>    <code>디자인 패턴</code>을 알고 있다면 보다 유연하고 재사용성이 뛰어난 <code>객체 지향적 소프트웨어</code>를 개발할 수 있습니다</p><hr><h3>    <strong>진행 방식</strong></h3><p>    본 스터디는 <a href="https://www.inflearn.com/course/%EB%94%94%EC%9E%90%EC%9D%B8-%ED%8C%A8%ED%84%B4">백기선님의 코딩으로 학습하는 GoF의 디자인 패턴</a>을 통해 이루어지는 스터디입니다</p><ol>    <li>        스터디 리더는 <code>매주마다 진행될 파트에 대한 Github Issue를 생성</code>합니다.    </li>    <li>        리더를 포함한 팀원들은 파트에 해당하는 강의를 듣고 <code>Notion이나 개인 블로그에 정리 후 해당 Issue에 링크</code>를 걸어 줍니다    </li>    <li>        모든 팀원들은 자신을 제외한 다른 팀원들의 정리글을 보고 피드백이나 궁금한 점을 토론날까지 기록해둡니다    </li>    <li>        토론은 <code>매주 토요일 PM 8시</code>에 진행되고 토론 시 간단한 패턴에 대한 설명과 궁금한 점을 서로 질의응답합니다    </li></ol><p>    &nbsp;</p><blockquote>    <p>        개발에 필수적인 디자인 패턴을 저희 스터디와 함께 공부하실분을 모집합니다    </p></blockquote>', DATE_FORMAT(ADDDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 4) HOUR), '%Y-%m-%d %H'), '8b1c9cab2fd6438abf8aa7a4281e0b72', DATE_FORMAT(SUBDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 10) HOUR), '%Y-%m-%d %H'), now()),
    ('CS 파헤치기', 6, '면접을 대비한 CS 공부하실분 모집합니다~', '<p>    <code>CS(Computer Science)</code>는 컴퓨터 공학 전공자라면 반드시 갖춰야 하는 지식이고 기업 면접 간에 굉장히 중요한 주제입니다.</p><p>    저희 스터디는<code> CS에 대한 기초/심화 개념을 리마인드</code>하고 <code>면접 대비 상호간의 피드백</code>을 지향하는 스터디입니다</p><p>    총 2달간 진행할 예정이고 진행 계획은 다음과 같습니다</p><ul>    <li>        주제는 CS(언어, 컴퓨터 구조, 자료구조, 운영체제, 네트워크, 데이터베이스, OOP)입니다    </li>    <li>        팀원들간에 각자 맡은 파트에 대해서 ppt 및 발표 준비를 합니다    </li>    <li>        발표는 매주 토요일 PM 10시에 진행이 됩니다    </li></ul><p>    &nbsp;</p><blockquote>    <p>        면접을 대비하고 싶으시거나 CS에 대한 리마인드를 원하시는 분들은 저희 스터디와 함께 하세요    </p></blockquote><p>    &nbsp;</p>', DATE_FORMAT(ADDDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 5) HOUR), '%Y-%m-%d %H'), '3ecfcab760c34771af2aff55f386502e', DATE_FORMAT(SUBDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 7) HOUR), '%Y-%m-%d %H'), now()),
    ('SQLD 시험 대비', 5, '이 스터디는 SQLD 자격증을 위한 스터디로써 내년 2 ~ 3월 SQLD 시험을 목표로 합니다', '<p>    2023년 2 ~ 3월 사이에 시행되는 <code>제 48회 SQL 개발자 : SQLD</code> 자격증을 대비해서 공부하실 분 모집합니다</p><ul>    <li>        예상 진행 일정 : 시험 시작 2주 전    </li>    <li>        교재 : SQLD 이론 정리본, 기출 시험지    </li></ul><p>    &nbsp;</p><p>    계획은 일단 1주 정도 SQLD 시험에 나오는 기본적인 이론에 대한 내용을 학습합니다.</p><p>    그리고 나머지 1주 동안은 기출문제를 풀고 그에 대한 오답을 서로 정리해주는 시간을 가질 예정입니다.</p><p>    저희와 함께 SQLD 시험을 대비하실 분 환영합니다.</p>', DATE_FORMAT(ADDDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 1) HOUR), '%Y-%m-%d %H'), '85f4bdbdf6e2440081a0b04deddd2e23', DATE_FORMAT(SUBDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 7) HOUR), '%Y-%m-%d %H'), now()),
    ('Spring Security', 6, 'Spring Security 공부하실분 구합니다', '<p>    <code>Spring Security</code>는 스프링 프레임워크를 통한 웹 개발 간에 <code>인증 및 인가에 대한 여러 API를 제공</code>해주고 <code>손쉽게 인증/인가 처리</code>를 할 수 있게 도와주는 프레임워크입니다.</p><p>    하지만 이러한 장점 속에 숨은 단점 또한 존재합니다</p><ul>    <li>        극한의 추상화로 인한 어려운 Flow 이해    </li>    <li>        예상치 못한 에러    </li>    <li>        …    </li></ul><p>    Spring Security는 인증/인가에 대해서 굉장히 편리하게 구현을 할 수 있지만 편리함 뒤에 숨은 복잡함 또한 존재합니다</p><p>    따라서 본 스터디는 Spring Security의 밑단부터 살펴보면서 여러 Work Flow들을 확실하게 이해할 수 있습니다</p><hr><h3>    <strong>진행 계획은 다음과 같습니다</strong></h3><ol>    <li>        <code>Authentication &amp; Authorization</code>에 대한 개념    </li>    <li>        Spring Security의 <code>Authentication Flow</code>        <ul style="list-style-type:disc;">            <li style="list-style-type:disc;">                AuthenticationFilter            </li>            <li style="list-style-type:disc;">                AuthenticationToken            </li>            <li style="list-style-type:disc;">                AuthenticationManager            </li>            <li style="list-style-type:disc;">                AuthenticationProvider            </li>            <li style="list-style-type:disc;">                AuthenticationSuccessHandler            </li>            <li style="list-style-type:disc;">                AuthenticationFailureHandler            </li>            <li style="list-style-type:disc;">                ….            </li>        </ul>    </li>    <li>        Spring Security의 <code>Authorization Flow</code>        <ul>            <li>                AccessDicisionManager            </li>            <li>                AccessDecisionVoter            </li>            <li>                AuthenticationEntryPoint            </li>            <li>                AccessDeniedHandler            </li>            <li>                ….            </li>        </ul>    </li>    <li>        Spring Security + OAuth2에 대한 개념 및 이해    </li></ol><p>    &nbsp;</p>', DATE_FORMAT(ADDDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 9) HOUR), '%Y-%m-%d %H'), 'fd95e0688a204904bb9406c9f1d6e3e0', DATE_FORMAT(SUBDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 5) HOUR), '%Y-%m-%d %H'), now()),
    ('자바 스터디', 4, '자바 기본 문법에 대해 학습하는 스터디입니다', '<p>    본 스터디는 <code>자바 기본 문법</code> 에 대해서 학습하는 스터디입니다</p><p>    총 <code>15주 동안</code> 스터디가 진행될 예정입니다</p><hr><h3>    <strong>주차별 진행 계획</strong></h3><ul style="list-style-type:disc;">    <li>        <code>1주차</code> : JVM은 무엇이고 자바 코드는 어떻게 실행이 되는가    </li>    <li>        <code>2주차</code> : 자바 데이터 타입, 변수 그리고 배열    </li>    <li>        <code>3주차</code> : 연산자    </li>    <li>        <code>4주차</code> : 제어문    </li>    <li>        <code>5주차</code> : 클래스    </li>    <li>        <code>6주차</code> : 상속    </li>    <li>        <code>7주차</code> : 패키지    </li>    <li>        <code>8주차</code> : 인터페이스    </li>    <li>        <code>9주차</code> : 예외 처리    </li>    <li>        <code>10주차</code> : 멀티쓰레드 프로그래밍    </li>    <li>        <code>11주차</code> : Enm    </li>    <li>        <code>12주차</code> : 애노테이션    </li>    <li>        <code>13주차</code> : I/O    </li>    <li>        <code>14주차</code> : 제네릭    </li>    <li>        <code>15주차</code> : 람다    </li></ul><p>    &nbsp;</p><blockquote>    <p>        주차별 세부 카테고리는 스터디 시작 후 PDF 파일로 배포 예정입니다.    </p>    <p>        자바에 대해서 리마인드 하고 싶으신 분 또는 자바를 제대로 배워보고 싶으신 분들은 스터디에 참여해주세요    </p></blockquote><p>    &nbsp;</p>', DATE_FORMAT(ADDDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 7) HOUR), '%Y-%m-%d %H'), '074ce5fff6ed4df39826a3b83d8188df', DATE_FORMAT(SUBDATE(CURRENT_TIMESTAMP, INTERVAL (RAND() * 24 * 7) HOUR), '%Y-%m-%d %H'), now());

INSERT INTO study_tag(study_id, tag)
VALUES
    (1, '이펙티브 자바'),
    (1, '자바'),
    (1, '프로그래밍 언어'),
    (1, '백기선'),

    (2, '코틀린'),
    (2, 'Kotlin In Action'),
    (2, '프로그래밍 언어'),
    (2, '자바'),
    (2, 'JVM'),

    (3, '자바'),
    (3, '스프링'),
    (3, '스프링 프레임워크'),
    (3, '스프링 부트'),
    (3, 'Spring'),
    (3, 'Spring Boot'),
    (3, '김영한'),

    (4, '김영한'),
    (4, 'JPA'),
    (4, 'Spring Data JPA'),
    (4, 'QueryDSL'),
    (4, 'JPQL'),

    (5, '디자인 패턴'),
    (5, '자바'),
    (5, 'GoF'),
    (5, '백기선'),
    (5, '인프런'),

    (6, 'CS'),
    (6, 'CS 전공 지식'),
    (6, '운영체제'),
    (6, '네트워크'),
    (6, 'DB'),
    (6, '자료구조'),

    (7, 'SQLD'),
    (7, '자격증'),
    (7, 'DB'),

    (8, 'Spring Security'),
    (8, 'Spring Boot'),
    (8, '스프링 시큐리티'),
    (8, '인증'),
    (8, '인가'),

    (9, '자바'),
    (9, '자바의 정석'),
    (9, 'IntelliJ'),
    (9, '남궁성');

INSERT INTO user_study(user_id, study_id, is_team_leader)
VALUES
    (5, 1, 1),
    (3, 1, 0),
    (2, 1, 0),
    (7, 1, 0),
    (10, 1, 0),

    (4, 2, 1),
    (21, 2, 0),
    (13, 2, 0),
    (29, 2, 0),

    (2, 3, 1),
    (6, 3, 0),
    (22, 3, 0),
    (16, 3, 0),
    (26, 3, 0),
    (3, 3, 0),

    (23, 4, 1),
    (26, 4, 0),
    (7, 4, 0),

    (17, 5, 1),
    (18, 5, 0),
    (19, 5, 0),
    (11, 5, 0),
    (10, 5, 0),

    (12, 6, 1),
    (24, 6, 0),
    (28, 6, 0),
    (30, 6, 0),
    (10, 6, 0),

    (8, 7, 1),
    (12, 7, 0),
    (13, 7, 0),

    (30, 8, 1),
    (2, 8, 0),

    (25, 9, 1),
    (6, 9, 0),
    (3, 9, 0),
    (11, 9, 0);