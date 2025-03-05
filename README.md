# kotlin-blackjack

- 플레이어 상태
    - [ ] 게임 진행 중 플레이어의 상태는 Blackjack, Bust, None이 있다.
    - [ ] Blackjack는 처음 받은 카드의 합이 21이다
    - [ ] Bust는 카드의 합이 21초과이다.
    - [ ] None은 카드의 합이 21미만이다.
- 게임 결과
    - [ ] 게임 결과는 Push, Win, Lose가 있다.
    - [ ] Push는 딜러의 점수와 플레이어의 점수가 같다.
    - [ ] Win는 딜러의 접수가 플레이어의 점수보다 높다.
    - [ ] Lose는 딜러의 점수가 플레이어의 점수보다 낮다.
- 플레이어
    - [X] 플레이어는 이름과 카드 리스트를 가진다.
    - [ ] 플레이어는 카드를 추가로 받을 수 있다.
- 카드 모양
    - [X] 카드 모양은 클로버, 하트, 다이아몬드, 스페이드를 가진다.
    - [X] 각 모양에 대해 한글 이름을 가진다.
- 카드
    - [X] Card는 모양과 denomination(끗수)를 가진다.
    - [X] Card의 끗수가 J, Q, K이면 숫자는 10이다.
    - [X] Card의 끗수가 2~10 사이의 숫자이면 숫자 그대로 가진다.
    - [X] 위의 끗수가 아닐 경우엔 숫자는 0이다.
- 카드들
    - [ ] 카드들은 생성자로 카드 리스트를 받는다.
    - [ ] Ace 카드가 0개 일때, 카드들의 총합을 반환한다.
    - [ ] Ace 카드가 1개 일때, Ace가 1일 경우, 11일 경우 각각의 카드들의 총합 중 21에 가까운 수를 반환한다.
    - [ ] Ace 카드가 2개 이상 일때, Ace 카드를 1로 치환하여, 카드들의 총합을 계산한다.
- Player Behavior
    - [X] 응답은 Y, N로만 받을 수 있다.
    - [X] 플레이 행동은 hit과 stay로 나뉜다.
- 딜러
    - [ ] 딜러는 이름과 카드들을 가진다.
    - [ ] 딜러가 가진 카드의 합이 16이상인지, 아닌지 판단할 수 있다.
- 카드 생성기
    - [ ] 카드 번들을 생성한다.
    - [ ] 카드를 무작위로 섞어 하나 반환한다.
