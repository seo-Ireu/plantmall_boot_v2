## 목차
1. [주제](#1-주제)
2. [핵심요약](#2-핵심-요약)
3. [기여한점](#3-기여한-점)
4. [사용기술](#4-사용-기술)
5. [트러블슈팅](#5-트러블슈팅)
6. [개선사항](#6-개선사항)
7. [결과화면](#7-결과화면)
# 1. 주제
- 식몰(plantmall_boot)
- 식물 판매 웹사이트
# 2. 핵심 요약
  (1) 문제 배경<br>
  \- 코로나-19로 집에서 보내는 시간이 늘면서 식물 키우기 취미 열풍, 식물 관련 매출 증가<br>
  (2) 해결 방안<br>
  \- 편리하게 식물을 조회하고 판매할 수 있는 식물 판매 웹사이트 제작<br>
  (3) 결론<br>
  \- 식몰(식물+Mall) 사이트 개발 <br><br>

# 3. 기여한 점
- 동덕여자대학교 컴퓨터학과 4-1학기 과목 소프트웨어시스템개발 팀프로젝트
## 업무
이름|업무|
---|---|
[서가람](https://github.com/mohyerolo)|제품 판매, 구매, 서비스 개발 역할(장바구니, 주문, 문의, 리뷰)|
[이세은](https://github.com/seny9)|회원 홈페이지 서비스 개발(프로필, 회원 관리 기능)|
[임소현](https://github.com/seo-Ireu)|공동구매, 회원 관리 서비스 개발(공동구매(펀딩), 회원 관리 기능)|
[황세원](https://github.com/hswon37)|제품 판매, 구매, 서비스 개발 역할(판매 제품 관리, 제품 조회, 검색) 및 메인 화면, DB Schema 작성|


# 4. 사용 기술

<table>
  <tr>
    <td width="300px">
    - Java 11<br>
    - Spring Boot<br>
    - lombok<br>
    - Oracle<br>
    - Erwin<br>
    - Mybatis<br>
    - Thymeleaf<br>
    - SQL Developer
  </td>
    <td><img src="https://user-images.githubusercontent.com/49058833/176380667-e5af59e1-691d-43ff-b831-15614f47814b.png" width="450"/></td>
  </tr>
</table>

# 5. 트러블슈팅
- 개발 중 발생 문제, 해결 방법
- 제품 수정
  - 제품 수정할 때, 제품 이미지를 업로드하지 않고 수정을 할 경우, 기존에 등록된 제품 이미지가 삭제되는 문제
  -  -> 이미지를 추가 등록하지 않은 경우, 이미지 테이블을 업데이트 하지 않도록 조건을 추가하여 해결
- 제품 이미지 불러오기
  - 제품 상세, 수정, 삭제, 목록 등에서 제품 이미지를 불러올 때, 제품에 대응되는 이미지를 불러오기 어려운 문제
  -  -> DB에 저장할 이미지 파일명을 "img_제품명"으로 저장하여, controller에서 DB에서 제품을 가져오면서, 제품명(p_name)을 parameter로 view에 전달 -> ```<img th:src="@{/getByteImage?p_name=}+${product.p_name}">``` -> controller에서 ```@RequestMapping("/getByteImage") ```로 받아 DB에서 "img_제품명"으로 검색하여 다시 view에 전달
- 홈화면 이미지 슬라이딩
  - Controller에서 받은 이미지 옆으로 넘기는 것이 일정 시간마다 반복 실행 안되는 문제
  -  -> Swiper 이용

# 6. 개선사항
- 추후 수정이 필요한 부분
- 제품 등록
  - 이름이 같은 제품과 이미지를 함께 넣으면, 이미지테이블의 pk(img_제품명으로 저장되게함)중복으로 무결성제약조건위배문제
  - 입력항목이 입력되지 않을 경우, 검증처리 없음(validation 구현필요)

# 7. 결과화면
![main](https://user-images.githubusercontent.com/49058833/176376308-b240b794-d5f7-4a94-9c31-49850c9f7007.png)
![001](https://user-images.githubusercontent.com/49058833/176376386-7ab47054-1288-42bb-8c5c-76edc684ff86.png)
![002](https://user-images.githubusercontent.com/49058833/176376401-47cb07c5-b4af-4e27-8c77-f11a403c740f.png)
![003](https://user-images.githubusercontent.com/49058833/176376402-c20032b2-3385-457b-a641-d9e71f8baf66.png)
![004](https://user-images.githubusercontent.com/49058833/176376406-17aa3912-07f2-4217-abf8-4bd8438a8895.png)
![005](https://user-images.githubusercontent.com/49058833/176376409-68a06360-6e30-4672-b8de-86b6b0bb393b.png)
![006](https://user-images.githubusercontent.com/49058833/176376411-db2f164f-2cb8-4a6d-8fa5-eaeff1f30080.png)
