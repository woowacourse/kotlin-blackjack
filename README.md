# kotlin-blackjack

- 카드
    - 카드는 숫자와 모양을 갖는다.
- Hand
    - 카드 리스트를 갖는다.
    - ACE 개수를 알 수 있다.
- 카드덱
    - [x] 카드 52장을 만든다.
    - [x] 카드 한장을 뽑고 삭제한다.

- 사람
    - 공통 (Person)
        - [x] 이름을 갖는다.
        - [x] 카드 모음을 갖는다.
        - [x] 상태가 Hit 이면 카드를 한장 더 받을 수 있다.
        - [x] 가지고 있는 카드의 총합을 구한다. (에이스 : 버스트면 1, 그렇지 않으면 11 )
    - Player
        - [x] 카드를 더 받을 수 있는지 확인한다. (A를 1로 간주)
          - [x] 21을 넘으면 더 받을 수 없다.
    - Dealer
        - [x] 총합이 16 이하면 한장 더 받을 수 있다.
        - [x] 카드 중에 하나만 보여준다.
    - Participants
      - [x] 딜러와 플레이어들을 갖는다.

- 상태
    - Started
      - PlayerFirstTurn
      - PlayerHit
      - DealerFirstTurn
      - DealerHit
      
    - Finished
      - BlackJack
        - PlayerBlackJack
        - DealerBlackJack
      - Bust
      - Stay
        - PlayerStay
        - DealerStay
        
- 게임 수익
    - 플레이어의 게임 수익을 반환
    - 딜러의 게임 수익을 반환
    

- 블랙젝 게임
  - [x] participants 와 deck 을 가진다.
  - [x] 플레이어에게 카드를 나눠준다.
  - [x] 딜러에게 카드를 나눠준다.
  - [x] 결과를 확인한다.