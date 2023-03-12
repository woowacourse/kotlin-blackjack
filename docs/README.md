# 거지같은 블랙잭

## Domain
#### Card
- [X] Shape와 Value를 저장한다

#### CardDeck 
  - [x] Card로 포장하여 반환하다
  ##### RandomShapeGenerator
  - [x] Shape를 랜덤으로 뽑는다
  ##### RandomCardNumberGenerator -> Map<Shape, Value>
  - [x] Shape의 리스트를 셔플하고 한 장 뽑고 삭제한다
 

### Participant
- [x] CardBunch를 소유한다
- [x] 카드를 받아서 cardBunch에 추가한다

#### Dealer
- [x] 최종 승패를 계산한다
- [x] 딜러 카드의 합이 16이하인지 판단한다

#### Player
- [x] 이름을 갖고있는다

#### CardBunch
- [x] 초기화(카드는 중복될수 없으며, 초기 카드는 2장이 배정된다) 
- [x] 카드를 추가한다(중복 카드 검사)
- [x] 카드의 총합을 계산한다
- [x] 카드의 총합이 21을 넘었는지 체크하여 반환한다

### Enum class
#### Shape
- [x] 하트, 클로버, 스페이드, 다이아몬드
#### CardNumber
- [x] Value : Int?
- [x] Ace, Jack, Queen, King ... 숫자 영문 표기
#### Consequence
- [x] WIN,LOSE,DRAW

## View
### InputView
- [x] 게임에 참여할 사람을 입력받는다
- [x] 카드 지급여부를 입력받는다
    
### OutputView
- [x] 최초 나눠준 카드를 보여준다(딜러는 한장가림)
  - [x] 딜러의 카드를 보여준다
  - [x] 플레이어의 카드를 보여준다
  - [x] 현재 가지고 있는 카드를 출력한다

- [x] 카드 지급시 지급내역 출력한다
  
- [x] 딜러의 카드 합에 따른 카드 지급여부 출력한다

- [x] 카드 총합 출력한다

- [x] 최종 승패 출력한다

- [x] 최초 카드 분배 대사 출력

#### 적용해보자 상태패턴
- [ ]State
  - [ ] ProgressAble
    - [ ] 카드를 뽑는다(draw)
      - [x] Hit
      - [x] FirstTurn
  - [ ] Stopped
    - [x] Stay
    - [x] BlackJack
    - [x] Bust