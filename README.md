# 🚀 블랙잭

## 기능 요구 사항
블랙잭 게임을 변형한 프로그램을 구현한다.  
블랙잭 게임은 딜러와 플레이어 중 카드의 합이 21 또는 21에 가장 가까운 숫자를 가지는 쪽이 이기는 게임이다.
- 카드의 숫자 계산은 카드 숫자를 기본으로 하며, 예외로 Ace는 1 또는 11로 계산할 수 있으며, King, Queen, Jack은 각각 10으로 계산한다.
- 게임을 시작하면 플레이어는 두 장의 카드를 지급 받으며, 두 장의 카드 숫자를 합쳐 21을 초과하지 않으면서 21에 가깝게 만들면 이긴다. 21을 넘지 않을 경우 원한다면 얼마든지 카드를 계속 뽑을 수 있다.
- 딜러는 처음에 받은 2장의 합계가 16이하이면 반드시 1장의 카드를 추가로 받아야 하고, 17점 이상이면 추가로 받을 수 없다.
- 게임을 완료한 후 각 플레이어별로 승패를 출력한다.

## 기능 목록
### 카드
- [x] 9개의 숫자(2~10)와 4개의 문자(A,J,Q,K) 중 하나를 rank로 가진다.
- [x] 4개의 모양(다이아몬드, 클로버, 하트, 스페이드) 중 하나를 suit를 가진다.
- [x] 카드에 해당하는 숫자를 구한다.
- [x] Ace인지 확인한다.

### 카드 덱
- [x] 셔플된 52장의 카드를 가진다.
- [x] 카드를 한 장씩 뽑는다.

### 카드 핸드
- [x] 21에 가장 가까운 카드의 합을 구한다.

### 참가자
- [ ] 카드를 지급 받는다.
- [ ] 가진 카드의 합을 구한다.
- [ ] 버스트(21 초과)인지 확인한다.

- #### 플레이어
  - [ ] 이름을 가진다.
  - [ ] 딜러 카드의 합을 기준으로 승패를 구한다.
- #### 딜러
  - [ ] 카드의 합계가 16이하이면 1장의 카드를 추가로 받는다.
