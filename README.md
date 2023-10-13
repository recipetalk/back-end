## 결과물
[앱 빌드 파일](https://khj745700.notion.site/430d0fdc6a08437fb28a8db813ca6881)

# 기술 스택
>### Spring Framework(Spring Boot 3.0.1)
>### MySQL
>### Spring Data JPA
>### Jenkins, githubAction


# API 명세

[링크](https://www.notion.so/khj745700/04561b1c96e7455d8037818deb67bc48?v=8c466a55b30a49b2ac5aa619234b5f7a)



<br>
<br>
<br>
<br>

# 아키텍처
### 시스템 아키텍처
<img width="1255" alt="스크린샷 2023-10-05 오후 8 13 02" src="https://github.com/recipetalk/back-end/assets/68643347/5fd12167-002e-4f14-9d9f-d8d416915673">



<br>
<br>
<br>



### ERD

![ERD](https://github.com/recipetalk/back-end/assets/68643347/80b4b8d7-51b1-4cc2-bb4f-72e6a6ff261a)

<br>
<br>
<br>


# Project Structure

```bash

main/java/com/solution/recipetalk
├── annotation // 커스텀 어노테이션 관련
│   └── jwt
├── async // 비동기 관련
│   ├── config
│   └── notification
├── batch // 배치 관련
├── config // 스프링 부트 기본 및 커스텀 설정(Mail, Multipart, QueryDSL...)
├── controller // 컨트롤러
├── domain // 도메인 엔티티
│   ├── board
│   ├── comment
│   ├── fcm
│   ├── ingredient
│   ├── notification
│   ├── product
│   ├── recipe
│   ├── report
│   └── user
├── dto 
├── exception // 커스텀 예외 및 에러 클래스
│   └── dto
├── listener // 어플리케이션 리스너
├── s3 // aws s3 설정 및 handler 클래스
│   ├── config
│   └── upload
├── security // spring security, 인증 / 인가
├── service // 유저 도메인
├── util // 유저 도메인
└── vo // 변경되면 안되는 객체 클래스

```

# 서비스 소개


이 프로젝트는 만개의 레시피를 모티브로 한 레시피 제공 서비스에 식재료 관리 기능을 추가한 애플리케이션입니다. 코드 작성의 깔끔함과 유지보수의 중요성, 그리고 안전성을 고려하여 많은 노력을 기울였습니다.



<br>
<br>
<br>


# installation


