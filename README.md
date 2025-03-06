# kotlin-blackjack

- 카드들 상태
    - [X] 게임 진행 중 카드들의 상태는 Blackjack, Bust, None이 있다.
    - [X] 카드들의 점수와 처음 턴 여부를 받아서 카드들의 상태를 반환한다.
      - Blackjack는 처음 받은 카드의 합이 21이다.
      - Bust는 카드의 합이 21초과이다.
      - None은 카드의 합이 21미만이다.
- 게임 결과
    - [X] 게임 결과는 Push, Win, Lose가 있고, 한국어 게임 결과를 가진다.
    - [X] Push는 기준 점수가 비교 점수와 같다.
    - [X] Win는 기준 점수가 비교 점수보다 높다.
    - [X] Lose는 기준 점수가 비교 점수보다 낮다.
- 플레이어
    - [X] 플레이어는 이름과 카드 리스트를 가진다.
    - [X] 플레이어는 카드를 추가로 받을 수 있다.
    - [X] 플레이어가 이름만 가질 경우, 가진 카드 리스트는 비어있다.
    - 플레이어 이름 검증 로직
        - [ ] 플레이어의 이름은 1자 이상 5자 이내이다.
        - [ ] 플레이어 이름은 공백일 수 없다.
        - [ ] 플레이어 이름은 딜러가 될 수 없다.
- 카드 모양
    - [X] 카드 모양은 클로버, 하트, 다이아몬드, 스페이드를 가진다.
    - [X] 각 모양에 대해 한글 이름을 가진다.
- 카드
    - [X] Card는 모양과 denomination(끗수)를 가진다.
    - [X] Card의 끗수가 J, Q, K이면 숫자는 10이다.
    - [X] Card의 끗수가 2~10 사이의 숫자이면 숫자 그대로 가진다.
    - [X] 위의 끗수가 아닐 경우엔 숫자는 0이다.
- 카드들
    - [X] 카드들은 생성자로 카드 리스트를 가진다.
    - [X] Ace 카드가 0개 일때, 카드들의 총합을 반환한다.
    - [X] Ace 카드가 1개이고 카드의 합이 11미만 일때, Ace카드를 11로 판단한다.
    - [X] Ace 카드가 1개이고 카드의 합이 11이상 일때, Ace카드를 1로 판단한다.
    - [X] Ace 카드가 2개이고 카드의 합이 10미만 일때, Ace카드의 합을 12로 판단한다.
        - Ace의 카드 2장 중 1장은 11, 1장은 1로 판단한다.
    - [X] Ace 카드가 2개이고 카드의 합이 10이상 일때, Ace카드의 합을 2로 판단한다.
        - Ace의 카드 2장 다 1로 판단한다.
    - [X] Ace 카드가 3개이고 카드의 합이 9미만 일때, Ace카드의 합을 13로 판단한다.
        - Ace의 카드 3장 중 1장은 11, 2장은 1로 판단한다.
    - [X] Ace 카드가 3개이고 카드의 합이 9이상 일때, Ace카드의 합을 3으로 판단한다.
        - Ace의 카드 3장 다 1로 판단한다.
    - [X] Ace 카드가 4개이고 카드의 합이 8미만 일때, Ace카드의 합을 14로 판단한다.
        - Ace의 카드 4장 중 1장은 11, 3장은 1로 판단한다.
    - [X] Ace 카드가 4개이고 카드의 합이 8이상 일때, Ace카드를 합을 4으로 판단한다.
        - Ace의 카드 4장 다 1로 판단한다.
    - [X] 카드들에 카드를 추가할 수 있다.
- 플레이어 행동
    - [X] 응답은 Y, N로만 받을 수 있다.
    - [X] 플레이 행동은 hit과 stay로 나뉜다.
    - [X] 딜러 카드의 합이 16 이하일 경우 hit을 반환하고, 아닐 경우엔 stay를 반환한다.
- 딜러
    - [X] 딜러는 이름과 카드들을 가진다.
    - [X] 딜러는 이름이 없을 경우, 딜러라는 이름을 가진다.
    - [X] 딜러의 초기 카드 리스트는 비어있다.
    - [X] 딜러는 카드를 추가로 받을 수 있다.
- 카드 생성기
    - [X] 무작위로 섞은 카드 번들을 생성한다.
- 카드 덱
    - [X] 카드 덱은 첫번째 순서의 카드부터 차례대로 카드를 반환한다.
- 끗수
    - [X] 끗수는 제목과 숫자를 가진다.