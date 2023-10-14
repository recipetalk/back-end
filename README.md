## 결과물
[앱 빌드 파일](https://khj745700.notion.site/430d0fdc6a08437fb28a8db813ca6881)

# 기술 스택
>### Spring Framework(Spring Boot 3.0.1)
>### MySQL
>### Spring Data JPA
>### Jenkins, githubAction


<br>
<br>

# 아키텍처
### 시스템 아키텍처
<img width="1255" alt="스크린샷 2023-10-05 오후 8 13 02" src="https://github.com/recipetalk/back-end/assets/68643347/5fd12167-002e-4f14-9d9f-d8d416915673">



<br>
<br>



### ERD

![ERD](https://github.com/recipetalk/back-end/assets/68643347/80b4b8d7-51b1-4cc2-bb4f-72e6a6ff261a)

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

<br>
<br>
<br>


# API 명세

[링크](https://www.notion.so/khj745700/04561b1c96e7455d8037818deb67bc48?v=8c466a55b30a49b2ac5aa619234b5f7a)

<br>
<br>




# installation
corretto-jdk-17.0.4 버전과 mysql 8.0으로 구현되었습니다.
<br>
<br>
<br>

# Environment
환경 변수는 프로젝트의 루트 패스 기준으로 src/main/resources/application.yml 에 아래 내용을 기입해 주시면 됩니다.

<img width="435" alt="스크린샷 2023-10-15 오전 12 00 35" src="https://github.com/recipetalk/back-end/assets/68643347/82e359ee-c378-4af8-843a-9c040d447d3b">


### 환경 변수 내용
- DB 정보
- JPA 설정 정보
- SMTP 정보
- JWT 설정 정보
- AWS 설정 정보
- multipart 설정 정보

```yaml

### DB ###
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: ### 채우기 필수 ###
    username: ### 채우기 필수 ###
    password: ### 채우기 필수 ###

### JPA ###
  jpa:
    show-sql: true
    open-in-view: false 
    hibernate:
      ddl-auto: create ### table 생성 ###
    properties:
      hibernate:
        highlight_sql: true
        format_sql: true

### SMTP 설정 ###
  mail:
    host: ## 채우기 필수 ##
    port: ## 채우기 필수 ##
    username: ## 채우기 필수 ##
    password: ## 채우기 필수 ##
    properties:
      mail:
        transport:
          protocol: smtp
        smtp:
          auth: true
          starttls:
            enable: true
          ssl:
            trust: ## 채우기 필수 ##
            enable: true
        debug: false
    title: '[레시피톡] 이메일 인증 안내입니다'

### FCM 설정 ###

fcm:
  project-id: ## 채우기 필수 ##
  secret-key-dir: ## 채우기 필수 ##
  v1-url: ## 채우기 필수 ##
  scope: ## 채우기 필수 ##

### JWT 설정 정보 ###
jwt:
  issuer: solution.recipetalk
  secret-key: ## 채우기 필수 ##
  expiry-period: 1440 #분단위 1일

### AWS 설정 정보 ###
cloud:
  aws:
    credentials:
      access-key: ## 채우기 필수 ##
      secret-key: ## 채우기 필수 ##
    s3:
      bucket: ## 채우기 필수 ##
    region:
      static: ## 채우기 필수 ##
    stack:
      auto: false

### multipart 설정 정보 ###
file:
  multipart:
    maxUploadSize: 1782579 #1.7MB
    maxUploadSizePerFile: 1363148 #1.3MB, 파일당 최대 사이즈

```




