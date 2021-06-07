# kotlin-blackjackgame

# 기능 구현 목록 
- 카드
  - [x] (하트, 스페이드, 다이아몬드, 클로버) * (A, 2-10, J, Q, K) 조합의 카드를 생성
  - [x] J,Q,K 은 10으로 계산
  - [x] Ace 는 1 또는 11 로 계산
  
- 카드 관리 (Deck)
  - [x] 무작위로 섞인 카드 뭉치를 생성한다.
  - [x] 첫 턴에는 카드를 2장씩 나눠준다.
  - [x] 이후의 턴에는 1장씩 패를 나눠줘야한다.
- 게임 참가자
  - [x] 플레이어의 이름을 받아 생성하는 기능
  - [x] 카드를 받는 기능
- [x] 게임 초기화 (경기 시작이후 처음 패를 나눠주는 기능)
- [x] 게임 진행중 (카드를 추가로 받는 기능)
  - [x] 점수의 합이 21이 넘지 않으면 계속 카드를 뽑을 수 있다.
  - [x] 카드를 추가로 받을지 선택할 수 있다. 
- [x] 플레이어가 가진 모든 카드와 점수를 보여주는 기능

# Step3

- [x] 딜러를 추가한다.
  - [x] 딜러의 초기 패는 한 장만 보여준다. 
  - [x] 딜러의 점수가 16 이하이면 카드를 추가로 한 장 받는다. 
- [x] 최종승패를 출력한다.

# Step4

## 플레이어
- [x] 플레이어는 게임 시작시 베팅금액을 정한다
- [x] 21을 초과하지 않으면서 21에 가까우면 이김 -> 1.0 배를 돌려받음
- [x] 처음 2장의 카드 합이 21이면 블랙잭으로 이김 -> 딜러에게 베팅금액의 1.5배를 돌려받음
- [x] 딜러와 동시에 블랙잭이면 비김 -> 1.0배를 돌려받음
- [x] 21을 초과하면 짐 -> 금액을 모두 잃음

## 딜러
- [x] 21점을 초과하면 플레이어들은 1.0배를 돌려받음

## 승패관련
- 이김, 비김 -> 1.0배
- 블랙잭으로 이김 -> 1.5배
- 짐 -> 0배

