# Todo API

## 프로젝트 개요
Spring Boot 기반 할 일 관리 REST API. Todo CRUD 기능을 제공한다.

## 기술 스택
- Java 21 + Spring Boot 4.0.6
- Spring Data JPA + H2 Database (개발용)
- Gradle (Groovy)

## 빌드/실행 명령어
- 실행: `./gradlew bootRun`
- 빌드: `./gradlew build`
- 테스트: `./gradlew test`

## 프로젝트 구조
src/main/java/com/example/todo_api/
- controller/ : REST API 엔드포인트
- service/    : 비즈니스 로직
- repository/  : DB 접근 (JPA)
- entity/      : DB 테이블 매핑 클래스
- dto/         : 요청/응답 데이터 객체

## 코드 규칙
- 계층 분리 필수: Controller → Service → Repository
- Controller에 비즈니스 로직을 넣지 않는다
- Entity를 직접 응답하지 않고 DTO로 변환한다
- 모든 API는 /api 프리픽스를 사용한다 (예: /api/todos)
- 에러 응답은 GlobalExceptionHandler에서 통일 처리한다
- 주석은 한국어로 작성한다

## API 설계
- POST   /api/todos      : 할 일 생성
- GET    /api/todos      : 전체 조회
- GET    /api/todos/{id} : 단건 조회
- PUT    /api/todos/{id} : 수정
- DELETE /api/todos/{id} : 삭제

## 실수 방지 규칙
(여기에 Claude가 같은 실수를 반복하면 규칙을 추가한다)
- H2 콘솔 활성화: application.properties에 spring.h2.console.enabled=true
- ID는 Long 타입, @GeneratedValue(strategy = GenerationType.IDENTITY) 사용
- completed 필드 기본값은 false
- 존재하지 않는 Todo 조회 시 404 응답 반환

## 작업 방식 (토큰 절약)
- 답변은 간결하게. 불필요한 설명/서론 생략
- 코드 수정 시 변경된 부분만 보여주고 전체 파일 재출력 금지
- 한 번에 하나의 작업만 처리
- 실수를 지적받으면 이 CLAUDE.md의 "실수 방지 규칙"에 추가한다