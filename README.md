# 블랙잭 Step1

## 기능 요구사항
- [x] 카드는 1부터 13, 문양 4개의 값으로 구별되어야 한다
- [x] 0부터 51 까지의 카드 인덱스로 카드를 만들 수 있다
- [x] Deck에서 카드를 줄 수 있다
- [x] Player는 무작위 카드를 2장 받는다
- [x] Player가 보유한 카드를 보여줄 수 있다
- [x] 카드 번호에 해당하는 카드의 숫자 혹은 문자를 받아올 수 있다 
- [x] 카드 문양 인덱스에 해당하는 카드의 문양 이름을 받아올 수 있다
- [x] Rule에게 손패의 결과를 물어볼 수 있다
- [x] Rule에게 플레이어의 드로우 여부를 받아올 수 있다
- [x] Rule에게 딜러의 드로우 여부를 받아올 수 있다
- [x] Player는 Deck에서 카드를 뽑을 수 있다
- [x] Card는 자신의 카드 정보를 문자열로 줄 수 있다
- [x] 손패 두 개를 비교해서 승 패를 받아올 수 있다
- [x] 딜러의 전체 승 무 패 결과를 텍스트로 받아올 수 있다

## Step1 리팩터링 요구사항
- [x] 도매인 패키지 역할별 구조화
- [x] 상태를 저장하는 enum 클래스를 사용하도록 수정
  - [x] 카드 문양(suit) 상수화
  - [x] 카드 번호(cardNumber) 상수화
  - [x] 승, 무, 패 상태 추가
  - [x] 블랙잭, 버스트, 노말 상태 추가
  - [x] 기존 코드에 enum 클래스를 사용하도록 적용
- [x] Rule의 과도한 책임 분리
- [x] WinLossStatistics 로직 enum 클래스 상태를 사용해서 재구현
  - [x] 딜러가 블랙잭이고 플레이어가 블랙잭이 아닌경우 딜러 승리 판정 추가
- [x] HandCards 일급 컬렉션 추가하여 활용
- [x] 카드의 한국어 이름을 만드는 책임을 OutputView 이동
- [x] 게임 참여자의 카드를 초기화하는 책임을 컨트롤러로 이동
- [x] 오타 및 잘못된 명명 수정
- [x] 누락된 접근 제어자 수정
- [x] 누락된 유효성 검사 추가
- [x] 줄바꿈 출력 형식 맞추기
- [x] 모든 todo 완료 혹은 삭제
- [x] companion object만 존재하는 class를 object로 변경
- [x] DSL 실습 코드 분리

## Step1 리팩터링 요구사항 2
- [x] Number의 orderNumber 프로퍼티 삭제 후 getByOrderNumber 메서드 변경
- [x] HandCards클래스 내 카드 조회시 backing property 활용
  - [x] 손패 내부의 카드를 한번에 초기화 하도록 테스트 코드 변경
- [x] DSLStudy코드를 Test로 이동
- [x] Deck Object를 일반 Class로 변경
  - [x] PlayerTest의 불필요해진 덱 관련 테스트 삭제
- [x] DeckTest의 테스트명 수정 및 추가
- [x] outputView.lineSeparator()호출을 컨트롤러에서 삭제
- [x] 초기 카드 장수 상수를 Casino에서 handcard로 이동

## Step2 기능 요구사항
- [x] BetHistory에 플레이어별 베팅 기록 추가
- [x] 베팅 금액 입력 로직 추가
- [x] WinLossStatistics가 playerResult를 보관 후 반환하도록 수정
- [x] ProfitStatistics를 추가하여 참가자별 수익 계산 로직 추가
  - [x] BetHistory를 보고 플레이어별 수익 계산 및 기록
  - [x] (플레이어의 수익의 합)의 역으로 딜러의 수익 역산 로직 추가
- [x] ProfitStatistics를 사용한 최종 수익 콘솔 출력
  - [x] step1의 최종 결과를 콘솔 출력하지 않도록 수정

## Step2 리팩터링 요구사항
- [x] GameParticipant의 isInitHandCard() 함수를 사용하도록 수정
- [x] 외부에서 게임 참가자의 HandCard 직접 접근하지 않도록 수정
- [x] HandCards 클래스에 방어적 복사 적용
- [x] intial로 Number 객체를 가져올 수 있는 로직 추가
- [x] 최소 베팅 금액 상수화
- [ ] 수익률 계산시 Float 대신 Double 사용
- [ ] 게임 참가자가 직접 수익이 얼마인지 알려줄 수 있도록 수정
- [ ] ProfitRate와 Profit 클래스를 추가하여 수익률 계산 로직 수정
- [ ] 게임 참여자가 직접 승 무 패를 알려주도록 수정
- [ ] 초기 카드를 가져오는 추상 메서드 추가
- [ ] participant 패키지의 테스트 코드 추가
- [ ] BetHistory 클래스에 방어적 복사 적용