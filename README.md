# ๐ฅ Studyholic

> ์์ ๋กญ๊ฒ ์คํฐ๋๋ฅผ ๋ง๋ค๊ณ  ์ฌ๋ฌ ์ฌ๋๋ค๊ณผ ์คํฐ๋๋ฅผ ์งํํ๊ธฐ ์ํ ์น ์ ํ๋ฆฌ์ผ์ด์ ์๋น์ค

- [์คํฐ๋ ์ค๋ช ์์ ๋ฐ๋ก๊ฐ๊ธฐ](https://www.canva.com/design/DAFS9EAmuT0/XOB5PHaDTQaXCiGU7j6CFQ/watch?utm_content=DAFS9EAmuT0&utm_campaign=designshare&utm_medium=link&utm_source=publishsharelink)
- [์คํ๋ง ์คํฐ๋ ๋ฌธ์  ํด์ค ๋ฐ๋ก๊ฐ๊ธฐ](https://github.com/sjiwon/Studyholic/blob/main/%EC%8A%A4%ED%94%84%EB%A7%81%20%EB%AC%B8%EC%A0%9C%20%ED%95%B4%EC%84%A4.md)
- [์คํ ๊ฐ์ด๋ ๋ฐ๋ก๊ฐ๊ธฐ](https://github.com/sjiwon/Studyholic/blob/main/%EC%8B%A4%ED%96%89%20%EA%B0%80%EC%9D%B4%EB%93%9C.md)

<br>
<hr>

# ๐ป ๊ฐ๋ฐ ํ๊ฒฝ

### Version 1

![๊ธฐ์  ์คํ](https://user-images.githubusercontent.com/51479381/204522481-42d58110-45e9-4d1f-8234-3edb108cada9.png)


<br>
<hr>

# โ๏ธ ๊ธฐ๋ฅ ๊ฐ๋ฐ

## 1. View

- ๋ฉ์ธํ์ด์ง : <code>GET /</code>
- ๋ก๊ทธ์ธ ํ์ด์ง : <code>GET /login</code>
- ํ์๊ฐ์ ํ์ด์ง : <code>GET /signup</code>
- ๋ง์ดํ์ด์ง : <code>GET /mypage</code>
- ์ฐธ์ฌ์ค์ธ ์คํฐ๋ ํ์ด์ง : <code>GET /mypage/study</code>
- ์์ด๋ ์ฐพ๊ธฐ ํ์ด์ง : <code>GET /find-id</code>
- ๋น๋ฐ๋ฒํธ ์ด๊ธฐํ ํ์ด์ง : <code>GET /reset-password</code>
- ๋น๋ฐ๋ฒํธ ๋ณ๊ฒฝ ํ์ด์ง : <code>GET /change-password</code>
- ์คํฐ๋ ์์ธ ์ ๋ณด ํ์ด์ง : <code>GET /study/{randomSequence}</code>
- ์คํฐ๋ ๋ฑ๋ก ํ์ด์ง : <code>GET /study/post</code>
- ์คํฐ๋ ์์  ํ์ด์ง : <code>GET /study/edit?seq={randomSequence}</code>

<br>


## 2. API

### ์ฌ์ฉ์ API

- ํ์๊ฐ์ API Version 1 (์ปค์คํ ์๋ก๋ ์ด๋ฏธ์ง) : <code>POST /api/user</code>
- ํ์๊ฐ์ API Version 2 (์๋ฒ ๊ธฐ๋ณธ ์ ๊ณต ์ด๋ฏธ์ง) : <code>POST /api/user/default-profile</code>
- ์ฌ์ฉ์ ํ๋กํ ์ด๋ฏธ์ง ๋ณ๊ฒฝ API Version 1 (์ปค์คํ ์๋ก๋ ์ด๋ฏธ์ง) <code>PATCH /api/user/change-profile</code>
- ์ฌ์ฉ์ ํ๋กํ ์ด๋ฏธ์ง ๋ณ๊ฒฝ API Version 2 (์๋ฒ ๊ธฐ๋ณธ ์ ๊ณต ์ด๋ฏธ์ง) <code>PATCH /api/user/change-default-profile</code>
- ์ฌ์ฉ์ ๋๋ค์ ๋ณ๊ฒฝ API : <code>PATCH /api/user/change-nickname</code>
- ์ฌ์ฉ์ ์ ๋ณด ์ค๋ณต ์ฒดํฌ API : <code>POST /api/user/duplicate-check</code>
    - ํ์๊ฐ์ ๋๋ค์ ์ค๋ณต ์ฒดํฌ
    - ํ์๊ฐ์ ์์ด๋ ์ค๋ณต ์ฒดํฌ
- ์์ด๋ ์ฐพ๊ธฐ API : <code>POST /api/user/find/login-id</code>
- ์์ ๋น๋ฐ๋ฒํธ ๋ฐ๊ธ API : <code>POST /api/user/random-password</code>
- ๋น๋ฐ๋ฒํธ ๋ณ๊ฒฝ API : <code>PATCH /api/user/change-password</code>

### ์คํฐ๋ API

- ์คํฐ๋ ์์ฑ API : <code>POST /api/study</code>
- ์คํฐ๋ ์ ๋ณด ์์  API : <code>PATCH /api/study/{studyId}</code>
- ์คํฐ๋ ์ ๋ณด ์์ ์ ๋ํ ๊ถํ ์ฒดํฌ API : <code>POST /api/study/{studyId}/validate/{userId}</code>
- ์คํฐ๋ ์ญ์  API : <code>DELETE /api/study/{studyId}</code>
- ์คํฐ๋ ์ค๋ณต ์ฒดํฌ API <code>POST /api/study/{type}/duplicate-check</code>
    - ์คํฐ๋ ๋ฑ๋ก ๊ฐ ์คํฐ๋ ์ด๋ฆ ์ค๋ณต ์ฒดํฌ
    - ์คํฐ๋ ์์  ๊ฐ ์คํฐ๋ ์ด๋ฆ ์ค๋ณต ์ฒดํฌ

### ์ฌ์ฉ์ <-> ์คํฐ๋ ๊ด๋ จ API

- ์คํฐ๋ ์ฐธ์ฌ API : <code>POST /api/user/join-study</code>
- ์คํฐ๋ ์ฐธ์ฌ ์ทจ์ API : <code>POST /api/user/cancel-study</code>

### ์ด๋ฉ์ผ ์ธ์ฆ API

- ์ด๋ฉ์ผ ์ธ์ฆ API : <code>POST /api/email/authenticate</code>
    - ํ์๊ฐ์ ์ด๋ฉ์ผ ์ธ์ฆ
    - ์์ด๋ ์ฐพ๊ธฐ ์ด๋ฉ์ผ ์ธ์ฆ
    - ๋น๋ฐ๋ฒํธ ์ฐพ๊ธฐ ์ด๋ฉ์ผ ์ธ์ฆ
