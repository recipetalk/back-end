## 결과물
[앱 빌드 파일](https://khj745700.notion.site/430d0fdc6a08437fb28a8db813ca6881)

# 기술 스택

> 프론트엔드 : React Native CLI, FCM, redux(toolkit), encrypted-storage 
>
>백엔드 : Java17 + Spring Framework(With Spring Boot 3.0.1), Jakarta EE, ThymeLeaf, JPA, Hibernate, JPQL, QueryDSL 5.0, FCM
>
>데이터베이스 : MySQL
>
>클라우드 : AWS (s3, EC2, router 53, CodeDeploy, RDS)
>
>CI/CD : Git Action, Jenkins
>
>형상관리 : Github, Git
> 

# API 명세

[링크](https://www.notion.so/khj745700/04561b1c96e7455d8037818deb67bc48?v=8c466a55b30a49b2ac5aa619234b5f7a)



<br>
<br>
<br>
<br>

# 아키텍처

<img width="1255" alt="스크린샷 2023-08-17 오후 8 20 54" src="https://github.com/recipetalk/back-end/assets/68643347/7a16f501-9376-47b7-8394-d9fded049b0c">


<br>
<br>
<br>



## ERD

![ERD](https://github.com/recipetalk/back-end/assets/68643347/80b4b8d7-51b1-4cc2-bb4f-72e6a6ff261a)

- 최대한 JPA 프록시 객체를 피치 못할 곳에 사용할 시 영속성 에러가 발생하는 경우가 많이 발생하였습니다. 그렇기에 프록시 객체를 최대한 피하는 과정에서 쿼리로 일괄 조회해서 가져오는 경우가 많았습니다. 그런 경우에 userDetail의 경우 쿼리가 많이 사용되는 경우가 많아 username 같은 경우 사용빈도도 높았기 때문에 한 테이블에 추가로 넣어줬습니다.
- board는 JPA의  Inheritence.JOIN 방식을 유사하게 만들어 사용하였습니다. 그 이유는  위와 유사합니다. recipe만 단독으로 조회되는 경우에도 필요없는 쿼리가 발생하였기에 분리하여 사용할 수 있도록 잡았습니다.
- 커뮤니티 서비스 특성상, 법적 대응을 제외할 수 없었습니다. 그렇기에 isDeleted가 게시판이나 회원정보, 덧글에 추가하여 SoftDelete가 가능하도록 처리하였습니다.
- 모든 데이터는 JpaAuditing 기능을 접목하여 상속 받아와 자동으로 처리되도록 구현됬습니다.

<br>
<br>
<br>

# 디자인

<img width="450" alt="디자인1" src="https://github.com/recipetalk/back-end/assets/68643347/d01b5a76-1800-4341-91e0-f9f999ab2b3a">
<img width="450" alt="디자인2" src="https://github.com/recipetalk/back-end/assets/68643347/8b10a008-db4b-4f9c-ab88-e39814b1f4b0">

<br>
<br>
<br>

# 서비스 소개


이 프로젝트는 만개의 레시피를 모티브로 한 레시피 제공 서비스에 식재료 관리 기능을 추가한 애플리케이션입니다. 코드 작성의 깔끔함과 유지보수의 중요성, 그리고 안전성을 고려하여 많은 노력을 기울였습니다.



<br>
<br>
<br>


# 문제 개선

1. JPA의 복합키와 MySQL 복합키의 문제점 분석 및 해결
2. Spring Security 설계 및 구현
3. JWT 토큰 사용으로 자동 로그인 구현
4. Axios intercepter를 활용한 JWT토큰 만기 시 토큰 업데이트 후 요청하도록 구현
5. 개발서버 및 **운영서버**로 배포 자동화 구현
6. 현재 무중단 배포 작업중입니다.
7. FCM을 활용한 알림 서비스 및 예약 알림 서비스 구현


<br>
<br>
<br>


## 1. JPA의 복합키와 MySQL 복합키의 문제점

- 저희 시스템에서 관계 테이블에서 두 개의 테이블의 PK를 FK로 받아와  PK를 복합키로 설정해 두면 어느 테이블의 PK를 이용해 인덱스를 타면 속도가 더 빠르지 않을까 생각했었습니다.
- 하지만 이는 잘못된 생각임을 깨닫게 됩니다. 사항은 아래와 같이 정리합니다.

1. 복합키는 클러스터드 인덱스, B-Tree에 저장됩니다.
2. 복합키의 PK들 또한 foreign key 이므로 mysql은 FK도 Index를 알아서 넣어줍니다.
    
    아래는 적용되어있는 예시입니다.
    

<img width="827" alt="JPA1" src="https://github.com/recipetalk/back-end/assets/68643347/4cf7a9b0-4e99-4f55-ae96-ad05cd7bc2c3">

3. JPA에서 복합키로 가져올 때는 좌 우 모두 Entity가 영속이 되있는 상태에서 조회가 되야됩니다.
(모듈 분리 전 얘기입니다.)

4. 아래사진은 복합키로 구성되어 있는 인덱스의 구조입니다.
    
<img width="831" alt="JPA2" src="https://github.com/recipetalk/back-end/assets/68643347/f0ffa284-bb14-400a-8379-f08a4bb2ca4c">

- Primary Key의 인덱스 생성 순서를 보면, JPA에서 Column_name의 알파벳 순서로 생성이 된 것을 확인할 수 있습니다. 게다가 같은 Key_name을 보유한 것을 알 수 있습니다. 이 경우, 오라클의 경우는 상관없지만 Mysql의 경우 복합인덱스로 생성이 됩니다. 이는 인덱스의 순서가 중요하기 때문에 첫번째 인덱스가 Where 조건에서 NULL 이거나 배제되고 조회된다면 Full Scan이 발생할 수 있습니다.
    
5. 아래 사진은 PK는 JPA에서 GeneratedValue = GenerationType.Identity 로 설정되어 있고,  user와 blocked_user_id 모두 FK로 따로 주게 된 경우입니다.
    
<img width="827" alt="JPA5" src="https://github.com/recipetalk/back-end/assets/68643347/60c9f2de-0416-4f1a-b063-7e3f7925cd71">
    
- 위의 경우 각각마다 FK가 다르게 Index를 타고있음을 확인할 수 있습니다.

- 따라서, 빠른 탐색 조회를 위해 복합키보단 별도의 FK로 할당해 줌으로써 인덱스를 태우는게 더 낫다는 결론이 나게 되었습니다.
- Reference :

[MySQL :: MySQL 8.0 Reference Manual :: 8.3.6 Multiple-Column Indexes](https://dev.mysql.com/doc/refman/8.0/en/multiple-column-indexes.html)

<br>
<br>

## 2. JWT 토큰 사용으로 자동 로그인 구현

- 저희 시스템은 기존 JWT와는 다르게 Refresh 토큰이 존재하지 않습니다.
- 그 이유를 설명드리겠습니다.

첫번째로, JWT 인증 인가 부분입니다.

<img width="1082" alt="JWT1" src="https://github.com/recipetalk/back-end/assets/68643347/dc7fa037-4610-4f02-9672-7d184b2b8546">

- 저희 시스템은 인증 시, 추가로 UserDetailsService에서 회원 정보가 존재하는지 확인하는 Business Validation이 존재합니다. 그래서 로그인 방식과 되게 유사하게 인증받게 됩니다.
- 추가로, JWT Access Token이 탈취된다 하더라도 Alive Time은 하루만 주어졌기 때문에 하루 지나면 검증이 안되도록 하였습니다.
- Refresh Token을 사용한다고 가정하고 Refresh Token은 2주~한달 에서 넉넉잡아 사용한다고 가정하였을 때, Refresh Token 또한 탈취하게 되면 Access Token을 한달동안 사용가능하다는 소리와 일맥상통 하게 됩니다.
- 프론트엔드 (앱)에서는 로그인 정보를 Encrypted-storage로 관리합니다. Android 에서는 AES-256의 암호 레벨을, IOS에서는 KeyChain으로 보관하는 방식을 취하고 있기 때문에 걱정 않고 쓸 수 있다는 장점을 가지고 있었습니다.
- 저희 시스템은 Redis를 사용하지 않습니다. 그렇기 때문에 Refresh Token을 DB에 저장하고 관리하기에 리스크가 있다고 판단했습니다.
- 그래서 다시 로그인 해서 가져오는 방식을 취하는 것이 맞겠다는 생각이 들어 재 로그인 방식으로 Access Token을 재발급 받도록 처리하였습니다.


<br>
<br>

## 3. Axios intercepter를 활용한 JWT 만기 시 토큰 업데이트 후 요청하도록 구현

- JWT 토큰에서 보통적으로 토큰 만기시간 혹은 일을 정하고 사용하도록 합니다. 그 이유는 csrf(xsrf)와 같은 문제가 크기 때문인데요, 그래서 저희는 토큰을 다시 받아오는 로직이 필요했습니다. 적용시키려고 보니, 레시피톡에서는 각각마다 토큰을 다시 받아오는 로직을 넣기에는 적용해야 할 코드가 너무 방대했습니다. (API만 대략 80개)


![Axios1](https://github.com/recipetalk/back-end/assets/68643347/aba91f16-3727-4ff6-a3d7-d28d74644a4a)


- axios의 interceptor라는 기능을 활용해, request 혹은 response의 응답오기 전, 후, 모두 적용가능 한 범위 내에서 저희는 응답을 받아온 후에 대한 기능에 적용을 시켜 정상 동작하도록 구현했습니다.
(레시피톡에서는 토큰 만기시 Security Filter에서 401에러를 던지기 때문에 적용 가능했습니다.)

<br>
<br>


## 4. 개발서버 및 운영서버로 배포 자동화 구현

### 개발서버 배포 자동화 With Jenkins

- 개발서버에서는 Jenkins를 활용한 배포 자동화를 실시했습니다.

<br>
<br>

### 왜 개발서버는 Jenkins를 사용했냐?

- 이유는 먼저 서버의 24간 동작여부에 판단했습니다.
- 배포서버와 다르게 개발서버는 개발자들이 배포 전 테스트 용도로 사용하는 용도로 사용되었습니다.
- 그렇기에 개발서버는 사용안하면 끄는 용도가 적합했습니다.
- 그리고, Jenkins는 서버의 성능에 영향을 많이 받기 때문에, GitAction과는 다르게 속도가 매우 빠릅니다.

- 대략적인 세팅방법은 쉬우니 제가 설정해둔 방식과 같은 예제로 설정하는 법을 링크해 두겠습니다.
    
    ⇒ [Jenkins Setting](https://khj745700.notion.site/Jenkins-Setting-cc5c251c500846a1ad2ee4872784d11e) 
    
<br>
<br>

### 배포서버는 GitAction을 사용한 이유가 있나?

- 먼저 시크릿 파일을 관리하기 용이하다는 점입니다.
- Jenkins와 같이 Github에서 코드를 그대로 Clone으로 따서 가져오게 되면 시크릿 파일이 없이 설정됩니다. 그러면 시크릿 파일이 업데이트 되면 개발 서버에서도 원격 쉘로 접속해서 따로 관리를 해줘야하는 불편함이 있었습니다.
- 이와 다르게 Git Action은 권한이 있는 사람은 시크릿파일에 쉽게 접근이 가능해서,  업로드가 용이했습니다.
- 속도는 살짝 느린 감이 있었지만, 실제로 배포하는 데 걸리는 시간은 약 4분 정도로 작은 규모에서의 서버에서는 큰 문제가 생기지 않을 것이라 판단했습니다.
현재는 24시간 무중단 배포를 구현중이라 곧 해결될 사항입니다.



<br>
<br>
<br>



## 5. FCM을 활용한 알림 서비스 및 예약 알림 서비스 구현… EventListener과 Spring Scheduler을 곁들인.

![FCM1](https://github.com/recipetalk/back-end/assets/68643347/b9f2c57b-b460-4875-88b8-ff87ae8c770a)

- 저희는 위와 같이 EventListener을 활용한 푸시 알림을 제공합니다.
- FCM 알림 전송은 아래 크게 세가지 조건입니다.
    - 댓글 및 대댓글 작성
    - 팔로잉 추가
    - 식재료 마감 임박 알림 (Spring 스케줄링으로 대체)

- 대표적으로 댓글 및 대댓글 로직 부터 확인하겠습니다.

![FCM2](https://github.com/recipetalk/back-end/assets/68643347/0ab8a632-a59a-4676-8a03-4ffdc58cc2a3)

- addComment의 추상화 수준을 높혀, 알림을 전송하는 로직과 덧글을 등록하는 로직과 분리를 했습니다.
<br>

- 그리고 시스템 상 사용자 소비 기한이 3일 부터 예약알림으로 소비기한 임박 알림을 전송해줘야 했습니다.
- Spring Batch를 사용하기엔 라이브러리가 너무 무거울 것 같아, Spring Scheduler을 사용해 예약 알림을 사용하는 방식으로 적용해 주었습니다.

<img width="1260" alt="FCM3" src="https://github.com/recipetalk/back-end/assets/68643347/32cd72da-a03d-4559-a854-da8f48b0bfd2">

<br>
<br>
<br>

# 보완해야 할 부분





## 학업 목적으로서, 개인적으로 진행해보고 싶은 부분들(토이프로젝트)

- 현재 단일적으로 EC2 t2.micro 에 올라가있는 서비스입니다. 웹 서비스 특성 상 갑자기 트래픽이 몰릴 가능성 때문에 차 후 서버 확장을 위한 scale out 설계 부분이 미흡하다고 느끼고 있습니다.
그래서, API별로 부하테스트를 진행해서 TPS를 측정, I/O 대기율 측정 및 CPU 사용율을 측정하여 모듈을 분리하는 방안을 생각 중에 있습니다.
    
    그리고 유난히 성능이 떨어지는 트랜잭션은 쿼리 실행 계획을 실행시켜 판단해보고 쿼리 최적화를 진행하고자 합니다.
    
- 위 사항을 적용시킬 경우 현재 단일 배포로 설정이 되어있어서 멀티 모듈이나 [Docker](https://khj745700.notion.site/aa4e8df519bd45a599bde7ac9512c3ba?pvs=21)로 배포한 다음, 무중단 배포를 구축할 예정입니다.
- GC 튜닝도 진행해서 최적의 설정를 찾아내는 방안도 물색하고자 합니다.
- 현재 JWT 토큰으로 인증하는 방식으로 구현되어 있으나, 학업용으로 Session으로 인증하는 방식으로 마이그레이션 할 예정입니다.
    - 현재 생각했을 때 이점으로는, Session이 구현되어있을 때 현재 시스템으로는 조회자가 차단한 유저를 Join해서 한방쿼리로 가져오게 됩니다. 이럴 때 Join 문에서 회원ID별로 조회되기 때문에, 분기가 많아서 쿼리 캐시가 적용되기 힘든 문제가 있습니다. 이 경우 Session에 사용자 차단 정보를 캐싱하게 되면 application 상에서의 Join 으로 성능 향상의 이점이 있을 것으로 사료됩니다.
        - 쿼리 캐시는 Mysql 5.7 부터 deprecate가 되었습니다. 그래서 8.0에서는 사용할 수 없는 문제가 있어, 5.6버전으로 마이그레이션, proxySQL을 적용, SQL Proxy 서버를 만드는 방식 중 하나를 채택해야 할 듯 합니다.
- 위 사항을 AWS에 모두 올리기엔 비용적 부담이 되므로, 학업 목적으로 온프레미스 환경에 진행할 예정입니다.
- 현재 Java Cache 부분도 공부 중에 있어서, 이 부분도 적용시킬 부분이 있으면 한번 적용해보는 방법으로 구현해볼 예정입니다.
- 현재 테스트 코드가 작성되어 있지 않습니다. 이를 위해 테스트 코드 작업도 추가적으로 병행해, 테스트 코드 작성법을 익힐 계획입니다.


<br>
<br>
<br>

# 느낀 점

- 풀스택 개발을 진행함으로써 프론트의 요구사항에 맞게 설계하는 법과 구현하는 법을 익히게 된 계기가 되었습니다.
