# kotlin-blackjack

## 기능명세서(Specification)

- [x] Participant
    - [x] 게임에 참여하는 사람의 추상화된 행동 명세를 가지고 있다.
    - [x] 승/패/무 를 판단하는 추상 행동 명세
    - [x] 히트를 진행하여 상태 업데이트

- [x] ParticipantName
  - [x] 이름의 길이는 10글자 이내로 한다.

- [x] ParticipantState
    - [x] Run/Ready/Playing/BlackJack/Bust 상태를 갖는다
    - [x] 핸드를 갖고, 이익률을 계산한다

- [x] Hand
    - [x] 카드 리스트를 갖는다
    - [x] 카드를 추가할 수 있다
    - [x] 블랙잭을 판단한다
    - [x] 점수를 계산한다
    - [x] 에이스를 포함한 최적의 점수를 계산한다

- [x] Dealer
    - [x] 핸드가 16 이하일 경우 카드를 계속 드로우 한다.
    - [x] 핸드가 17 이상인 경우 드로우를 멈춘다.
    - [x] 상대와의 승패 결과를 판정할 수 있다

- [x] Player
    - [x] 핸드가 21 미만인 경우, 카드를 더 받을 수 있다.
    - [x] 핸드가 21 이상인 경우, 카드를 더 받을 수 없다.
    - [x] 상대와의 승패 결과를 판정할 수 있다
    - [x] 결과에 따른 이익을 구할 수 있다

- [x] Players
    - [x] Player의 List를 가진다.
    - [x] 1~10명의 Player가 존재하는지 검증한다.

- [x] Money
    - [x] 배팅 금액을 담는다
    - [x] 문자를 입력하거나 0 이하의 수를 입력하면 예외가 발생한다

- [x] Wallet
    - [x] 사용자의 이름과 배팅 금액을 갖고 있다

- [x] Game
    - [x] Players와 Dealer를 받아 게임을 진행시킨다

- [x] Card
    - [x] 인자의 값을 통해 본인의 표식(Mark)를 결정한다.
    - [x] 인자의 값을 통해 본인의 value (1~10, J, Q, K)를 결정한다.

- [x] MarkType (enum class)
    - 하트, 다이아, 클로버, 스페이드의 값을 가진다.

- [x] ValueType (enum class)
    - A(Ace), 2 부터 10 사이의 숫자, J, Q, K 의 값을 가진다.

- [x] Deck
    - [x] 카드 덱으로, create 함수를 통해 특정 전략의 덱을 생성할 수 있다
    - [x] pop 시 맨 윗장의 카드를 반환한다.

- [x] DeckGenerationStrategy

- [x] DeckRandomGenerationStrategy
    - [x] 52 장의 트럼프 카드 덱을 섞어서 만드는 전략
- [x] DeckExplicitGenerationStrategy
    - [x] 원하는 카드 덱을 생성하여 테스트에 쓰이는 전략

- [x] Hand
    - [x] 카드의 리스트를 갖는다.
    - [x] 카드를 추가할 수 있다.
    - [x] 핸드의 값을 계산할 수 있다.

- [x] Point
  - 카드의 총 합 점수를 담는 Int 래퍼 클래스

- [x] Profit
  - 이익률을 계산하는 Double 래퍼 클래스

- [x] ResultType (enum class)
    - 승/패/무 의 값을 가진다.

---
- 📝 InputView
    - [x] 게임에 참여하는 사람의 이름 리스트를 입력 받을 수 있다.
    - [x] Player 들의 응답을 받을 수 있다.
    - [x] Player 들의 배팅 금액을 받을 수 있다.

- [x] OutputView
    - [x] Player의 이름을 출력한다.
        - (`ex. 딜러와 pobi, jason에게 2장의 나누었습니다.`)
    - [x] Dealer 와 Player의 핸드를 출력한다.
    - [x] Dealer 와 Player의 최종 핸드를 출력한다. (결과(최종합) 포함)
    - [x] 최종 승패 결과를 출력한다.
    - [x] 최종 이익률 결과를 출력한다