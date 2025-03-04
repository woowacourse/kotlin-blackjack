# kotlin-blackjack


## 기능 구현 목록

### 카드
- [x] 숫자와 패턴을 가진 카드를 생성한다
  - 패턴은 클로버/하트/다이아/스페이드

<br>

### 덱
- [x] 고유한 52장의 카드를 가지고 있어야 한다
- [x] 요청한 수량에 맞게 카드를 뽑을 수 있다

<br>

### 플레이어
- [x] 이름과 패, 역할을 가지고 있다
- [x] 덱에서 카드를 뽑아서 패에 가져온다
- [x] 게임 상태를 가지고 있다
  - `STAY`/`BUST`/`HIT`/`BLACKJACK`

<br>

### 패
- [x] 패에 카드를 추가할 수 있다
- [x] 패에 들고 있는 카드의 합을 구할 수 있다

<br>

### 최종 승패
- [x] 딜러의 점수와 각 플레이어의 점수를 비교하여 승패 여부를 판단한다