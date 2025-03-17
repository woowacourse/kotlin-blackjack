# kotlin-blackjack

블랙잭 게임을 변형한 프로그램을 구현한다. 블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.

카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
게임을 완료한 후 각 플레이어별로 승패를 출력한다.

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

## InputView
- [x] 게임에 참여할 사람의 이름을 입력
- [x] 플레이어에게 한장의 카드를 더 받는 여부 입력

## OutputView
- [x] 게임 참여자에게 카드를 분배함을 출력
- [x] 플레이어 카드 중간 현황 출력
- [x] 딜러가 카드를 더 받았는지 출력
- [x] 최종 게임 참여자들의 카드 현황 및 카드 숫자의 합 출력
- [x] 최종 게임 승패 출력

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

## TODO

- [x] CardNumber | 출력문 저장 위치 변경
- [x] ActionType | InputView에서 Yes No 판단하도록 수정
- [x] Hand | 방어적 복사를 사용하도록 변경
- [x] Hand | haveAce 검사를 메소드로 변경
- [x] PlayingCard | 카드를 여러장 받아오는 메소드 추가
- [x] BlackJack | hitAction의 bustcheck이동
- [x] Participants | checkBust메소드 이동
- [x] GameResult | bust판단 메소드 삭제
- [x] Participants | status 변수 삭제
- [x] BlackJack | 딜러 임계값 상수 이동
- [x] DealerTest | 테스트명 변경
- [x] Participants | checkBust메소드 Hand의 isBust로 병합

## TODO

- [x] Hand | 손에 카드에 따라 Bust와 BlackJack을 판단하는 메소드 추가
- [x] Blackjack | 처음 카드를 받았을 때 BlackJack인지 판단
- [x] Player | BlackJack인지 여부에 따라 승패 판단 기준 추가
- [x] InputView | 플레이어들의 베팅 금액 입력
- [x] PlayerBetAmount | 플레이어와 베팅 금액을 매핑하는 클래스 추가
- [x] BlackjackController | 플레이어의 승패와 블랙잭여부에 따라 베팅 결과 출력
- [x] CardFactory | 테스트에만 사용한 코드 제거
- [x] BlackjackController | PlayerGroup 추가적으로 활용
- [x] InputView | 입력에 따라 도메인이 출력되도록 수정
- [x] Hand | 객체에 메시지를 던지도록 수정
- [x] Player | 계산하는 로직 추가
- [x] BlackJack | PlayerGroup을 인수로 받도록 수정
- [x] BlackJack | 중복 메서드 수정
- [x] BlackJack | drawUntilThreshold 메서드명 수정
- [x] BlackJack | BUST_STANDARD 상수 이동
- [x] Dealer | 카드 공개 메서드 추상화
- [x] DealerTest | Dealer의 테스트 추가 작성
- [x] HandTest | Hand의 테스트 추가 작성

## TODO

- [x] Hand | status 삭제
- [x] BlackjackController | 컨트롤러의 인스턴스 변수 삭제
- [x] Player | compareScores | 매개변수를 하나로 받도록 수정
- [x] PlayerBetResult | 플레이어 베팅 금액 정산 결과를 받는 클래스 생성
- [x] Hand | 빽킹 프로퍼티와 프로퍼티를 구별하여 메소드에 적용
- [x] Hand | sum을 val로 선언
- [x] Hand | if문을 return if 형태로 작성
- [x] Hand | BUST_STANDARD를 private 으로 변경
- [x] Participant | showInitCards메소드 네이밍 변경
- [x] Player | 점수 비교 로직 Participants로 이동
- [x] BlackjackController | 딜러의 수익 판단 메소드 생성
- [ ] 여러 객체들의 Test코드 작성
- [ ] HandTest | methodSource를 활용한 getSumNumber에 대한 테스트 추가 생성
- [ ] BlackjackController | 버스트되어서 플레이어에게 카드를 뽑을 수 없다고 메시지 출력