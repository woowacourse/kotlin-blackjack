# kotlin-blackjack

## 기능명세서(Specification)


---

- [x] Participant
    - [x] 게임에 참여하는 사람의 추상화된 행동 명세를 가지고 있다.
    - [x] 상황에 따라 에이스를 포함한 핸드의 값을 계산할 수 있다.


- [x] Dealer
    - [x] 핸드가 16 이하일 경우 카드를 계속 드로우 한다.
    - [x] 핸드가 17 이상인 경우 드로우를 멈춘다.


- [x] Player
    - [x] 핸드가 21 미만인 경우, 카드를 더 받을 수 있다.
    - [x] 핸드가 21 이상인 경우, 카드를 더 받을 수 없다.

- [x] BetMoney
    - [x] 배팅 금액을 담는다
    - [x] 문자를 입력하거나 0 이하의 수를 입력하면 예외가 발생한다

- [ ] Hand


- [x] Players
    - [x] Player의 List를 가진다.
    - [x] 1~10명의 Player가 존재하는지 검증한다.


- [ ] Game


- [x] ParticipantName
  - [x] 이름의 길이는 10글자 이내로 한다.

- [ ] ParticipantState


- [x] Card
    - [x] 0~51 사이의 숫자의 값을 인자로 받는다.
    - [x] 인자의 값을 통해 본인의 표식(Mark)를 결정한다.
    - [x] 인자의 값을 통해 본인의 value (1~10, J, Q, K)를 결정한다.


- [x] Mark (enum class)
    - 하트, 다이아, 클로버, 스페이드의 값을 가진다.


- [x] Value (enum class)
    - A(Ace), 2 부터 10 사이의 숫자, J, Q, K 의 값을 가진다.


- [x] Deck
  - 카드 덱의 인터페이스
  - pop 시 맨 윗장의 카드를 반환한다.


- [x] TrumpDeck
    - [x] 블랙잭 게임에 필요한 Deck을 가진다.
    - [x] 덱 내에 중복된 카드을 갖지 않는다.
    - [x] 52장의 카드를 갖는다.


- [x] TestDeck
    - [x] 테스트에 필요한 Deck을 사용자가 구성한다.


- [x] Answer
    - [x] 긍정 혹은 부정의 값을 보관한다.
    - [x] 대소문자 구분 없이, 입력이 y 또는 n이 아니면 예외가 발생한다.


- [x] Hand
    - [x] 카드의 리스트를 갖는다.
    - [x] 카드를 추가할 수 있다.
    - [x] 핸드의 값을 계산할 수 있다.


- [x] Point
  - 카드의 총 합 점수를 담는 Int 래퍼 클래스


- [x] Result (enum class)
    - 승/패/무 의 값을 가진다.


- [x] Judge
  - [x] 플레이어들의 결과를 반환한다.
  - [x] 딜러의 최종 결과를 반환한다.


---
- 📝 InputView
    - [x] 게임에 참여하는 사람의 이름 리스트를 입력 받을 수 있다.
    - [x] Player 들의 응답을 받을 수 있다.


- [x] OutputView
    - [x] Player의 이름을 출력한다.
        - (`ex. 딜러와 pobi, jason에게 2장의 나누었습니다.`)
    - [x] Dealer 와 Player의 핸드를 출력한다.
    - [x] Dealer 와 Player의 최종 핸드를 출력한다. (결과(최종합) 포함)
    - [x] 최종 승패 결과를 출력한다.
