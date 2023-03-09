# 블랙잭 기능 정의
* [x] 카드 랜덤 생성
* [X] 유저에게 n장의 카드 분배
* [X] 유저가 카드를 더 분배받을 수 있는지 검사
* [X] 카드 숫자 합 계산
* [X] 최종 승패 결정

//STEP2
* [o] 배팅금액 받기 -> player 클래스에 name 과 카드와 돈이 담겨있는 resource 클래스를 넣어서관리
* [o] 수익 계산 ex) 1000원
  * [o] 2장 합이 21 = 배팅 금액 1.5 배 ex) 2500
  * [o] 그냥 이겼을 때 = 배팅 금액 1 배 ex) 2000
  * [o] 딜러 & 플레이어 21 = 원금 돌려받기 ex) 1000
  * [o] 21 넘음 = 배팅 금액 다 잃음 ex) 0
* [ ] 최종 수익 출력

### Entity
- CardType
- CardNumber
    - numberStrategy
- Card
    - type
    - number
- Cards
    - List<Card>

### Model
- User
    - name
    - cards
    - checkMoreCard(cardChecker)
    - determineGameResult(determiner)
- CardDistributeConditionChecker
    - Player
    - Dealer
- GameResultDeterminer
    - Player
    - Dealer
- CardDistributer
    - distribute(user, count)
- GameRule
    - initialDistribute()
    - checkUser(user)