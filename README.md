# kotlin-blackjack

블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

플레이어는 게임을 시작할 때 배팅 금액을 정해야 한다.
카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다. 단, 카드를 추가로 뽑아 21을 초과할 경우 배팅 금액을 모두 잃게 된다.
처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1.5 배를 딜러에게 받는다. 딜러와 플레이어가 모두 동시에 블랙잭인 경우 플레이어는 베팅한 금액을 돌려받는다.
딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다. 딜러가 21을 초과하면 그 시점까지 남아 있던 플레이어들은 가지고 있는 패에 상관 없이 승리해 베팅 금액을 받는다.

## 베팅금 객체
- [x] 승패무&블랙잭 컨디션에 따라 수익 금액을 계산한다
- [x] 베팅금은 0원 이상이어야 한다s

## 카드 객체
- [x] 심볼과 카드 숫자를 매핑하여 카드를 생성한다.
- [x] 카드를 셔플해서 카드를 나누어준다.

## 플레이어 객체
- [x] 카드 숫자의 합을 계산한다.
- [x] 받은 카드의 목록을 반환한다.
- [x] 카드 숫자의 합을 토대로 bust를 판단한다.
- [x] 플레이어의 숫자의 합과 받은 숫자의 합을 비교하여 승패를 결정한다.

## 딜러 객체
- [x] 카드 숫자의 합을 계산한다.
- [x] 받은 카드의 목록을 반환한다.
- [x] 카드 숫자 합이 16보다 작으면 카드를 계속 받는다.

## 블랙잭 게임 객체
- [x] 게임 시작시 카드를 2장을 나눈다.
- [x] 게임이 끝난 후 승패를 가린다.
- [x] 승패를 가린 후 각 플레이어의 수익 금액을 계산한다

## InputView
- [x] 게임에 참여할 사람의 이름을 입력
- [x] 플레이어에게 한장의 카드를 더 받는 여부 입력
- [x] 참여자의 베팅금을 입력

## OutputView
- [x] 게임 참여자에게 카드를 분배함을 출력
- [x] 플레이어 카드 중간 현황 출력
- [x] 딜러가 카드를 더 받았는지 출력
- [x] 최종 게임 참여자들의 카드 현황 및 카드 숫자의 합 출력
- [x] 최종 게임 승패 출력
- [x] 최종 수익 출력

## TODO
- [x] Shape | 출력문 저장 위치 변경
- [x] CardNumber | OTHER_ACE 네이밍 변경
- [x] Deck | deck 네이밍 변경
- [x] Dealer | overThreshold 메소드 네이밍 고민
- [x] Participants | 카드 뭉치를 갖는 일급 컬렉션 구현
- [x] Participants | 카드 합 구하는 로직 변경
- [x] YesOrNo | Enum으로 합체
- [x] Dealer | 더 뽑을 수 있는지 스스로 판단 (참여자 객체에서 판단하게 함)
- [x] Deck | 카드를 명시적으로 넣어 주기 (이러면 Deck이 셔플 방법을 몰라도 됨)
- [x] Blackjack | shouldStopDrawing 메소드가 한 가지 일만 하도록 변경
- [x] Participants | 가시성 변경자 수정
- [x] 8명 인원 제한
- [x] 참여자 이름 중복 검사
- [x] 테스트 코드 | given when then 반영
- [x] BlackjackTest | BeforeEach 적용
- [x] ParticipantsStatus | Bust 삭제

- [x] Blackjack | 16 & 21 기준 딜러 & 손에게 나눠 주기
- [x] Hand | Bust 상태 판단 역할 부여 (GameResult에서 Hand로 이동)
- [x] Hand | 방어적 복사 개념 적용
- [x] OutputView | 초반 카드 보여 주는 로직을 비즈니스 로직으로 처리하기
- [x] Introduce | 이름을 필수 요소로 적용
- [x] Blackjack | playerGroup 생성자 프로퍼티로 변경
- [x] ActionType | 입력 유효성 검사 로직을 View로 이동 & 리턴값 변경
- [x] Participants | 결과 저장 위치 변경 (Participants -> controller)
- [x] GameResult | None 상태 삭제
- [x] GameResult | compare 삭제 및 Player로 승패 판단 로직 이동

- [x] 패키지 구조 변경
- [x] ActionType | Boolean -> String 타입 변경
- [x] Player | 결과 판단 로직 위치 고민 (Player vs Participant)
- [x] Player & Dealer | getInitCard 메소드 로직 변경
- [x] test | 메소드명 직관적으로 변경
- [ ] Introduce | 이름 필수 요소 고민
- [x] BlackjackController | PlayerGroup 파라미터 생략

- [x] 테스트 코드 추가
- [x] Player | receive 로직 단순화 (Hand를 생성자로 해 줄지 고민)
- [x] BlackjackController | 블랙잭 혼자서도 게임이 가능하도록 변경
- [x] ActionType | String -> ActionType 변경 로직 위치 고민
- [x] 수익률을 value class로 선언
- [x] BetAmount | 수익 금액 로직 개선
- [ ] ActionType | View에서 행동을 반환하도록 변경
- [ ] Blackjack | drawUntilThreshold 메소드 네이밍 변경
- [ ] Map 사용 지양하기 (방법 고민)
