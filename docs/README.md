# 블랙잭 기능 정의
* [ ] 카드 랜덤 생성
* [X] 유저에게 n장의 카드 분배
* [X] 유저가 카드를 더 분배받을 수 있는지 검사
* [X] 카드 숫자 합 계산
* [X] 최종 승패 결정


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