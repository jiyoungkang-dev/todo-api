# Todo API

Spring Boot 기반 할 일 관리 REST API입니다.
Todo CRUD와 함께 완료 여부 필터링 · 페이징 · 정렬을 지원하며, Swagger(OpenAPI)로 API 문서를 제공합니다.

<img width="3812" height="1933" alt="image" src="https://github.com/user-attachments/assets/de123e9d-45d9-4e6e-b298-8f75376604c3" />


> 백엔드 핵심 패턴(계층 분리, DTO 변환, 전역 예외 처리, 환경변수 분리, API 문서화)을 실습·정리한 포트폴리오용 프로젝트입니다.

<br>

## 🛠 기술 스택

| 구분 | 기술 |
|------|------|
| Language | Java 21 |
| Framework | Spring Boot 4.0.6 |
| ORM | Spring Data JPA (Hibernate) |
| Database | MySQL 8 |
| API 문서 | Swagger / OpenAPI (springdoc 3.0.3) |
| Validation | Spring Boot Starter Validation |
| Build | Gradle (Groovy) |

<br>

## ✨ 주요 기능

- **할 일 CRUD** — 생성 / 전체 조회 / 단건 조회 / 수정 / 삭제
- **검색 & 페이징** — `completed` 필터, `page` · `size` 페이징, `sort` 정렬 지원
- **계층 분리 설계** — Controller → Service → Repository 구조로 책임 분리
- **DTO 변환** — Entity를 직접 노출하지 않고 Request/Response DTO로 응답
- **전역 예외 처리** — `GlobalExceptionHandler`로 에러 응답 통일 (없는 ID 조회 시 404)
- **API 문서 자동화** — Swagger UI로 엔드포인트 확인 및 테스트

<br>

## 📁 프로젝트 구조

```
src/main/java/com/example/todo_api/
├── controller/   # REST API 엔드포인트
├── service/      # 비즈니스 로직
├── repository/   # DB 접근 (JPA)
├── entity/       # DB 테이블 매핑 클래스
├── dto/          # 요청/응답 데이터 객체
└── exception/    # 전역 예외 처리
```

<br>

## 🚀 실행 방법

### 1. 사전 준비
- Java 21
- MySQL 8 (로컬에 `tododb` 데이터베이스 생성)

```sql
CREATE DATABASE tododb;
```

### 2. 환경변수 설정

DB 비밀번호는 코드에 직접 넣지 않고 환경변수 `DB_PASSWORD`로 주입합니다.

**Windows (PowerShell)**
```powershell
$env:DB_PASSWORD="your_password"
```

**macOS / Linux**
```bash
export DB_PASSWORD=your_password
```

### 3. 실행

```bash
./gradlew bootRun
```

기본 접속 주소: `http://localhost:8080`

<br>

## 📖 API 명세

애플리케이션 실행 후 아래 주소에서 Swagger UI로 전체 API를 확인하고 테스트할 수 있습니다.

```
http://localhost:8080/swagger-ui/index.html
```

OpenAPI 스펙(JSON): `http://localhost:8080/v3/api-docs`

### 엔드포인트 요약

| Method | URL | 설명 |
|--------|-----|------|
| `POST` | `/api/todos` | 할 일 생성 |
| `GET` | `/api/todos` | 목록 조회 (필터 · 페이징) |
| `GET` | `/api/todos/{id}` | 단건 조회 |
| `PUT` | `/api/todos/{id}` | 수정 |
| `DELETE` | `/api/todos/{id}` | 삭제 |

### 목록 조회 쿼리 파라미터

| 파라미터 | 설명 | 예시 |
|----------|------|------|
| `completed` | 완료 여부 필터 (선택) | `true` / `false` |
| `page` | 페이지 번호 (0부터 시작) | `0` |
| `size` | 페이지 크기 | `10` |
| `sort` | 정렬 기준 | `id,desc` |

```
GET /api/todos?completed=true&page=0&size=10&sort=id,desc
```
